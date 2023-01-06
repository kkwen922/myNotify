package my.notify.schedule;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import my.notify.nms.model.NmsNotificationLog;
import my.notify.nms.model.SendMessageParam;
import my.notify.nms.service.NmsNotificationLogService;
import my.notify.utils.teamplus.TeamPlusUtil;
import my.notify.utils.xsms.XsmsUtil;
import my.notify.utils.properties.MailProperties;
import my.notify.utils.properties.TeamplusProperties;
import my.notify.utils.properties.XsmsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;


/**
 * @author : kevin Chang
 * @since : 2022/2/23
 */

@Slf4j
@Component
public class SendAlertScheduleTask {

    @Autowired
    TeamplusProperties teamPlusProperties;

    @Autowired
    MailProperties mailProperties;

    @Autowired
    XsmsProperties xsmsProperties;

    @Autowired
    NmsNotificationLogService nmsNotificationLogService;

    /**
     * Send2EmailTask
     * @param sendMessageParam
     */
    public void sendemailTask(SendMessageParam sendMessageParam) {

        SnsCategory snsType = SnsCategory.eMail;

        String host = mailProperties.getHost();
        Integer port = mailProperties.getPort();
        String account = mailProperties.getUserAddress();
        String password = mailProperties.getUserPwd();
        String displayName = mailProperties.getUseDisplayname();
        String textContent = sendMessageParam.getMessage();
        String send2Target = sendMessageParam.getTarget();

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", String.valueOf(true));
        properties.put("mail.smtp.starttls.enable", String.valueOf(true));

//        Session session = Session.getInstance(properties, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(account, password);
//            }
//        });
//
//        try {
//
//            Message message = new MimeMessage(session);
//
//            String mineContent = "<html><body><h3>"+text_content+"</h3></body></html>";
//
//            message.setSubject("Notification Message");
//            message.setContent(mineContent, "text/html;charset=UTF-8");
//            message.setFrom(new InternetAddress(account, display_name));
//
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(send2Target));
//
//            NmsNotificationLog newLog = new NmsNotificationLog();
//            newLog.setStatus(0); //初始狀態：未發送
//            newLog.setContext(text_content);
//            newLog.setName(snsType.name());//teamPlus
//            newLog.setCategoryId(snsType.ordinal());
//            newLog.setSendTo(send2Target);
//            nmsNotificationLogService.create(newLog);
//
//            int sendResult = 2;//預設失敗
//            Transport.send(message);
//
//            nmsNotificationLogService.updateStatus(newLog.getId(), 1);
//
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//        }

    }


    public void sendteamplusTask(SendMessageParam sendMessageParam) {

        SnsCategory snsType = SnsCategory.teamPlus;
        String url = teamPlusProperties.getUrl();
        String ask = teamPlusProperties.getAsk();
        String chSn = sendMessageParam.getTarget();
        String key = teamPlusProperties.getKey();
        Integer type = teamPlusProperties.getType();
        String textContent = sendMessageParam.getMessage();

        String teamplusUrl = url + ask + "&ch_sn=" + chSn + "&api_key=" +
                key + "&content_type=" + type + "&text_content=" + textContent;

        log.info("==>"+teamplusUrl);

        NmsNotificationLog newLog = new NmsNotificationLog();
        //初始狀態：未發送
        newLog.setStatus(0);
        newLog.setContext(textContent);
        //teamPlus
        newLog.setName(snsType.name());
        newLog.setCategoryId(snsType.ordinal());
        newLog.setSendTo("Channel:" + chSn);

        nmsNotificationLogService.create(newLog);

        TeamPlusUtil teamPlusUtil = new TeamPlusUtil();

        boolean result = false;
        result = teamPlusUtil.send2Channels(teamplusUrl);

        //預設發送失敗
        int sendResult = 2;
        if (result) {
            //發送成功
            sendResult = 1;
        }
        nmsNotificationLogService.updateStatus(newLog.getId(), sendResult);

    }


    /**
     * Send2XSMSTask
     * @param sendMessageParam
     */
    public void sendxsmsTask(SendMessageParam sendMessageParam) throws JsonProcessingException {

        SnsCategory snsType = SnsCategory.SMS;

        String send2Target = sendMessageParam.getTarget();
        String textContent = sendMessageParam.getMessage();

        String url = xsmsProperties.getUrl();
        String mdn = xsmsProperties.getMdn();
        String uid = xsmsProperties.getUid();
        String pwd = xsmsProperties.getPwd();
        String call = xsmsProperties.getCall();

        NmsNotificationLog newLog = new NmsNotificationLog();
        //初始狀態：未發送
        newLog.setStatus(0);
        newLog.setContext(textContent);
        //SMS
        newLog.setName(snsType.name());
        newLog.setCategoryId(snsType.ordinal());
        newLog.setSendTo(send2Target);
        newLog.setContext(textContent);

        nmsNotificationLogService.create(newLog);

        XsmsUtil xsmsUtil = new XsmsUtil();
        boolean result = xsmsUtil.send2Xsms(send2Target, url, mdn, uid, pwd, call, textContent);

        log.info("sms result: {}",result);

        //預設發送失敗
        int sendResult = 2;
        if (result) {
            //發送成功
            sendResult = 1;
        }
        nmsNotificationLogService.updateStatus(newLog.getId(), sendResult);
    }
}
