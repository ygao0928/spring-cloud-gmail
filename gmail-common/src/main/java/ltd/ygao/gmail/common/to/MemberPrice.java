package ltd.ygao.gmail.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Kevin Gao
 * @version 1.0
 * @ClassName MemberPrice.java
 * @Description TODO
 * @date 2020/8/18 17:29
 * @Content:
 */
@Data
public class MemberPrice {
    private Long id;
    private String name;
    private BigDecimal price;
}
