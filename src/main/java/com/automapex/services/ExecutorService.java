package com.automapex.services;

public interface ExecutorService {

     String REGISTER_USER_COMMAND = "RegisterUser";
     String LOGIN_USER_COMMAND = "LoginUser";
     String LOGOUT_USER_COMMAND = "Logout";


    String execute(String command);
}
