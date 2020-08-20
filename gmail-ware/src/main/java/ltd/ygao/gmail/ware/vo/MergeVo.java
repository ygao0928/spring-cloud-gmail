package ltd.ygao.gmail.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Kevin Gao
 * @version 1.0
 * @ClassName MergeVo.java
 * @Description TODO
 * @date 2020/8/19 15:42
 * @Content:
 */
@Data
public class MergeVo {
    private Long purchaseId;
    private List<Long> items;
}
