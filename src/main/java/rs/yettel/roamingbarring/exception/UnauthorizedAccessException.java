package rs.yettel.roamingbarring.exception;

public class UnauthorizedAccessException extends ServiceException {

    public UnauthorizedAccessException(ErrorType errorType) {
        super(errorType);
    }

    public UnauthorizedAccessException(Throwable cause, ErrorType errorType) {
        super(cause, errorType);
    }

}
