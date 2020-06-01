package ltd.ygao.gmail.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ltd.ygao.gmail.common.utils.PageUtils;
import ltd.ygao.gmail.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-06-01 00:49:13
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

