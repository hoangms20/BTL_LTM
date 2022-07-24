package com.example.eventmanager.controller;

import com.example.eventmanager.view.login.LoginScreenHandler;

import static com.example.eventmanager.Constain.SocketConfig.SEPARATOR_LEVEL_1;
import static com.example.eventmanager.Constain.SocketConfig.SEPARATOR_LEVEL_2;


/**
 * This class is the base controller for our EventManager project
 * @author hoangnguyenthe20183925
 */
public class BaseController {
    public String getUserName(){
        if (LoginScreenHandler.getUserNameLoggedIn() == null){
            return "";
        }

        return LoginScreenHandler.getUserNameLoggedIn();
    }

    public boolean checkNotAllowedCharacter(String s) {
        if (s != null) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == SEPARATOR_LEVEL_1.charAt(0) || s.charAt(i) == SEPARATOR_LEVEL_2.charAt(0)) {
                    return false;
                }
            }
        }

        return true;
    }

}
