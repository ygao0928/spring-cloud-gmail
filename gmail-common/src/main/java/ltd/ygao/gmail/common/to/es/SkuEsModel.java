package ltd.ygao.gmail.common.to.es;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Kevin Gao
 * @version 1.0.0
 * @ClassName SkuEsModel.java
 * @date 2020/8/21 16:41
 * @Description
 * @Content: "properties": {
*/
@Data
public class SkuEsModel {
    private Long skuId;
    private Long spuId;
    private String skuTitle;
    private BigDecimal skuPrice;
    private String skuImg;
    private Long saleCount;
    private Boolean hasStock;
    private Long hotScore;
    private Long brandId;
    private Long catalogId;

    private String brandName;
    private String brandImg;
    private String catalogName;
    private List<Object> attrs;

    @Data
    public static class attrs {
        private Long attrId;
        private String attrName;
        private String attrValue;

    }
}
