package ltd.ygao.gmail.member.feign;

import ltd.ygao.gmail.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kevin
 * @version 1.0
 * @date 2020/5/8 14:21
 */
@FeignClient("gmail-coupon")
public interface CouponFeignService {
    @RequestMapping("/coupon/coupon/member/list")
     R memberCoupons();
}
