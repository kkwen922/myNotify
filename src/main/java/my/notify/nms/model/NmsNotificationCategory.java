package my.notify.nms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author : kevin Chang
 * @since : 2022/2/24
 * 訊息發送紀錄資訊
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("nms_notification_category")
public class NmsNotificationCategory {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Date createTime;

    /**
     * 發送方式
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 啟動狀態
     */
    private Integer status;

    /**
     * 備註
     */
    private String note;
}
