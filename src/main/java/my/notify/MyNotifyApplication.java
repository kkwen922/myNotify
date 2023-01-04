package my.notify;

import my.notify.utils.properties.MailProperties;
import my.notify.utils.properties.TeamplusProperties;
import my.notify.utils.properties.XsmsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author kevinchang
 */
@SpringBootApplication
@EnableConfigurationProperties({TeamplusProperties.class, XsmsProperties.class, MailProperties.class})
public class MyNotifyApplication {


    public static void main(String[] args) {
        SpringApplication.run(MyNotifyApplication.class, args);
    }

}
