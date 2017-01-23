package eu.corstjens.springbootwsexemple.services;

import eu.corstjens.springbootwsexemple.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by koencorstjens on 20/01/17.
 */
@Service
public class UserService {

    public User findById(String id) {

        return new User();
    }
}
