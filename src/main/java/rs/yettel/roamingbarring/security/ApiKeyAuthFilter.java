package rs.yettel.roamingbarring.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import rs.yettel.roamingbarring.exception.ErrorType;
import rs.yettel.roamingbarring.exception.UnauthorizedAccessException;

public class ApiKeyAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    private final String principalRequestHeader;

    public ApiKeyAuthFilter(String principalRequestHeader) {
        this.principalRequestHeader = principalRequestHeader;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        if (request.getHeader(principalRequestHeader) == null) {
            throw new UnauthorizedAccessException(ErrorType.UNAUTHORIZED_ACCESS);
        }
        return request.getHeader(principalRequestHeader);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
        return "N/A";
    }

}
