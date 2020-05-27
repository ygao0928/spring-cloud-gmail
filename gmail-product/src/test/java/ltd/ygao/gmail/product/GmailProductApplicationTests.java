package ltd.ygao.gmail.product;

import lombok.extern.slf4j.Slf4j;
import ltd.ygao.gmail.product.entity.BrandEntity;
import ltd.ygao.gmail.product.service.BrandService;
import ltd.ygao.gmail.product.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jws.Oneway;
import java.lang.reflect.Array;
import java.util.Arrays;


@SpringBootTest
@Slf4j
class GmailProductApplicationTests {
    @Autowired
    BrandService brandService;
@Autowired
    CategoryService categoryService;

    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("华为");
        brandService.save(brandEntity);
    }

    @Test
    public void testFindPath() {
        Long[] catelogPath = categoryService.findCatelogPath(225L);
        log.info("完整路径："+ Arrays.asList(catelogPath));
    }
}
