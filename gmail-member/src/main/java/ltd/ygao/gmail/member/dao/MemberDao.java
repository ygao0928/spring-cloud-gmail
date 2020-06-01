package ltd.ygao.gmail.member.dao;

import ltd.ygao.gmail.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-06-01 00:49:13
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
