package com.automapex.Exceptions;

public class ValidationException extends RuntimeException{

    public ValidationException(String reason) {
        super(reason);
    }
}
