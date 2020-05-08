package ltd.ygao.gmail.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.ygao.gmail.order.entity.OrderReturnReasonEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退货原因
 * 
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-05-08 10:56:29
 */
@Mapper
public interface OrderReturnReasonDao extends BaseMapper<OrderReturnReasonEntity> {
	
}
