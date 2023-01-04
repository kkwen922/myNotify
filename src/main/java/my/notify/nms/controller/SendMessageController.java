package my.notify.nms.controller;

import lombok.extern.slf4j.Slf4j;
import my.notify.common.api.CommonResult;
import my.notify.nms.model.NmsNotificationCategory;
import my.notify.nms.model.SendMessageParam;
import my.notify.nms.service.NmsNotificationLogCategoryService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : kevin Chang
 * @since : 2022/3/1
 *
 * 接收發送訊息功能
 */
@RestController
@Slf4j
@RequestMapping("/notify")
public class SendMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    NmsNotificationLogCategoryService nmsNotificationLogCategoryService;

    /**
     * 透過API發送Notify
     * @param snsType
     * @param message
     * @param target
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult putMessage(
            @RequestParam(name = "snsType") Integer snsType,
            @RequestParam(name = "message") String message,
            @RequestParam(name = "target") String target) {

        log.info("send Message Param: {}, {}, {}", snsType, message, target);

        if (snsType > 0 && !target.isEmpty() && !message.isEmpty()) {

            NmsNotificationCategory snsCate = nmsNotificationLogCategoryService.getById(snsType);

            if (snsCate != null && snsCate.getStatus() == 1) {

                SendMessageParam sendMessageParam = new SendMessageParam(snsType, message, target);
                rabbitTemplate.convertAndSend("tpu.queue", sendMessageParam);
                return CommonResult.success(sendMessageParam);

            } else {
                return CommonResult.failed("無此發送類別/或已停用");
            }
        } else {
            return CommonResult.validateFailed();
        }
    }

}
