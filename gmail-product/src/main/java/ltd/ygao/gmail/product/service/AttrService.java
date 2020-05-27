package ltd.ygao.gmail.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ltd.ygao.gmail.common.utils.PageUtils;
import ltd.ygao.gmail.product.entity.AttrEntity;
import ltd.ygao.gmail.product.vo.AttrVo;

import java.util.Map;

/**
 * 商品属性
 *
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-05-22 17:42:26
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attr);
}

