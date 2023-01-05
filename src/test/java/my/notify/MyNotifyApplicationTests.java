package my.notify;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import lombok.Data;
import my.notify.utils.xsms.MdnBean;
import my.notify.utils.xsms.Request;
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
    void kktest() throws JsonProcessingException {
        List<MdnBean> mdnBeans = new ArrayList<>();
        String bno = "0982158008,0982158007,0982158006,0982158005";
        String[] cArray = bno.split(",");
        for (String xmdn : cArray) {
            MdnBean mdnBean = new MdnBean();
//            mdnBean.setMSISDN(xmdn);
            mdnBeans.add(mdnBean);
        }

        XsmsBean xsmsBean = new XsmsBean();
        xsmsBean.setSubject("事件資訊");
        xsmsBean.setRetry("Y");
        xsmsBean.setAutoSplit("Y");
        xsmsBean.setCallback("09821233222");
        xsmsBean.setMessage("hello");
        xsmsBean.setMdnList(mdnBeans);


        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setDefaultUseWrapper(false);
        //美化输出
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String xml = xmlMapper.writeValueAsString(xsmsBean);

        System.out.println(xml);
    }
    @Test
    void xmlRequestTest() throws JsonProcessingException {

        /**
         * <Request>
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
         * </Request>
         */

        ArrayList<String> list = new ArrayList<String>();
        String bno = "0982158008,0982158007,0982158006,0982158005";
        String[] cArray = bno.split(",");
        for (String xmdn : cArray) {
            list.add(xmdn);
        }
        MdnBean mdnBean = new MdnBean();
        mdnBean.setMSISDN(list);

        Request res = new Request();
        res.setSubject("事件資訊");
        res.setRetry("Y");
        res.setAutoSplit("Y");
        res.setCallback("09821233222");
        res.setMessage("hello");
        res.setMDNList(mdnBean);

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setDefaultUseWrapper(false);
        String xml = xmlMapper.writeValueAsString(res);

        System.out.println(xml);

    }
    @Test
    void xmlTest() throws IOException {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Puppy", Boolean.TRUE);
        map.put("Apple", 2);
        map.put("Jet", "Li");
        Examples examples = new Examples();
        examples.setOverlyComplicated("yes");
        examples.setMap(map);

        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.putPOJO("Examples", examples);
        ObjectNode currentNode = rootNode.putObject("Single");
        currentNode.put("One", 1);

        mapper.writeValue(System.out, rootNode);

    }
    @Test
    void contextLoads() throws JsonProcessingException {

        String bno = "123123123,3213123213,12321312312,12312321321";
        String[] cArray = bno.split(",");
        List<MdnBean> vowels = new ArrayList<>();

        for (String xmdn : cArray) {
            MdnBean mdnBean = new MdnBean();
//            mdnBean.setMSISDN(xmdn);
            vowels.add(mdnBean);
        }
        System.out.println("DD==>"+vowels.toString());

        XmlMapper xmlMapper = new XmlMapper();
        Request res = new Request();
        res.setSubject("事件資訊");
        res.setRetry("Y");
        res.setAutoSplit("Y");
        res.setCallback("09821233222");
        res.setMessage("hello");
//        res.setMDNList(vowels);
        String xml = xmlMapper.writeValueAsString(res);

        System.out.println("KK=>"+ xml);
    }


    @Data
    class Examples implements JsonSerializable {

        @Override
        public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
            ToXmlGenerator toXmlGenerator = (ToXmlGenerator) gen;
            toXmlGenerator.writeStartObject();

            writeAttributes(toXmlGenerator);
            writeMap(toXmlGenerator);

            toXmlGenerator.writeEndObject();
        }

        private void writeAttributes(ToXmlGenerator gen) throws IOException {
            if (overlyComplicated != null) {
                gen.setNextIsAttribute(true);
                gen.writeFieldName("overlyComplicated");
                gen.writeString(overlyComplicated);
                gen.setNextIsAttribute(false);
            }
        }

        private void writeMap(ToXmlGenerator toXmlGenerator) throws IOException {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                toXmlGenerator.writeObjectField(entry.getKey(), entry.getValue());
            }
        }

        @Override
        public void serializeWithType(JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
            serialize(gen, serializers);
        }

        private String overlyComplicated;
        private Map<String, Object> map;

        // getters, setters, toString
    }

}
