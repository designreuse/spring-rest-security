package info.vitdo82.spring.web.resource.dto;

import java.io.Serializable;

/**
 * Created by vit on 3/4/16.
 */
public class LoginModel implements Serializable {

    private String userName;

    private String password;

    private Boolean rememberMe;

    public LoginModel() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
