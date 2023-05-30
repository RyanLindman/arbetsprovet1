package com.lindman.models;

public class RegistrationDTO {
    private String username;
    private String password;
    private String full_name;
    private String email;
    public RegistrationDTO(){
        super();
    }

    public RegistrationDTO(String username, String password, String full_name, String email){
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.full_name = full_name;

    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String toString(){
        return "Registration info: username: " + this.username + " password: " + this.password;
    }
}
