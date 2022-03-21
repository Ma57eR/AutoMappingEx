package com.automapex;

import com.automapex.Exceptions.UserNotLoggedInException;
import com.automapex.Exceptions.ValidationException;
import com.automapex.services.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {


    private ExecutorService executorService;

    @Autowired
    public ConsoleRunner(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scan = new Scanner(System.in);

        String command = scan.nextLine();

        String result;
        try {
            result = executorService.execute(command);
        } catch (ValidationException | UserNotLoggedInException ex) {
            result = ex.getMessage();
        }


        System.out.println(result);
    }
}
