package ltd.ygao.gmail.product;

import ltd.ygao.gmail.product.entity.BrandEntity;
import ltd.ygao.gmail.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GmailProductApplicationTests {
@Autowired
    BrandService brandService;
    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("华为");
        brandService.save(brandEntity);
    }

}
