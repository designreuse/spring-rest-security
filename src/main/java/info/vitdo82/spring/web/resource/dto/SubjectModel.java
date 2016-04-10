package info.vitdo82.spring.web.resource.dto;

import java.io.Serializable;

/**
 * Created by vit on 3/10/16.
 */
public class SubjectModel implements Serializable {

    private String id;
    private String handle;
    private String name;
    private String content;
    private boolean verified;
    private String type;

    public SubjectModel() {
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
