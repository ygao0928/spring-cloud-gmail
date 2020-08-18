package ltd.ygao.gmail.product.service.impl;

import ltd.ygao.gmail.common.utils.PageUtils;
import ltd.ygao.gmail.common.utils.Query;
import ltd.ygao.gmail.product.dao.SkuSaleAttrValueDao;
import ltd.ygao.gmail.product.entity.SkuSaleAttrValueEntity;
import ltd.ygao.gmail.product.service.SkuSaleAttrValueService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



@Service("skuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValueEntity> implements SkuSaleAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuSaleAttrValueEntity> page = this.page(
                new Query<SkuSaleAttrValueEntity>().getPage(params),
                new QueryWrapper<SkuSaleAttrValueEntity>()
        );

        return new PageUtils(page);
    }

}