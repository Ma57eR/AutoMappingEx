package com.automapex.services;

import com.automapex.entities.users.LoginDTO;
import com.automapex.entities.users.RegisterDTO;
import com.automapex.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExecutorServiceImpl implements ExecutorService {

    final UserService userService;

    @Autowired
    public ExecutorServiceImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(String commandLine) {
        String[] commandParts = commandLine.split("\\|");

        String commandName = commandParts[0];

        String commandOutput = switch (commandName) {
            case REGISTER_USER_COMMAND -> {
                RegisterDTO registerData = new RegisterDTO(commandParts);
                User user = userService.register(registerData);

                yield String.format("%s was registered", user.getFullName());
            }
            case LOGIN_USER_COMMAND -> {
                LoginDTO loginData = new LoginDTO(commandParts);

                Optional<User> user = userService.login(loginData);

                if (user.isPresent()) {

                    yield String.format("Successfully logged in %s",
                            user.get().getFullName());
                } else {
                    yield "Wrong credentials";
                }
            }
            case LOGOUT_USER_COMMAND -> logoutUser();
            default -> "unknown command";
        };

        return commandOutput;
    }

    private String logoutUser() {
        User loggedUser = this.userService.getLoggedUser();

        this.userService.logout();

        return String.format("User $s successfully logged out.",
                loggedUser.getFullName());
    }
}
