package ltd.ygao.gmail.product.feign;

import ltd.ygao.gmail.common.to.es.SkuEsModel;
import ltd.ygao.gmail.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Kevin Gao
 * @version 1.0.0
 * @ClassName SearchFeignService.java
 * @date 2020/10/12 17:11
 * @Description
 * @Content:
 */
@FeignClient("gmail-search")
public interface SearchFeignService {
    @PostMapping("/search/save/product")
    R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
}
