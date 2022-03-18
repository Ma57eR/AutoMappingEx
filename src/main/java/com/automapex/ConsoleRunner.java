package com.automapex;

import com.automapex.Exceptions.ValidationException;
import com.automapex.entities.users.RegisterDTO;
import com.automapex.entities.users.User;
import com.automapex.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final String REGISTER_USER_COMMAND = "RegisterUser";
    private final String LOGIN_USER_COMMAND = "LoginUser";
    private final String LOGOUT_USER_COMMAND = "Logout";
    private final UserService userService;

    @Autowired
    public ConsoleRunner(UserService userService) {
        this.userService = userService;
    }

    private String execute(String commandLine) {
        String[] commandParts = commandLine.split("\\|");

        String commandName = commandParts[0];

        String commandOutput = switch (commandName) {
            case REGISTER_USER_COMMAND -> {
                RegisterDTO registerData = new RegisterDTO(commandParts);
                User user = userService.register(registerData);

                yield String.format("%s was registered", user.getFullName());
            }
//            case LOGIN_USER_COMMAND -> userService.login();
//            case LOGOUT_USER_COMMAND -> userService.logout();
            default -> "unknown command";
        };

        return commandOutput;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scan = new Scanner(System.in);

        String command = scan.nextLine();

        String result;
        try {
            result = execute(command);
        } catch (ValidationException ex) {
            result = ex.getMessage();
        }


        System.out.println(result);
    }
}
