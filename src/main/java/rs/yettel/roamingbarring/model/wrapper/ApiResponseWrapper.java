package rs.yettel.roamingbarring.model.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import rs.yettel.roamingbarring.model.ApiErrorResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseWrapper<T> {

    private T data;
    private ApiErrorResponse error;

    public ApiResponseWrapper(T data) {
        this.data = data;
    }

    public ApiResponseWrapper(ApiErrorResponse error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiErrorResponse getError() {
        return error;
    }

    public void setError(ApiErrorResponse error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ApiResponseWrapper{" +
                "data=" + data +
                ", error=" + error +
                '}';
    }
}
