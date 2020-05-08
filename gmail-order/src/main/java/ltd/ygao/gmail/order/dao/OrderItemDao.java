package ltd.ygao.gmail.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.ygao.gmail.order.entity.OrderItemEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-05-08 10:56:29
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}
