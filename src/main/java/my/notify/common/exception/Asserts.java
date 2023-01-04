package my.notify.common.exception;


import my.notify.common.api.IErrorCode;

/**
 * @author kevinchang
 *
 * 断言處理類，用於抛出各種API異常
 */
public class Asserts {

    /**
     * fail
     * @param message
     */
   public static void fail(String message) {
        throw new ApiException(message);
    }

    /**
     * fail
     * @param errorCode
     */
    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
