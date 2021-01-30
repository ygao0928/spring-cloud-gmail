package ltd.ygao.search.vo;

import lombok.Data;
import ltd.ygao.gmail.common.to.es.SkuEsModel;

import java.util.List;

/**
 * @author Kevin Gao
 * @version 1.0.0
 * @ClassName SearchResponse.java
 * @date 2021/1/27 22:44
 * @Description
 * @Content:
 */
@Data
public class SearchResult {
    //查询到所有的产品信息
    private List<SkuEsModel> products;
    /**
     * 分页信息
     */
    private Integer pageNum;//当前页码
    private Long total;//总记录数
    private Integer totalPages;//总页码
    private List<BrandVo> brands;//当前查询到的结果
    private List<AttrVo> attrs;
    private List<CatalogVo> catalogs;

    @Data
    public static class BrandVo {
        private Long brandId;
        private String brandName;
        private String brandImg;
    }

    @Data
    public static class AttrVo {
        private Long attrId;
        private String attrName;
        private List<String> attrValue;
    }
    @Data
    public static class CatalogVo {
        private Long catalogId;
        private String catalogName;
    }

}
