package ltd.ygao.gmail.ware.feign;

import ltd.ygao.gmail.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kevin Gao
 * @version 1.0
 * @ClassName ProductFeignService.java
 * @Description TODO
 * @date 2020/8/19 23:49
 * @Content:
 */
@FeignClient("gmail-gateway")
public interface ProductFeignService {
    /**
     * 1)、让所有的请求过网关
     *      1.@FeignClient("gmail-gateway")；给gmail-gateway所在的机器发请求
     *      2./api/product/skuinfo/info/{skuId}
     * 2）、直接让后台指定服务器处理
     *      1、@FeignClient("gmail-product")
     *      2、/product/skuinfo/info/{skuId}
     *
     * @param skuId
     * @return
     */
    @RequestMapping("/api/product/skuinfo/info/{skuId}")
    public R info(@PathVariable("skuId") Long skuId);
}
