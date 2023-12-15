package rs.yettel.roamingbarring.exception;


public class P2PGroupNotFoundException extends ServiceException {
    public P2PGroupNotFoundException(ErrorType errorType) {
        super(errorType);
    }

}
