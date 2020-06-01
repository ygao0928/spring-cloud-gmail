package ltd.ygao.gmail.product.vo;

import lombok.Data;

/**
 * @author Kevin
 * @version 1.0
 * @date 2020/5/28 0:24
 */
@Data
public class AttrRespVo extends AttrVo {
    private String catelogName;
    private String groupName;
    private Long[] catelogPath;
}
