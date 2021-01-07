package ltd.ygao.gmail.common.to;

import lombok.Data;

/**
 * @author Kevin Gao
 * @version 1.0.0
 * @ClassName SkuHasStockVo.java
 * @date 2020/8/23 13:45
 * @Description
 * @Content:
 */
@Data
public class SkuHasStockVo {
    private Long skuId;
    private Boolean hasStock;
}
