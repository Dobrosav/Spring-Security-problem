package rs.yettel.roamingbarring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import rs.yettel.roamingbarring.model.ApiErrorResponse;
import rs.yettel.roamingbarring.model.wrapper.ApiResponseWrapper;

import java.util.List;
import java.util.StringJoiner;

import static rs.yettel.roamingbarring.utils.Constants.API_ID_CODE;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiGlobalErrorHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ApiGlobalErrorHandler.class.getName());

    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    protected ApiResponseWrapper handleUnauthorizedAccessException(UnauthorizedAccessException ex) {
        LOG.error("UnauthorizedAccessException occurred:{},code-{}", ex.getErrorType(), ex.getErrorType().getCode());
        return new ApiResponseWrapper(new ApiErrorResponse(HttpStatus.UNAUTHORIZED.value() + "." + API_ID_CODE + "." + ex.getErrorType().getCode(), ex.getErrorType().getMessagge()));
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ApiResponseWrapper handleServiceException(ServiceException ex) {
        LOG.error("ServiceException occurred:{},code-{}", ex.getErrorType(), ex.getErrorType().getCode());
        return new ApiResponseWrapper<>(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value() + "." + API_ID_CODE + "." + ex.getErrorType().getCode(), ex.getErrorType().getMessagge()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ApiResponseWrapper handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return new ApiResponseWrapper<>(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value() + "." + API_ID_CODE + "." + ErrorType.UNRESOLVED_ERROR.getCode(), ErrorType.UNRESOLVED_ERROR.getMessagge()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ApiResponseWrapper handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return new ApiResponseWrapper<>(new ApiErrorResponse(HttpStatus.BAD_REQUEST.value() + "." + API_ID_CODE + "." + ErrorType.NOT_SUPPORTED_HTTP_METHOD.getCode(), ErrorType.NOT_SUPPORTED_HTTP_METHOD.getMessagge() + ":" + ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ApiResponseWrapper handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ApiResponseWrapper(new ApiErrorResponse(HttpStatus.BAD_REQUEST.value() + "." + API_ID_CODE + "." + ErrorType.NOT_VALID_REQUEST_FORMAT.getCode(), ErrorType.NOT_VALID_REQUEST_FORMAT.getMessagge() + ":" + ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ApiResponseWrapper handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<FieldError> bindingResult = ex.getBindingResult().getFieldErrors();
        StringJoiner errorFields = new StringJoiner(",");
        for (FieldError error : bindingResult) {
            errorFields.add(error.getField());
        }
        return new ApiResponseWrapper<>(new ApiErrorResponse(HttpStatus.BAD_REQUEST.value() + "." + API_ID_CODE + "." + ErrorType.INVALID_ARGUMENT.getCode(), ErrorType.INVALID_ARGUMENT.getMessagge() + ":" + errorFields));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ApiResponseWrapper handleRuntimeException(RuntimeException ex) {
        LOG.error("Occurred unexpected error", ex);
        return new ApiResponseWrapper<>(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value() + "." + API_ID_CODE + "." + ErrorType.UNRESOLVED_ERROR.getCode(), ErrorType.UNRESOLVED_ERROR.getMessagge()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ApiResponseWrapper handleGeneralException(Exception ex) {
        LOG.error("Occurred unexpected error", ex);
        return new ApiResponseWrapper<>(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value() + "." + API_ID_CODE + "." + ErrorType.UNRESOLVED_ERROR.getCode(), ErrorType.UNRESOLVED_ERROR.getMessagge()));
    }


}
