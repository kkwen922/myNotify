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
 *
 * <P>
 *     訊息發送紀錄資訊
 * </P>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("nms_notifyication_log")
public class NmsNotificationLog {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 創建時間
     */
    private Date createTime;

    /**
     * 回覆結果時間
     */
    private Date responseTime;

    /**
     * value = "發送狀態 0->未發送；1->發送成功;2->發送失敗"
     */
    private Integer status;

    /**
     * 發送內容
     */
    private String context;

    /**
     * value = "發送方式 1->TeamPlus; 2->SMS; 3->eMail"
     */
    private String name;

    /**
     * value = "發送方式 1->TeamPlus; 2->SMS; 3->eMail"
     */
    private Integer categoryId;

    /**
     * value = "發送對象，例如：門號/Email或群組代碼"
     */
    private String  sendTo;
}
