package my.notify.utils.xsms;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@JacksonXmlRootElement
//@JacksonXmlRootElement(namespace = "http://www.w3.org/TR/html4/school/", localName = "school:grade")
public class XsmsBean {

    String Subject;
    String Retry;
    String AutoSplit;
    String Callback;
    String Message;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty
    //@JacksonXmlProperty(localName = "student", namespace = "http://www.w3.org/TR/html4/school/")
    List<MdnBean> MdnList;


}
