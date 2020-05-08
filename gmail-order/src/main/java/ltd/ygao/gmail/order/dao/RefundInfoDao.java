package ltd.ygao.gmail.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.ygao.gmail.order.entity.RefundInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退款信息
 * 
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-05-08 10:56:29
 */
@Mapper
public interface RefundInfoDao extends BaseMapper<RefundInfoEntity> {
	
}
