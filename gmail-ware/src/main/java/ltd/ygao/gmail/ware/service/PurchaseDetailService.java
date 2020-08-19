package ltd.ygao.gmail.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ltd.ygao.gmail.common.utils.PageUtils;
import ltd.ygao.gmail.ware.entity.PurchaseDetailEntity;

import java.util.Map;

/**
 * 
 *
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-08-19 14:24:51
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

