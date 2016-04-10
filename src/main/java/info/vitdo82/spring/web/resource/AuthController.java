package info.vitdo82.spring.web.resource;

import info.vitdo82.spring.web.config.SecurityConfig;
import info.vitdo82.spring.web.resource.dto.LoginModel;
import info.vitdo82.spring.web.resource.dto.TokenModel;
import info.vitdo82.spring.web.security.MyUsernamePasswordAuthToken;
import info.vitdo82.spring.web.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by vit on 3/4/16.
 */
@RestController
@RequestMapping("/api")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authorize(@Valid @RequestBody LoginModel loginModel,
                                       @ApiIgnore HttpServletRequest request,
                                       @ApiIgnore HttpServletResponse response) {

        MyUsernamePasswordAuthToken authenticationToken =
                new MyUsernamePasswordAuthToken(loginModel.getUserName(), loginModel.getPassword(), request);
        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            boolean rememberMe = (loginModel.isRememberMe() == null) ? false : loginModel.isRememberMe();
            String jwt = tokenProvider.createToken(authentication, rememberMe);
            response.addHeader(SecurityConfig.AUTHORIZATION_HEADER, jwt);
            return ResponseEntity.ok(new TokenModel(jwt));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
