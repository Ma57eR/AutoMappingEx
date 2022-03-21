package com.automapex.entities.users;

public class LoginDTO {

    //TODO: Validate email
    private String email;
    private String password;

    public LoginDTO(String[] commandLineParts) {
        this.email = commandLineParts[1];
        this.password = commandLineParts[2];
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
