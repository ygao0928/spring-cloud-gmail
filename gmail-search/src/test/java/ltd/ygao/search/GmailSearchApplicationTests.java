package ltd.ygao.search;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.directory.SearchResult;
import java.io.IOException;

import static ltd.ygao.search.config.ElasticConfig.COMMON_OPTIONS;

@SpringBootTest
class GmailSearchApplicationTests {

    @Autowired
    private RestHighLevelClient client;

    /**
     * ES存储数据
     */
    @Test
    public void indexData() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
        User user = new User();
        user.setUserName("高勇");
        user.setAge(26);
        user.setGender("男");
        String jsonString = JSON.toJSONString(user);
        indexRequest.source(jsonString, XContentType.JSON);
        IndexResponse index = client.index(indexRequest, COMMON_OPTIONS);
        System.out.println(index);
    }

    /**
     * 检索
     * @throws IOException
     */
    @Test
    public void searchData() throws IOException {
        //创建检索请求
        SearchRequest searchRequest = new SearchRequest();
        //指定索引
        searchRequest.indices("bank");
        //指定DSL，检索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //1.1)
       // searchSourceBuilder.query();
       // searchSourceBuilder.from(10);
       // searchSourceBuilder.size(20);
       // searchSourceBuilder.aggregation();
        searchSourceBuilder.query(QueryBuilders.matchQuery("address","Kings"));
        //1.2)按照年龄
        TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age").size(10);
        searchSourceBuilder.aggregation(ageAgg);
        //1.3)按照平均薪资
        AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");
        searchSourceBuilder.aggregation(balanceAvg);
        searchRequest.source(searchSourceBuilder);

        //执行
        SearchResponse searchResponse = client.search(searchRequest, COMMON_OPTIONS);
        //分析
        System.out.println(searchResponse.toString());
        //获取所有的查到的数据
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    @Data
    class User {
        private String userName;
        private Integer age;
        private String gender;
    }

    @Test
    public void contextLoads() {
        System.out.println(client);
    }

}
