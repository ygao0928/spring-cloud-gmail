package ltd.ygao.gmail.product.feign;

import ltd.ygao.gmail.common.to.SkuReductionTo;
import ltd.ygao.gmail.common.to.SpuBoundTo;
import ltd.ygao.gmail.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Kevin Gao
 * @version 1.0
 * @ClassName SpuFeignService.java
 * @Description TODO
 * @date 2020/8/18 17:06
 * @Content:
 */
@FeignClient("gmail-coupon")
public interface CouponFeignService {
    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);

    @PostMapping("/coupon/skufullreduction/saveInfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);

}
