package com.automapex.services;

import com.automapex.entities.users.RegisterDTO;
import com.automapex.entities.users.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User register(RegisterDTO registerData);

    User login();

    void logout();

}
