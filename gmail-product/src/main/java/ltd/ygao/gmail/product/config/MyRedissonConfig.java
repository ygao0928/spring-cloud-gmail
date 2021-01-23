package ltd.ygao.gmail.product.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author Kevin Gao
 * @version 1.0.0
 * @ClassName MyRedissonConfig.java
 * @date 2021/1/22 19:14
 * @Description
 * @Content:
 */
@Configuration
public class MyRedissonConfig {
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() throws IOException {
        //创建配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.56.10:6379");
        //创建实列
        return Redisson.create(config);
    }
}
