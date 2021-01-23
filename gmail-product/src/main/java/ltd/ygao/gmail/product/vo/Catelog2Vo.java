package ltd.ygao.gmail.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Kevin Gao
 * @version 1.0.0
 * @ClassName Catelog2Vo.java
 * @date 2021/1/14 10:30
 * @Description
 * @Content:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Catelog2Vo {
    private String catalog1Id;//1级
    private List<Catelog3Vo> catalog3List;//三级
    private String id;
    private String name;

    /**
     * 三级
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Catelog3Vo{
        private String catalog2Id;//1级
        private String id;
        private String name;
    }

}
