package ltd.ygao.gmail.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ltd.ygao.gmail.common.utils.PageUtils;
import ltd.ygao.gmail.ware.entity.WareOrderTaskDetailEntity;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-08-19 14:24:51
 */
public interface WareOrderTaskDetailService extends IService<WareOrderTaskDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

