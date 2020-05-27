package ltd.ygao.thirdparty;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication

public class GmailThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmailThirdPartyApplication.class, args);
    }



}
