package info.vitdo82.spring.web.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vit on 3/4/16.
 */
@RestController
@RequestMapping("/api/users")
public class UsersController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getUser() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
