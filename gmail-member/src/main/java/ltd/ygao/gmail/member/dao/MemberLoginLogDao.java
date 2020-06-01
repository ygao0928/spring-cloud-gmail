package ltd.ygao.gmail.member.dao;

import ltd.ygao.gmail.member.entity.MemberLoginLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员登录记录
 * 
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-06-01 00:49:14
 */
@Mapper
public interface MemberLoginLogDao extends BaseMapper<MemberLoginLogEntity> {
	
}
