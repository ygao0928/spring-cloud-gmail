package ltd.ygao.gmail.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ltd.ygao.gmail.common.utils.PageUtils;
import ltd.ygao.gmail.coupon.entity.CouponEntity;

import java.util.Map;

/**
 * 优惠券信息
 *
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-05-08 13:36:14
 */
public interface CouponService extends IService<CouponEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

