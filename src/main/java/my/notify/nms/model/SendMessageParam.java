package my.notify.nms.model;

import lombok.Data;

/**
 * @author : kevin Chang
 * @since : 2022/3/2
 */

@Data
public class SendMessageParam {

    private Integer snsType;
    private String message;
    private String target;

    public SendMessageParam(){}

    public SendMessageParam(int snsType,String message,String target){
        this.snsType = snsType;
        this.message = message;
        this.target = target;
    }


}
