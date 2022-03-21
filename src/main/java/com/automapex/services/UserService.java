package com.automapex.services;

import com.automapex.entities.users.LoginDTO;
import com.automapex.entities.users.RegisterDTO;
import com.automapex.entities.users.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    User register(RegisterDTO registerData);

    void logout();

    Optional<User> login(LoginDTO loginData);

    User getLoggedUser();
}
