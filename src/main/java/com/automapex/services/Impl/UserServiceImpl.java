package com.automapex.services.Impl;

import com.automapex.Exceptions.UserNotLoggedInException;
import com.automapex.entities.users.LoginDTO;
import com.automapex.entities.users.RegisterDTO;
import com.automapex.entities.users.User;
import com.automapex.repositories.UserRepository;
import com.automapex.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

        private User currentUser;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(RegisterDTO registerData) {

        ModelMapper mapper = new ModelMapper();
        User toRegister = mapper.map(registerData, User.class);

        long userCount = this.userRepository.count();

        if (userCount == 0) {
            toRegister.setAdmin(true);
        }

        return this.userRepository.save(toRegister);
    }


    @Override
    public Optional<User> login(LoginDTO loginData) {


        Optional<User> user = this.userRepository.findByEmailAndPassword(
                loginData.getEmail(), loginData.getPassword());

        user.ifPresent(value -> this.currentUser = value);

        return user;
    }

    @Override
    public User getLoggedUser() {
        if (this.currentUser == null) {
            throw new UserNotLoggedInException();
        }
        return this.currentUser;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public void logout() {
        if (currentUser != null) {
            this.currentUser = null;
        }
    }
}
