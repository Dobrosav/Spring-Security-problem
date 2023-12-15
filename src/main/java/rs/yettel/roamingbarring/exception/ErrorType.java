package rs.yettel.roamingbarring.exception;

public enum ErrorType {

    INVALID_ARGUMENT("001", "Invalid fields in the request"),
    NOT_SUPPORTED_HTTP_METHOD("002", "Not supported http method"),
    NOT_VALID_REQUEST_FORMAT("003", "Not valid request format"),
    UNAUTHORIZED_ACCESS("004", "Missing Authorization header"),
    INVALID_TOKEN("005", "Invalid access token"),

    UNRESOLVED_ERROR("050", "General error"),
    SQL_ERROR("051", "Occurred SQL error"),
    SEND_SMS_ERROR("054", "Unexpected error while sending sms"),
    SMS_NOT_SENT("055", "Sms not sent"),
    DATABASE_NOT_AVAILABLE("053", "Database not available");
    private final String code;
    private final String messagge;

    ErrorType(String code, String messagge) {
        this.code = code;
        this.messagge = messagge;
    }

    public String getCode() {
        return code;
    }

    public String getMessagge() {
        return messagge;
    }

}
