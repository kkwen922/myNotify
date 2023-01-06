package my.notify;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import lombok.Data;
import my.notify.utils.xsms.MdnBean;
import my.notify.utils.xsms.XsmsBean;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MyNotifyApplicationTests {


    @Test
    void xmlRequestTest() throws JsonProcessingException {

        /**
         * <XsmsBean>
         * <message>hello</message>
         * <subject>事件資訊</subject>
         * <retry>Y</retry>
         * <autoSplit>Y</autoSplit>
         * <callback>09821233222</callback>
         * <mdnlist>
         * 	<msisdn>0982158008</msisdn>
         * 	<msisdn>0982158007</msisdn>
         * 	<msisdn>0982158006</msisdn>
         * 	<msisdn>0982158005</msisdn>
         * </mdnlist>
         * </XsmsBean>
         */

        ArrayList<String> list = new ArrayList<String>();
        String bno = "0982158008,0982158007,0982158006,0982158005";
        String[] cArray = bno.split(",");
        for (String xmdn : cArray) {
            list.add(xmdn);
        }
        System.out.println(list.size());
        MdnBean mdnBean = new MdnBean();
        mdnBean.setMSISDN(list);

        XsmsBean res = new XsmsBean();
        res.setSubject("事件資訊");
        res.setRetry("Y");
        res.setAutoSplit("Y");
        res.setCallback("09821233222");
        res.setMessage("hello");
        res.setMDNList(mdnBean);

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setDefaultUseWrapper(false);
        //xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        xmlMapper.enable(MapperFeature.USE_STD_BEAN_NAMING);
        String xml = xmlMapper.writeValueAsString(res);

        System.out.println(xml);

    }


}
