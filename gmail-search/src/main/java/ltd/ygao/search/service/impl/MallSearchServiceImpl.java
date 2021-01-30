package ltd.ygao.search.service.impl;

import lombok.extern.slf4j.Slf4j;
import ltd.ygao.search.config.ElasticConfig;
import ltd.ygao.search.constant.EsConstant;
import ltd.ygao.search.service.MallSearchService;
import ltd.ygao.search.vo.SearchParam;
import ltd.ygao.search.vo.SearchResult;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Kevin Gao
 * @version 1.0.0
 * @ClassName MallSearchServiceImpl.java
 * @date 2021/1/26 20:38
 * @Description
 * @Content:
 */
@Service
@Slf4j
public class MallSearchServiceImpl implements MallSearchService {
    @Autowired
    private RestHighLevelClient client;

    @Override
    public SearchResult search(SearchParam param) {
        SearchResult result = null;
        //动态构建出需要查询的DSL语句
        //1 准备检索请求
        SearchRequest searchRequest = buildSearchRequest(SearchParam param);
        try {
            // 2执行检索请求
            SearchResponse response = client.search(searchRequest, ElasticConfig.COMMON_OPTIONS);
            //3分析响应数据封装成我们需要的格式
            result = buildSearchResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 准备检索请求
     * 功能：模糊匹配 过滤(按照属性 ，分类，品牌，价格区间，库存)，排序 分页 高亮 聚合分析
     *
     * @return
     */
    private SearchRequest buildSearchRequest(SearchParam param) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();//构建DSL语句
        /**
         * 模糊匹配，过滤（按照属性，分类，品牌，价格区间，库存）
         */
        //1, 构建bool  - query
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //1.1 must -- 模糊匹配
        if (!StringUtils.isEmpty(param.getKeyword())) {
            boolQuery.must(QueryBuilders.matchQuery("skuTitle", param.getKeyword()));
        }
        //1.2 bool  - filter
        if (param.getCatalog3Id() != null) {
            boolQuery.filter(QueryBuilders.termQuery("catalogId", param.getCatalog3Id()));
        }


        sourceBuilder.query(boolQuery);
        /**
         * 排序，分页，高亮
         */

        /**
         * 聚合分析
         */
        SearchRequest searchRequest = new SearchRequest(new String[]{EsConstant.PRODUCT_INDEX}, sourceBuilder);
        return searchRequest;
    }

    /**
     * 构建结果数据
     *
     * @param response
     * @return
     */
    private SearchResult buildSearchResponse(SearchResponse response) {
        return null;
    }


}
