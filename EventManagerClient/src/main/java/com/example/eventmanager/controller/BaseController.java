package com.example.eventmanager.controller;

import com.example.eventmanager.view.login.LoginScreenHandler;

import static com.example.eventmanager.Constant.SocketConfig.SEPARATOR_LEVEL_1;
import static com.example.eventmanager.Constant.SocketConfig.SEPARATOR_LEVEL_2;


/**
 * This class is the base controller for our EventManager project
 *
 * @author hoangnguyenthe20183925
 */
public class BaseController {

    /**
     * This function is get username of user that logged in
     *
     * @return username
     */
    public String getUserName() {
        if (LoginScreenHandler.getUserNameLoggedIn() == null) {
            return "";
        }

        return LoginScreenHandler.getUserNameLoggedIn();
    }

    /**
     * This function is to check s include Not Allowed Character?
     *
     * @param s: string
     * @return true if s include
     */
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
