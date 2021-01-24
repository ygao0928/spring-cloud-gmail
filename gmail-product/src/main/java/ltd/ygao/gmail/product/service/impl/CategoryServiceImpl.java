package ltd.ygao.gmail.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import ltd.ygao.gmail.product.service.CategoryBrandRelationService;
import ltd.ygao.gmail.product.vo.Catelog2Vo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ltd.ygao.gmail.common.utils.PageUtils;
import ltd.ygao.gmail.common.utils.Query;

import ltd.ygao.gmail.product.dao.CategoryDao;
import ltd.ygao.gmail.product.entity.CategoryEntity;
import ltd.ygao.gmail.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redisson;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //查出所有的分类
        List<CategoryEntity> entities = baseMapper.selectList(null);
        //将查出的分类以树形结构
        //先查出一级分类
        List<CategoryEntity> level1Menus = entities.stream().filter(categoryEntity ->
                categoryEntity.getParentCid() == 0
        ).map((menu) -> {
            menu.setChildren(getChildrens(menu, entities));
            return menu;
        }).sorted((menu1, menu2) -> {
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());
        return level1Menus;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO 1.检查当前删除的菜单，是否被其他地方引用
        baseMapper.deleteBatchIds(asList);
    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, paths);
        Collections.reverse(parentPath);
        return parentPath.toArray(new Long[parentPath.size()]);
    }

    /**
     * 级联更新
     *
     * @param category
     */

//    @Caching(evict = {
//            @CacheEvict(value = "category", key = "'getLevel1Categorys'"),
//            @CacheEvict(value = "category", key = "'getCatalogJson'")
//    })
    @CacheEvict(value = "category", allEntries = true)//失效模式  //删除同一片区的所有的数据
    //  @CachePut //双写模式
    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
    }

    //每一个缓存的数据需要我们都要指定放到那个名字的缓存
    @Cacheable(value = "category", key = "#root.method.name")  //代表当前方法的结果需要缓存，如果缓存中有，方法不调用，如果缓存没有，会调用方法，最后将方法的结果
    @Override
    public List<CategoryEntity> getLevel1Categorys() {
        System.out.println("getLevel1Categorys。。。。。。");
        long l = System.currentTimeMillis();
        List<CategoryEntity> categoryEntities = baseMapper.selectList(new QueryWrapper<CategoryEntity>().lambda().eq(CategoryEntity::getParentCid, 0));
        System.out.println("消耗时间：" + (System.currentTimeMillis() - l));
        return categoryEntities;
    }

    //todo redis会出现堆外内存溢出的异常
    //产生原因 lettuce-core 操作netty的内存回收异常
    //解决方案，更新lettuce。更换jedis
    //@Override
    public Map<String, List<Catelog2Vo>> getCatalogJson2() {
        /**
         * 1、空结果缓存设置过期时间：解决缓存的穿透
         * 2、给缓存的过期时间加上随机数防止大面积的key同一时间失效；解决缓存雪崩
         * 3、给某个热点数据（key）加上锁，只让一个人来查；解决缓存击穿
         */
        //加入缓存逻辑，
        String catalogJson = redisTemplate.opsForValue().get("catalogJson");
        if (StringUtils.isEmpty(catalogJson)) {
            //2、缓存中不存在，查询数据库
            //保证数据库查询完成以后，将数据库放在redis，这是一个原子操作
            System.out.println("缓存不命中。。。。。将要查询数据库");
            Map<String, List<Catelog2Vo>> catalogJsonFromDB = getCatalogJsonFromDBWithRedisLock();
            //3、查到的数据转成json放在缓存中
            redisTemplate.opsForValue().set("catalogJson", JSON.toJSONString(catalogJsonFromDB), 1, TimeUnit.DAYS);
            return catalogJsonFromDB;
        }
        System.out.println("缓存命中。。。直接返回");
        Map<String, List<Catelog2Vo>> result = JSON.parseObject(catalogJson, new TypeReference<Map<String, List<Catelog2Vo>>>() {
        });
        return result;
    }

    /**
     * spring-cache 的不足
     * 1）、读模式：
     * 缓存穿透：查询一个null数据。解决空数据：cache-null-value=true
     * 缓存击穿：大量并发进来同时查询一个正好过期的数据，解决方案：加锁？
     * 缓存雪崩：大量key 同时过期：解决方案：加随机时间
     * 2）、写模式：（缓存宇与数据库一致）
     *    1）、读写加锁
     *    2）、引入Canal，感知MYSQL的更新去更新数据库
     *    3）、读多写多，直接去查询数据库
     * @return
     */
    @Cacheable(value = "category", key = "#root.methodName")
    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        System.out.println("查询了数据库");
        List<CategoryEntity> selectList = baseMapper.selectList(null);
        //1.查出所有的一级分类
        List<CategoryEntity> level1Categorys = getParent_cid(selectList, 0L);
        //2.封装数据
        Map<String, List<Catelog2Vo>> collect = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            //1每一个的一级分类，查到这个一级分类的二级分类
            List<CategoryEntity> categoryEntities = getParent_cid(selectList, v.getCatId());
            List<Catelog2Vo> catelog2Vos = null;
            if (categoryEntities != null) {
                catelog2Vos = categoryEntities.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
                    //找当前的二级分类的三级分类封装成vo
                    List<CategoryEntity> level3Category = getParent_cid(selectList, l2.getCatId());
                    if (level3Category != null) {
                        List<Catelog2Vo.Catelog3Vo> collect1 = level3Category.stream().map(l3 -> {
                            Catelog2Vo.Catelog3Vo catelog3Vo = new Catelog2Vo.Catelog3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
                            return catelog3Vo;
                        }).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(collect1);
                    }
                    return catelog2Vo;
                }).collect(Collectors.toList());
            }
            return catelog2Vos;
        }));
        return collect;
    }

    /**
     * 缓存数据的一致性
     * 方案：
     * 1、双写模式
     * 2.失效模式
     *
     * @return
     */
    public Map<String, List<Catelog2Vo>> getCatalogJsonFromDBWithRedissonLock() {
        RLock rLock = redisson.getLock("catalogJson-lock");
        rLock.lock();
        Map<String, List<Catelog2Vo>> dataFromDb;
        try {
            dataFromDb = getDataFromDb();
        } finally {
            rLock.unlock();
        }
        return dataFromDb;

    }

    public Map<String, List<Catelog2Vo>> getCatalogJsonFromDBWithRedisLock() {
        //1、占分布式锁，去占redis锁
        String token = UUID.randomUUID().toString();
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", token, 300, TimeUnit.SECONDS);
        if (lock) {
            System.out.println("获取分布式锁成功");
            //redisTemplate.expire("lock",30,TimeUnit.SECONDS);
            //加锁成功，执行业务
            //2、设置过期时间 和加锁必须是原子操作 set EX NX
            Map<String, List<Catelog2Vo>> dataFromDb;
            try {
                dataFromDb = getDataFromDb();
            } finally {
                String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
                //删除
                Long lock1 = redisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class)
                        , Arrays.asList("lock"), token);
            }
            //redisTemplate.delete("lock");//删除锁
            //获取值对比，+对比成功删除=原子操作 lua 脚本解锁
//            String lockValue = redisTemplate.opsForValue().get("lock");
//            if (token.equals(lockValue)) {
//                //删除锁
//                redisTemplate.delete("lock");
//            }
            return dataFromDb;
        } else {
            System.out.println("获取分布式锁失败。。等待");
            //加锁失败。。。重试
            //休眠100ms
            try {
                Thread.sleep(200);
            } catch (Exception e) {
            }
            return getCatalogJsonFromDBWithRedisLock();//自旋方式
        }
    }

    private Map<String, List<Catelog2Vo>> getDataFromDb() {
        String catalogJson = redisTemplate.opsForValue().get("catalogJson");
        if (!StringUtils.isEmpty(catalogJson)) {
            Map<String, List<Catelog2Vo>> result = JSON.parseObject(catalogJson, new TypeReference<Map<String, List<Catelog2Vo>>>() {
            });
            return result;
        }
        /**
         * 将多次数据库的查询变为一次
         */
        System.out.println("查询了数据库");
        List<CategoryEntity> selectList = baseMapper.selectList(null);
        //1.查出所有的一级分类
        List<CategoryEntity> level1Categorys = getParent_cid(selectList, 0L);
        //2.封装数据
        Map<String, List<Catelog2Vo>> collect = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            //1每一个的一级分类，查到这个一级分类的二级分类
            List<CategoryEntity> categoryEntities = getParent_cid(selectList, v.getCatId());
            List<Catelog2Vo> catelog2Vos = null;
            if (categoryEntities != null) {
                catelog2Vos = categoryEntities.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
                    //找当前的二级分类的三级分类封装成vo
                    List<CategoryEntity> level3Category = getParent_cid(selectList, l2.getCatId());
                    if (level3Category != null) {
                        List<Catelog2Vo.Catelog3Vo> collect1 = level3Category.stream().map(l3 -> {
                            Catelog2Vo.Catelog3Vo catelog3Vo = new Catelog2Vo.Catelog3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
                            return catelog3Vo;
                        }).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(collect1);
                    }
                    return catelog2Vo;
                }).collect(Collectors.toList());
            }
            return catelog2Vos;
        }));
        //3. 查到的数据再放到缓存，将数据转为json放在缓存中
        redisTemplate.opsForValue().set("catalogJson", JSON.toJSONString(collect), 1, TimeUnit.DAYS);
        return collect;
    }

    //从数据库查询的封装数据
    public Map<String, List<Catelog2Vo>> getCatalogJsonFromDBWithLocalLock() {
        synchronized (this) {
            //得到锁，需要再去查询一些缓存的
            return getDataFromDb();
        }

    }

    private List<CategoryEntity> getParent_cid(List<CategoryEntity> selectList, Long parent_cid) {
        List<CategoryEntity> collect = selectList.stream().filter(item -> item.getParentCid() == parent_cid).collect(Collectors.toList());

        return collect;
    }

    private List<Long> findParentPath(Long catelogId, List<Long> paths) {
        paths.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if (byId.getParentCid() != 0) {
            findParentPath(byId.getParentCid(), paths);
        }
        return paths;
    }

    //递归查找所有菜单的子菜单
    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> children = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentCid() == root.getCatId();
        }).map(categoryEntity -> {
            //找到子菜单
            categoryEntity.setChildren(getChildrens(categoryEntity, all));
            return categoryEntity;
        }).sorted((menu1, menu2) -> {
            //菜单排序
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());
        return children;
    }

}