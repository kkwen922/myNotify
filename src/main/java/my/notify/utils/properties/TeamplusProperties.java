package my.notify.utils.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : kevin Chang
 * @since : 2022/2/23
 */

@Data
@ConfigurationProperties(prefix = "teamplus")
public class TeamplusProperties {

    private Integer flag;
    private String name;
    private String url;
    private String ask;
    private Integer chSn;
    private String key;
    private Integer type;

}
