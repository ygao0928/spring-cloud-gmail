package ltd.ygao.gmail.ware.vo;

import lombok.Data;

/**
 * @author Kevin Gao
 * @version 1.0
 * @ClassName PurchaseItemDoneVo.java
 * @Description TODO
 * @date 2020/8/19 17:10
 * @Content:
 */
@Data
public class PurchaseItemDoneVo {
    private Long itemId;
    private Integer status;
    private String reason;
}
