package ltd.ygao.gmail.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

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
 */
@MapperScan("ltd.ygao.gmail.product.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class GmailProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmailProductApplication.class, args);
    }

}
