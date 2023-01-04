package my.notify.nms.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author : kevin Chang
 * @since : 2022/3/3
 *
 * 發送方式參數
 */
@Getter
@Setter
public class NmsNotificationCategoryParam {

    @NotEmpty
    private String name;


    private String note;


    private Integer sort;


    private Integer status;
}
