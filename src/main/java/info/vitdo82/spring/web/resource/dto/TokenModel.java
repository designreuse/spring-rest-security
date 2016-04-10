package info.vitdo82.spring.web.resource.dto;

import java.io.Serializable;

/**
 * Created by vit on 3/4/16.
 */
public class TokenModel implements Serializable {

    private String authorization;

    public TokenModel() {
    }

    public TokenModel(String authorization) {
        this.authorization = authorization;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
