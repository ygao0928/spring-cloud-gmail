package ltd.ygao.gmail.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "ltd.ygao.gmail.member.feign")
@MapperScan("ltd.ygao.gmail.member.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class GmailMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmailMemberApplication.class, args);
    }

}
