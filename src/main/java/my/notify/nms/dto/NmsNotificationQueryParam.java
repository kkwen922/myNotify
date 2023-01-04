package my.notify.nms.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Notification 查询参数
 * @author : kevin Chang
 * @since : 2022/3/1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NmsNotificationQueryParam {

    /**
     * 發送結果
     */
    private Integer publishStatus;

    /**
     * 發送訊息模糊關鍵字
     */
    private String keyword;

}
