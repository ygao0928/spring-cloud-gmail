package ltd.ygao.gmail.product.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import ltd.ygao.gmail.product.entity.AttrEntity;
import org.apache.catalina.LifecycleState;

import java.util.List;

/**
 * @author Kevin
 * @version 1.0
 * @date 2020/6/3 23:43
 */
@Data
public class AttrGroupWithAttrsVo {
    /**
     * 分组id
     */
    @TableId
    private Long attrGroupId;
    /**
     * 组名
     */
    private String attrGroupName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String descript;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    private Long catelogId;
    private List<AttrEntity> attrs;
}
