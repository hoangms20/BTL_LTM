package com.example.eventmanager.Constain;

public class ResponseCode {
    //sign up new user
    public static final String OK_SIGN_UP_CODE = "10";
    public static final String NOT_ALLOWED_CHARACTER_SIGN_UP_CODE = "11";
    public static final String NO_USER_OR_PASSWORD_SIGN_UP_CODE = "12";
    public static final String EXITED_USER_SIGN_UP_CODE = "13";

    //login
    public static final String OK_LOGIN_CODE = "20";
    public static final String WRONG_USER_OR_PASSWORD_LOGIN_CODE = "21";

    //create new event
    public static final String OK_CREATE_EVENT_CODE = "30";
    public static final String ILLEGAL_TIME_CREATE_EVENT_CODE = "34";

    //invitation
    public static final String OK_INVITATION_CODE = "40";
    public static final String NOT_EXIST_RECEIVER_OR_SENDER_INVITATION_CODE = "41";

    //reply invitation
    public static final String OK_REPLY_INVITATION_CODE = "50";

    //request
    public static final String OK_REQUEST_CODE = "60";
    public static final String NOT_EXIST_RECEIVER_OR_SENDER_REQUEST_CODE = "61";

    //reply request
    public static final String OK_REPLY_REQUEST_CODE = "70";

    //Log out
    public static final String OK_LOG_OUT_CODE = "120";

    //wrong request message
    public static final String WRONG_REQUEST_CODE = "99";
}
