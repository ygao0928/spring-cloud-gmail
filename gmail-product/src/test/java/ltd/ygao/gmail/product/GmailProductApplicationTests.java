package ltd.ygao.gmail.product;

import lombok.extern.slf4j.Slf4j;
import ltd.ygao.gmail.product.entity.BrandEntity;
import ltd.ygao.gmail.product.service.BrandService;
import ltd.ygao.gmail.product.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.jws.Oneway;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.UUID;


@SpringBootTest
@Slf4j
class GmailProductApplicationTests {
    @Autowired
    BrandService brandService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    private StringRedisTemplate redisTemplate;
@Test
public void testStringRedisTemplate(){
    ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();

    //保存
    opsForValue.set("Hello"," World"+ UUID.randomUUID().toString());
    //查询
    String hello = opsForValue.get("Hello");
    System.out.println("之前保存的数据"+hello);
}
    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("华为");
        brandService.save(brandEntity);
    }

    @Test
    public void testFindPath() {
        Long[] catelogPath = categoryService.findCatelogPath(225L);
        log.info("完整路径：" + Arrays.asList(catelogPath));
    }
}
