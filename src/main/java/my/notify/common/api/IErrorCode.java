package my.notify.common.api;

/**
 * @author kevinchang
 *
 * 封装API的錯誤碼
 */
public interface IErrorCode {
    /**
     * getCode
     * @return
     */
    long getCode();

    /**
     * getMessage
     * @return
     */
    String getMessage();
}
