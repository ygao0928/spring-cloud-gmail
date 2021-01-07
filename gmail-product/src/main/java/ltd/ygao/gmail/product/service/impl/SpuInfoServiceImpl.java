package ltd.ygao.gmail.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ltd.ygao.gmail.common.constant.ProductConstant;
import ltd.ygao.gmail.common.to.SkuHasStockVo;
import ltd.ygao.gmail.common.to.SkuReductionTo;
import ltd.ygao.gmail.common.to.SpuBoundTo;
import ltd.ygao.gmail.common.to.es.SkuEsModel;
import ltd.ygao.gmail.common.utils.PageUtils;
import ltd.ygao.gmail.common.utils.Query;
import ltd.ygao.gmail.common.utils.R;
import ltd.ygao.gmail.product.dao.SpuInfoDao;
import ltd.ygao.gmail.product.entity.*;
import ltd.ygao.gmail.product.feign.CouponFeignService;
import ltd.ygao.gmail.product.feign.SearchFeignService;
import ltd.ygao.gmail.product.feign.WareFeignService;
import ltd.ygao.gmail.product.service.*;
import ltd.ygao.gmail.product.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {
    @Autowired
    private SpuInfoDescService spuInfoDescService;
    @Autowired
    SpuImagesService spuImagesService;
    @Autowired
    private AttrService attrService;
    @Autowired
    ProductAttrValueService productAttrValueService;
    @Autowired
    private SkuInfoService skuInfoService;
    @Autowired
    private SkuImagesService skuImagesService;
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;
    @Autowired
    private CouponFeignService couponFeignService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private WareFeignService wareFeignService;
    @Autowired
    private SearchFeignService searchFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveSpuInfo(SpuSaveVo vo) {
        //1.保存spu基本信息
        SpuInfoEntity infoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(vo, infoEntity);
        infoEntity.setCreateTime(new Date());
        infoEntity.setUpdateTime(new Date());
        this.saveBaseSpuInfo(infoEntity);
        //2.保存spu的描述图片
        List<String> decript = vo.getDecript();
        SpuInfoDescEntity spuInfoDescEntity = new SpuInfoDescEntity();
        spuInfoDescEntity.setSpuId(infoEntity.getId());
        spuInfoDescEntity.setDecript(String.join(",", decript));
        spuInfoDescService.saveSpuInfoDesc(spuInfoDescEntity);
        //3.保存spu的图片集
        List<String> images = vo.getImages();
        spuImagesService.saveImages(infoEntity.getId(), images);
        //4.保存spu的规格参数;
        List<BaseAttrs> baseAttrs = vo.getBaseAttrs();
        List<ProductAttrValueEntity> collect = baseAttrs.stream().map(attr -> {
            ProductAttrValueEntity valueEntity = new ProductAttrValueEntity();
            valueEntity.setAttrId(attr.getAttrId());
            AttrEntity byId = attrService.getById(attr.getAttrId());
            valueEntity.setAttrName(byId.getAttrName());
            valueEntity.setAttrValue(attr.getAttrValues());
            valueEntity.setQuickShow(attr.getShowDesc());
            valueEntity.setSpuId(infoEntity.getId());
            return valueEntity;
        }).collect(Collectors.toList());
        productAttrValueService.saveProductAttr(collect);
        //5.保存积分信息
        Bounds bounds = vo.getBounds();
        SpuBoundTo spuBoundTo = new SpuBoundTo();
        BeanUtils.copyProperties(bounds, spuBoundTo);
        spuBoundTo.setSpuId(infoEntity.getId());
        R r = couponFeignService.saveSpuBounds(spuBoundTo);
        if (r.getCode() != 0) {
            log.error("远程调用积分信息错误");
        }
        //5.保存当前的spu对应的sku信息
        //5.1 sku的基本信息
        List<Skus> skus = vo.getSkus();
        if (skus != null && skus.size() > 0) {
            skus.forEach(item -> {
                String defaultImg = "";
                for (Images img : item.getImages()) {
                    if (img.getDefaultImg() == 1) {
                        defaultImg = img.getImgUrl();
                    }
                }
                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                BeanUtils.copyProperties(item, skuInfoEntity);
                skuInfoEntity.setBrandId(infoEntity.getBrandId());
                skuInfoEntity.setCatalogId(infoEntity.getCatalogId());
                skuInfoEntity.setSaleCount(0L);
                skuInfoEntity.setSpuId(infoEntity.getId());
                skuInfoEntity.setSkuDefaultImg(defaultImg);
                skuInfoService.saveSkuInfo(skuInfoEntity);
                Long skuId = skuInfoEntity.getSkuId();
                List<SkuImagesEntity> imagesEntities = item.getImages().stream().map(img -> {
                    SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                    skuImagesEntity.setSkuId(skuId);
                    skuImagesEntity.setImgUrl(img.getImgUrl());
                    skuImagesEntity.setDefaultImg(img.getDefaultImg());
                    return skuImagesEntity;
                }).filter(entity -> {
                    return !StringUtils.isEmpty(entity.getImgUrl());
                }).collect(Collectors.toList());
                //5.2 保存sku的图片信息
                skuImagesService.saveBatch(imagesEntities);
                //5.3 保存sku的销售属性
                List<Attr> attr = item.getAttr();
                List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = attr.stream().map(a -> {
                    SkuSaleAttrValueEntity attrValueEntity = new SkuSaleAttrValueEntity();
                    BeanUtils.copyProperties(a, attrValueEntity);
                    attrValueEntity.setSkuId(skuId);
                    return attrValueEntity;
                }).collect(Collectors.toList());
                skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);
                //5.4 sku的满减优惠信息
                SkuReductionTo skuReductionTo = new SkuReductionTo();
                BeanUtils.copyProperties(item, skuReductionTo);
                skuReductionTo.setSkuId(skuId);
                if (skuReductionTo.getFullCount() > 0 || skuReductionTo.getFullPrice().compareTo(new BigDecimal("0")) == 1) {
                    R r1 = couponFeignService.saveSkuReduction(skuReductionTo);
                    if (r1.getCode() != 0) {
                        log.error("远程调用sku优惠信息失败");
                    }
                }

            });
        }


    }


    private void saveBaseSpuInfo(SpuInfoEntity infoEntity) {
        this.baseMapper.insert(infoEntity);

    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();

        /**
         * status:
         * key:
         * brandId: 0
         * catelogId: 0
         */
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wrapper.and((w) -> {
                w.eq("id", key).or().like("spu_name", key);
            });
        }
        String status = (String) params.get("status");
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("publish_status", status);

        }
        String brandId = (String) params.get("brandId");
        if (!StringUtils.isEmpty(brandId) && !"0".equalsIgnoreCase(brandId)) {
            wrapper.eq("brand_id", brandId);
        }
        String catelogId = (String) params.get("catelogId");
        if (!StringUtils.isEmpty(catelogId) && !"0".equalsIgnoreCase(catelogId)) {
            wrapper.eq("catalog_id", catelogId);
        }

        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    /**
     * 商品上架
     *
     * @param spuId
     */
    @Override
    public void up(Long spuId) {
        //组装需要的数据
        SkuEsModel esModel = new SkuEsModel();
        //查出spuId所有的的sku的信息，品牌的名字
        List<SkuInfoEntity> skuInfoEntities = skuInfoService.getSkusBySpuId(spuId);
        List<Long> skuIdList = skuInfoEntities.stream().map(SkuInfoEntity::getSkuId).collect(Collectors.toList());
        //TODO 4.查询所有的attr的所有可以被检索的规格属性
        List<ProductAttrValueEntity> baseAttrs = productAttrValueService.baseAttrlistforspu(spuId);
        List<Long> attrIds = baseAttrs.stream().map(attr -> {
            return attr.getAttrId();
        }).collect(Collectors.toList());
        List<Long> searchAttrIds = attrService.selectSearchAttrIds(attrIds);
        Set<Long> idSet = new HashSet<>(searchAttrIds);

        List<Attr> attrsList = baseAttrs.stream().filter(item -> {
            return idSet.contains(item.getAttrId());
        }).map(item -> {
            Attr attrs1 = new Attr();
            BeanUtils.copyProperties(item, attrs1);
            return attrs1;
        }).collect(Collectors.toList());
        //TODO 1.发送远程调用，库存系统是否有库存

        Map<Long, Boolean> stockMap = null;
        try {
            R<List<SkuHasStockVo>> skuHasStock = wareFeignService.getSkuHasStock(skuIdList);
            stockMap = skuHasStock.getData().stream().collect(Collectors.toMap(SkuHasStockVo::getSkuId, item -> item.getHasStock()));
        } catch (Exception e) {
            log.error("库存服务查询异常：原因（）", e);
            e.printStackTrace();
        }
        //封装每个sku的信息
        Map<Long, Boolean> finalStockMap = stockMap;
        List<SkuEsModel> upProducts = skuInfoEntities.stream().map(sku -> {
            SkuEsModel esModel1 = new SkuEsModel();
            BeanUtils.copyProperties(sku, esModel1);
            //skuPrice  skuImg  hasStock  hotScore
            esModel1.setSkuPrice(sku.getPrice());
            esModel1.setSkuImg(sku.getSkuDefaultImg());
            if (finalStockMap == null) {
                esModel1.setHasStock(true);

            } else {
                esModel1.setHasStock(finalStockMap.get(sku.getSkuId()));
            }
            //TODO 2.热度评分，0
            esModel1.setHotScore(0L);
            //TODO 3.查询品牌和分类
            BrandEntity brandEntity = brandService.getById(esModel1.getBrandId());
            esModel1.setBrandImg(brandEntity.getLogo());
            esModel1.setBrandName(brandEntity.getName());
            CategoryEntity categoryEntity = categoryService.getById(esModel1.getCatalogId());
            esModel1.setCatalogName(categoryEntity.getName());
            esModel1.setAttrs(Collections.singletonList(attrsList));
            return esModel1;
        }).collect(Collectors.toList());
        //TODO 5.将数据发送给es进行保存
        R r = searchFeignService.productStatusUp(upProducts);
        if (r.getCode() == 0) {
            //远程调用成功
            //todo 6. 修改当前spu的转态
            this.baseMapper.updateSpuStatus(spuId, ProductConstant.StatusEnum.SPU_UP.getCode());
        } else {
            //远程调用失败
            // todo 7.重复调用,接口的幂等性：重试机制；
        }

    }


}