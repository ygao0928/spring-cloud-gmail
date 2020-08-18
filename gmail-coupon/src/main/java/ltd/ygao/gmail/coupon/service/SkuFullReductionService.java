package ltd.ygao.gmail.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ltd.ygao.gmail.common.to.SkuReductionTo;
import ltd.ygao.gmail.common.utils.PageUtils;
import ltd.ygao.gmail.coupon.entity.SkuFullReductionEntity;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-05-08 13:36:14
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuReduction(SkuReductionTo skuReductionTo);
}

