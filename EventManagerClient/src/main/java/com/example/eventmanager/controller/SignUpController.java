package com.example.eventmanager.controller;

import com.example.eventmanager.constant.ResponseMessage;
import com.example.eventmanager.model.Response;
import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.subsystem.IRequestHandler;
import com.example.eventmanager.subsystem.IResponseHandler;
import com.example.eventmanager.subsystem.request.RequestHandler;
import com.example.eventmanager.subsystem.response.ResponseHandler;

/**
 * This class is controller of Sign up Screen
 *
 * @author hoangnguyenthe20183925
 */
public class SignUpController extends BaseController {

    /**
     * This function is to sign up a new user
     *
     * @param user:         user info
     * @param responseMess: message after handler response
     * @return 0 if success
     */
    public int signUp(UserDTO user, StringBuilder responseMess) {
        IRequestHandler requestHandler = new RequestHandler();
        IResponseHandler responseHandler = new ResponseHandler();
        int ret;

        //send login message
        ret = requestHandler.sendSignUpRequest(user.getUsername(), user.getPassword());
        //check send successfully?
        if (ret != 0) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return -1;
        }

        //receive message to cache of client
        ret = responseHandler.receiveResponse();
        //check receive successfully?
        if (ret != 0) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return -1;
        }
        //get a message from cache and converse message to response
        Response response = responseHandler.getResponses();

        //handle login response
        responseHandler.handlerSignUpResponse(response, responseMess);

        //check responseMess == OK?
        if (!responseMess.toString().equals(ResponseMessage.OK_MESS))
            return -1;

        return 0;
    }
}
