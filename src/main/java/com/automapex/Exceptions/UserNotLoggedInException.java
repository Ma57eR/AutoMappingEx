package com.automapex.Exceptions;

public class UserNotLoggedInException extends RuntimeException {
    public UserNotLoggedInException() {
        super("Execute Login command first!");
    }

}
