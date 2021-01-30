package ltd.ygao.search.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Kevin Gao
 * @version 1.0.0
 * @ClassName SearchParam.java
 * @date 2021/1/25 0:25
 * @Description 封装页面所有的传递过来的查询条件
 * @Content:
 */
@Data
public class SearchParam {
    private String keyword;//页面传递过来的匹配关键字
    private Long catalog3Id;//三级分类id
    /**
     * sort=saleCount_asc/desc
     * sort=skuPrice_asc/desc
     * sort=hotScore_asc/desc
     */
    private String sort;//排序条件
    private Integer hasStock;//是否有货
    private String skuPrice;//价格区间
    private List<Long> brandId;//按照品牌进行查询，多选
    private List<String> attrs;//按照属性
    private Integer pageNum;

}
