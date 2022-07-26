package com.example.eventmanager.Constain;

public class ResponseMessage {
    //ok
    public static final String OK_MESS = "OK";

    //sign up
    public static final String NOT_ALLOWED_CHARACTER_MESS = "Username or password is include not allowed character!";
    public static final String EXITED_USER_MESS = "This username is existed!";

    //login
    public static final String WRONG_USER_OR_PASSWORD_MESS = "Wrong user or password!";

    //create new event
    public static final String ILLEGAL_TIME_CREATE_EVENT_MESS = "Time must begin from tomorrow!";

    //invitation
    public static final String NOT_EXIST_RECEIVER_OR_SENDER_MESS = "Not existed this receiver or sender!";
    public static final String INVITED_INVITATION_MESS = "You invited this user!";

    //reply invitation
    public static final String REPLIED_INVITATION_MESS = "You replied this invitation!";

    //request
    public static final String REQUESTED_REQUEST_MESS = "You requested to join this event!";

    //reply request
    public static final String REPLIED_REQUEST_MESS = "You replied this request!";

    //something wrong
    public static final String SOMETHING_WRONG_MESS = "Somethings Wrong!";
}
