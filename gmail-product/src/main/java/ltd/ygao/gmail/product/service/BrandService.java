package ltd.ygao.gmail.product.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import ltd.ygao.gmail.common.utils.PageUtils;
import ltd.ygao.gmail.product.entity.BrandEntity;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * 品牌
 *
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-05-07 16:54:29
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    @Override
    default boolean saveBatch(Collection<BrandEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    default boolean saveOrUpdateBatch(Collection<BrandEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    default boolean updateBatchById(Collection<BrandEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    default boolean saveOrUpdate(BrandEntity entity) {
        return false;
    }

    @Override
    default BrandEntity getOne(Wrapper<BrandEntity> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    default Map<String, Object> getMap(Wrapper<BrandEntity> queryWrapper) {
        return null;
    }

    @Override
    default <V> V getObj(Wrapper<BrandEntity> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    default BaseMapper<BrandEntity> getBaseMapper() {
        return null;
    }

    void updateDetail(BrandEntity brand);
}

