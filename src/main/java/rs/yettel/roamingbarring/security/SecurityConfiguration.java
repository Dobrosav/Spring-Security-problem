package rs.yettel.roamingbarring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import rs.yettel.roamingbarring.exception.ErrorType;
import rs.yettel.roamingbarring.exception.FilterChainExceptionHandler;
import rs.yettel.roamingbarring.exception.UnauthorizedAccessException;

import java.util.Set;

@Configuration
@EnableWebSecurity
@Order(4)
public class SecurityConfiguration {

    private final FilterChainExceptionHandler filterChainExceptionHandler;

    @Value("${roaming-barring.rest.token.whitelisted.values}")
    private Set<String> validTokens;

    @Value("${roaming-barring.rest.token.name}")
    private String authorizationHeader;

    @Autowired
    public SecurityConfiguration(FilterChainExceptionHandler filterChainExceptionHandler) {
        this.filterChainExceptionHandler = filterChainExceptionHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        ApiKeyAuthFilter whitelistedTokensFilter = new ApiKeyAuthFilter(authorizationHeader);
        whitelistedTokensFilter.setAuthenticationManager(authentication -> {
            String token = authentication.getPrincipal().toString().substring("Bearer ".length());
            if (!validTokens.contains(token)) {
                throw new UnauthorizedAccessException(ErrorType.INVALID_TOKEN);
            }
            authentication.setAuthenticated(true);
            return authentication;
        });

        httpSecurity.csrf(csrf->csrf.disable()).
                authorizeRequests(auth->auth.requestMatchers("/roaming-barring/**").permitAll())
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).addFilter(whitelistedTokensFilter);

        return httpSecurity.build();
    }

}
