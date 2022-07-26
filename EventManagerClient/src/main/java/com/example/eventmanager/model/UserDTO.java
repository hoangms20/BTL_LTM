package com.example.eventmanager.model;

/**
 * This class is to user info
 *
 * @author hoangnguyenthe20183925
 */
public class UserDTO {
    private String username;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String username) {
        this.username = username;
    }

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
