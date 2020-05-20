package ltd.ygao.gmail.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@MapperScan("ltd.ygao.gmail.coupon.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class GmailCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmailCouponApplication.class, args);
    }

}
