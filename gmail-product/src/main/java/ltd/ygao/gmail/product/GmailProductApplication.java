package ltd.ygao.gmail.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * JSR303 数据校验
 * 1)、给bean添加校验注解：并且可以定义自己的message提示
 * 2）、开启校验功能@valid
 * 3）、给校验的bean后跟紧一个BingingResult,就可以获取到检验的结果
 * 4）、分组校验
 * 5)、自定义校验
 *       1）、编写一个自定义的校验注解
 *       2）、编写一个自定义的校验器
 *       3）、关联自定义的校验器和自定义的校验注解
 *
 * 7.整合redisson
 *    1）引入依赖
 *    <dependency>
 *             <groupId>org.redisson</groupId>
 *             <artifactId>redisson</artifactId>
 *             <version>3.12.5</version>
 *         </dependency>
 *    2）配置
 *8.整合springCache
 *   1)引入依赖
 *   2、写配置
 *    （1）自动配置啥
 *       CacheAutoConfiguration 会导入RedisCacheConfiguration
 *       自动配置好缓存管理器RedisCacheManager
 *     (2) 配置使用redis作为缓存
 *    （3）测试
 *           @Cacheable:触发将数据保存到缓存的操作
 *           @CacheEvict:将数据删除
 *           @CachePut:不影响方法执行，更新缓存
 *           @Caching:组合以上多个操作
 *           @CacheConfig:在类级别共享缓存的相同配置
 *           1）开启缓存功能:@EnableCaching
 *           2) 使用注解
 */
@MapperScan("ltd.ygao.gmail.product.dao")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "ltd.ygao.gmail.product.feign")
public class GmailProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmailProductApplication.class, args);
    }

}
