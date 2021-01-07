package ltd.ygao.search.service;

import ltd.ygao.gmail.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * @author Kevin Gao
 * @version 1.0.0
 * @ClassName ProductSaveService.java
 * @date 2020/8/25 0:15
 * @Description
 * @Content:
 */
public interface ProductSaveService {
    Boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;

}
