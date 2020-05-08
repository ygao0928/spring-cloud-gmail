package ltd.ygao.gmail.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ltd.ygao.gmail.common.utils.PageUtils;
import ltd.ygao.gmail.ware.entity.WmsWareOrderTaskEntity;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-05-08 11:25:38
 */
public interface WmsWareOrderTaskService extends IService<WmsWareOrderTaskEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

