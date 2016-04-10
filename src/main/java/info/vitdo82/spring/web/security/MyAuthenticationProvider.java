package info.vitdo82.spring.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vit on 3/30/16.
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        HttpServletRequest request = ((MyUsernamePasswordAuthToken) authentication).getHttpServletRequest();

        try {
            UserDetails userCtx = null;

            if (authentication.getCredentials() == null || userCtx == null) {
                LOGGER.debug("No pre-authenticated credentials found in request.");
                throw new BadCredentialsException("No pre-authenticated credentials found in request.");
            }

            List<GrantedAuthority> grantedAuthorities = Arrays.asList(new SimpleGrantedAuthority("ADMIN"));

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
            authenticationToken.setDetails(userCtx);

            return authenticationToken;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new BadCredentialsException("No pre-authenticated credentials found in request.", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MyUsernamePasswordAuthToken.class.isAssignableFrom(authentication);
    }
}
