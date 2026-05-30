package com.ui.pojos;

public class UserCredentials {
    private String emailAddress;
    private String password;
    private String userName;

    public UserCredentials(String emailAddress, String password, String userName) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
