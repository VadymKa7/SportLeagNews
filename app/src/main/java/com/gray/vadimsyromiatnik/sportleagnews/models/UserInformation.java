package com.gray.vadimsyromiatnik.sportleagnews.models;

/**
 * Created by vadimsyromiatnik on 12/23/17.
 */

public class UserInformation  {

    private String name;
    private String email;
    private String phone_num;
    private String league;
    private String command;

    public UserInformation(){

    }

    public UserInformation(String league, String command) {
        this.league = league;
        this.command = command;
    }

    public UserInformation(String email, String name, String phone_num) {
        this.email = email;
        this.name = name;
        this.phone_num = phone_num;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }
}

