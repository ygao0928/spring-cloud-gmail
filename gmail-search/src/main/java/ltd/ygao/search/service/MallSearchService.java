package ltd.ygao.search.service;

import ltd.ygao.search.vo.SearchParam;
import ltd.ygao.search.vo.SearchResult;

/**
 * @author Kevin Gao
 * @version 1.0.0
 * @ClassName MallSearchService.java
 * @date 2021/1/26 20:38
 * @Description
 * @Content:
 */
public interface MallSearchService {
    SearchResult search(SearchParam param);
}
