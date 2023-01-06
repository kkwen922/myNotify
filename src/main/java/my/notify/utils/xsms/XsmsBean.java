package my.notify.utils.xsms;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author kevinchang
 */
@Data
@JacksonXmlRootElement(localName = "Request")
public class XsmsBean {

    @JacksonXmlProperty(localName = "Subject")
    private String Subject;

    @JacksonXmlProperty(localName = "Retry")
    private String Retry;

    @JacksonXmlProperty(localName = "AutoSplit")
    private String AutoSplit;

    @JacksonXmlProperty(localName = "Callback")
    private String Callback;
    @JacksonXmlProperty(localName = "Message")
    private String Message;
    @JacksonXmlProperty(localName = "MDNList")
    private MdnBean MDNList;

//
//    public void setSubject(String subject) {
//        Subject = subject;
//    }
//
//    public void setRetry(String retry) {
//        Retry = retry;
//    }
//
//    public void setAutoSplit(String autoSplit) {
//        AutoSplit = autoSplit;
//    }
//
//    public void setCallback(String callback) {
//        Callback = callback;
//    }
//
//    public void setMessage(String message) {
//        Message = message;
//    }
//
//    public String getSubject() {
//        return Subject;
//    }
//
//    public String getRetry() {
//        return Retry;
//    }
//
//    public String getAutoSplit() {
//        return AutoSplit;
//    }
//
//    public String getCallback() {
//        return Callback;
//    }
//
//    public String getMessage() {
//        return Message;
//    }
//
//    public MdnBean getMDNList() {
//        return MDNList;
//    }
//
//    public void setMDNList(MdnBean mdnBean) {
//        MDNList = mdnBean;
//    }
}


