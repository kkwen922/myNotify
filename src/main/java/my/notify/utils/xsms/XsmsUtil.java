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
//        String xsmsMdn = "0906180640";
//        String xsmsCall ="0906180640";
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

        log.info("===>" + response.getStatusCode());
        log.info("===>" + response.getStatusCodeValue());

        String[] xsmsResult = response.toString().split(",");

        log.info("==>" + xsmsResult[1]);
        String xCode = "";
        XmlUtil xmlUtil = new XmlUtil();
        Document doc = xmlUtil.convertstring2Xmsdocument(xsmsResult[1]);

        Node n = doc.getFirstChild();
        for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling()) {
            log.info("==>" + d.getNodeName() + "," + d.getTextContent());
            if("Code".equalsIgnoreCase(d.getNodeName())){
                xCode = d.getTextContent();
            }
        }

        if (XSMS_SUCCESS_CDOE.equals(xCode)) {
            result = true;
        }
        return result;
    }

}
