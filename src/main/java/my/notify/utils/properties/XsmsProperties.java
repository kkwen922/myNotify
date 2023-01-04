package my.notify.utils.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : kevin Chang
 * @since : 2022/2/23
 */
@Data
@ConfigurationProperties(prefix = "xsms")
public class XsmsProperties {

    private Integer flag;
    private String name;
    private String url;
    private String mdn;
    private String uid;
    private String pwd;
    private String call;

}
