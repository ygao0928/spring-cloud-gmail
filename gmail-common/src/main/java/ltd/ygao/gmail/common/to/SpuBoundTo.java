package ltd.ygao.gmail.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Kevin Gao
 * @version 1.0
 * @ClassName SpuBoundTo.java
 * @Description TODO
 * @date 2020/8/18 17:14
 * @Content:
 */
@Data
public class SpuBoundTo {
    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}
