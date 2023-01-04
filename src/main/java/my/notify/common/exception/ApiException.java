package my.notify.common.exception;


import my.notify.common.api.IErrorCode;

/**
 * @author kevinchang
 *
 * 自定義API異常
 */
public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    /**
     * ApiException
     * @param errorCode
     */
    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
