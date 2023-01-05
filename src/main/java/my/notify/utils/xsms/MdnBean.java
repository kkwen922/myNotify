package my.notify.utils.xsms;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@Data
@ToString
public class MdnBean {


    ArrayList<String> MSISDN;

    public ArrayList<String> getMSISDN() {
        return MSISDN;
    }

    public void setMSISDN(ArrayList<String> msisdn) {
        this.MSISDN = msisdn;
    }
}
