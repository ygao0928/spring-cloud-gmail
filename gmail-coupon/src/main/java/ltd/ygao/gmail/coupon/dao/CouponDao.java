package ltd.ygao.gmail.coupon.dao;

import ltd.ygao.gmail.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-05-08 13:36:14
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
