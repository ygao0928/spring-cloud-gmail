package ltd.ygao.gmail.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.ygao.gmail.order.entity.PaymentInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付信息表
 * 
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-05-08 10:56:29
 */
@Mapper
public interface PaymentInfoDao extends BaseMapper<PaymentInfoEntity> {
	
}
