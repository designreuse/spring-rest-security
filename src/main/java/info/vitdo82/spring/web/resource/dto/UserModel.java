package info.vitdo82.spring.web.resource.dto;

/**
 * Created by vit on 3/4/16.
 */
public class UserModel {

    private String userName;
    private String email;

    public UserModel() {
    }

    public UserModel(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
