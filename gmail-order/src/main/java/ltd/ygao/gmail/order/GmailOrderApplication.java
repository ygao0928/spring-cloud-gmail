package ltd.ygao.gmail.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@MapperScan("ltd.ygao.gmail.order.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class GmailOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmailOrderApplication.class, args);
    }

}
