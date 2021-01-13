package ltd.ygao.gmail.product.feign;

import ltd.ygao.gmail.common.to.SkuHasStockVo;
import ltd.ygao.gmail.common.utils.R;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Kevin Gao
 * @version 1.0.0
 * @ClassName WareFeignService.java
 * @date 2020/8/24 23:42
 * @Description
 * @Content:
 */
@FeignClient("gmail-ware")
public interface WareFeignService {
    @PostMapping("/ware/waresku/hasstock")
     R getSkuHasStock(@RequestBody List<Long> skuIds);

}
