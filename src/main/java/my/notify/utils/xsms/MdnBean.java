package my.notify.utils.xsms;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@Data
public class MdnBean {

    @JacksonXmlProperty(localName = "Subject")
    ArrayList<String> MSISDN;

//    public ArrayList<String> getMSISDN() {
//        return MSISDN;
//    }
//
//    public void setMSISDN(ArrayList<String> msisdn) {
//        MSISDN = msisdn;
//    }
}
