package ltd.ygao.gmail.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("ltd.ygao.gmail.product.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class GmailProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmailProductApplication.class, args);
    }

}
