package my.notify.utils.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : kevin Chang
 * @since : 2022/3/3
 */
@Data
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

    private String host;
    private Integer port;
    private String authEnabled;
    private String starttlsEnabled;
    private String userAddress;
    private String userPwd;
    private String useDisplayname;

}
