package my.notify.utils.xsms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.ArrayList;

/**
 * @author : kevin Chang
 * @since : 2022/2/25
 */
@Slf4j
public class XsmsUtil {

    private final String XSMS_SUCCESS_CDOE="0";

    /**
     * send2Xsms
     * @param bno
     * @param smsUrl
     * @param smsMdn
     * @param smsUid
     * @param smsPwd
     * @param smsCall
     * @param message
     * @return
     */
    public boolean send2Xsms(String bno,String smsUrl,String smsMdn,String smsUid,String smsPwd,String smsCall,String message) throws JsonProcessingException {
        boolean result =false;

        String[] cArray = bno.split(",");
        String reqUrl = smsUrl + "?MDN=" + smsMdn + "&UID=" + smsUid + "&UPASS=" + smsPwd;
        log.info("sms url: {}"+reqUrl);

        ArrayList<String> list = new ArrayList<String>();
        for (String xmdn : cArray) {
            list.add(xmdn);
        }
        MdnBean mdnBean = new MdnBean();
        mdnBean.setMSISDN(list);

        XsmsBean res = new XsmsBean();
        res.setSubject("事件資訊");
        res.setRetry("Y");
        res.setAutoSplit("Y");
        res.setCallback(smsCall);
        res.setMessage(message);
        res.setMDNList(mdnBean);

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setDefaultUseWrapper(false);
        xmlMapper.enable(MapperFeature.USE_STD_BEAN_NAMING);
        String xml = xmlMapper.writeValueAsString(res);
        log.info("===>" + xml);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        reqUrl = reqUrl + "&Content=" + xml;

        HttpEntity<String> request = new HttpEntity<String>(xml, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(reqUrl, request, String.class);

        log.info("response.getStatusCode() ===>" + response.getStatusCode());
        log.info("response.getStatusCodeValue()===>" + response.getStatusCodeValue());

        String[] xsmsResult = response.toString().split(",");

        log.info("xsmsResult[1] ==>" + xsmsResult[1]);
        String xCode = "";
        XmlUtil xmlUtil = new XmlUtil();
        Document doc = xmlUtil.convertstring2Xmsdocument(xsmsResult[1]);

        Node n = doc.getFirstChild();
        for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling()) {
            log.info("NodeName ==>" + d.getNodeName() + ", TextContent==>" + d.getTextContent());
            if("Code".equalsIgnoreCase(d.getNodeName())){
                xCode = d.getTextContent();
            }
        }
        /**
         * 10:45:11.736 [org.springframework.amqp.rabbit.RabbitListenerEndpointContainer#0-1] INFO  my.notify.utils.xsms.XsmsUtil - response.getStatusCode() ===>200 OK
         * 10:45:11.737 [org.springframework.amqp.rabbit.RabbitListenerEndpointContainer#0-1] INFO  my.notify.utils.xsms.XsmsUtil - response.getStatusCodeValue()===>200
         * 10:45:11.738 [org.springframework.amqp.rabbit.RabbitListenerEndpointContainer#0-1] INFO  my.notify.utils.xsms.XsmsUtil - xsmsResult[1] ==><?xml version="1.0" encoding="UTF-8"?><Response><Reason>成功</Reason><Code>0</Code><MDN>0906180640</MDN><TaskID>20230106104511cf6501</TaskID><RtnDateTime>20230106104511</RtnDateTime></Response>
         * 10:45:11.742 [org.springframework.amqp.rabbit.RabbitListenerEndpointContainer#0-1] INFO  my.notify.utils.xsms.XsmsUtil - NodeName ==>Reason, TextContent==>成功
         * 10:45:11.745 [org.springframework.amqp.rabbit.RabbitListenerEndpointContainer#0-1] INFO  my.notify.utils.xsms.XsmsUtil - NodeName ==>Code, TextContent==>0
         * 10:45:11.745 [org.springframework.amqp.rabbit.RabbitListenerEndpointContainer#0-1] INFO  my.notify.utils.xsms.XsmsUtil - NodeName ==>MDN, TextContent==>0906180640
         * 10:45:11.745 [org.springframework.amqp.rabbit.RabbitListenerEndpointContainer#0-1] INFO  my.notify.utils.xsms.XsmsUtil - NodeName ==>TaskID, TextContent==>20230106104511cf6501
         * 10:45:11.745 [org.springframework.amqp.rabbit.RabbitListenerEndpointContainer#0-1] INFO  my.notify.utils.xsms.XsmsUtil - NodeName ==>RtnDateTime, TextContent==>20230106104511
         */

        if (XSMS_SUCCESS_CDOE.equals(xCode)) {
            result = true;
        }
        return result;
    }

}
