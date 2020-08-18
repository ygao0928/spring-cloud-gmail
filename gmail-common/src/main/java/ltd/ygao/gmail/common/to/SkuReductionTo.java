package ltd.ygao.gmail.common.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Kevin Gao
 * @version 1.0
 * @ClassName SkuReductionTo.java
 * @Description TODO
 * @date 2020/8/18 17:27
 * @Content:
 */
@Data
public class SkuReductionTo {
    private Long skuId;
    private int fullCount;
    private BigDecimal discount;
    private int countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private int priceStatus;
    private List<MemberPrice> memberPrice;
}
