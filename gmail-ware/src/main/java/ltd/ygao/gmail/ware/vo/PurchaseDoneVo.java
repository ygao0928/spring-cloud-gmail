package ltd.ygao.gmail.ware.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Kevin Gao
 * @version 1.0
 * @ClassName PurchaseDoneVo.java
 * @Description TODO
 * @date 2020/8/19 17:09
 * @Content:
 */
@Data
public class PurchaseDoneVo {
    @NotNull
    private Long id;
    private List<PurchaseItemDoneVo> items;
}
