package ltd.ygao.gmail.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("ltd.ygao.gmail.ware.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class GmailWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmailWareApplication.class, args);
    }

}
