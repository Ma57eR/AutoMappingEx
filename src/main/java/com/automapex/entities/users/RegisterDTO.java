package com.automapex.entities.users;

import com.automapex.Exceptions.ValidationException;

public class RegisterDTO {
    private String email;

    public String getEmail() {
        return email;
    }

    private String password;
    private String confirmPassword;
    private String fullName;

    /**
     * Validate the data for registering a user
     *
     * email must be
     * Pass must be
     * command[0] contains name, and it's not needed
     * @param commandParts all data read from console
     */

    public RegisterDTO(String[] commandParts) {
        this.email = commandParts[1];
        this.password = commandParts[2];
        this.confirmPassword = commandParts[3];
        this.fullName = commandParts[4];

        this.validate();
    }


    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    private void validate() {
        int indexOfAt = email.indexOf("@");
        int indexOfDot = email.lastIndexOf(".");
        if (indexOfAt < 0 || indexOfDot < 0 || indexOfAt > indexOfDot) {
            throw new ValidationException("Email must contains @ and .");
        }

        //TODO: Validate password
        if (!password.equals(confirmPassword)) {
            throw new ValidationException("Password must match!");
        }


    }

}
