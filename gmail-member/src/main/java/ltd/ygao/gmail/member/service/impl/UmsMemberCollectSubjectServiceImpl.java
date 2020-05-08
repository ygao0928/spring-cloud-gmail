package ltd.ygao.gmail.member.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ltd.ygao.gmail.common.utils.PageUtils;
import ltd.ygao.gmail.common.utils.Query;

import ltd.ygao.gmail.member.dao.UmsMemberCollectSubjectDao;
import ltd.ygao.gmail.member.entity.UmsMemberCollectSubjectEntity;
import ltd.ygao.gmail.member.service.UmsMemberCollectSubjectService;


@Service("umsMemberCollectSubjectService")
public class UmsMemberCollectSubjectServiceImpl extends ServiceImpl<UmsMemberCollectSubjectDao, UmsMemberCollectSubjectEntity> implements UmsMemberCollectSubjectService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UmsMemberCollectSubjectEntity> page = this.page(
                new Query<UmsMemberCollectSubjectEntity>().getPage(params),
                new QueryWrapper<UmsMemberCollectSubjectEntity>()
        );

        return new PageUtils(page);
    }

}