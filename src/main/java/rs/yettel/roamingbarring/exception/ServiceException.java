package rs.yettel.roamingbarring.exception;

public class ServiceException extends RuntimeException {

    private final ErrorType errorType;

    public ServiceException(ErrorType errorType) {
        super(errorType.getMessagge());
        this.errorType = errorType;
    }

    public ServiceException(Throwable cause, ErrorType errorType) {
        super(errorType.getMessagge(), cause);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
