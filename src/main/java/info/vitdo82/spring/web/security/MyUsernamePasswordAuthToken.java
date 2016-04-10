package info.vitdo82.spring.web.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by vit on 3/30/16.
 */
public class MyUsernamePasswordAuthToken extends UsernamePasswordAuthenticationToken {

    private HttpServletRequest httpServletRequest;

    public MyUsernamePasswordAuthToken(Object principal, Object credentials, HttpServletRequest httpServletRequest) {
        super(principal, credentials);
        this.httpServletRequest = httpServletRequest;
    }

    public MyUsernamePasswordAuthToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, HttpServletRequest httpServletRequest) {
        super(principal, credentials, authorities);
        this.httpServletRequest = httpServletRequest;
    }

    public MyUsernamePasswordAuthToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public MyUsernamePasswordAuthToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }
}
