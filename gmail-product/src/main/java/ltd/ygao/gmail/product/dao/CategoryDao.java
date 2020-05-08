package ltd.ygao.gmail.product.dao;

import ltd.ygao.gmail.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author Kevin
 * @email ygao0928@outlook.com
 * @date 2020-05-07 16:54:29
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
