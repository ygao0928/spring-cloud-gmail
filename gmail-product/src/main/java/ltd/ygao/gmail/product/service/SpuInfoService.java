package ltd.ygao.gmail.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ltd.ygao.gmail.common.utils.PageUtils;
import ltd.ygao.gmail.product.entity.SpuInfoEntity;
import ltd.ygao.gmail.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-05-07 16:54:29
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVo vo);

}

