package com.example.eventmanager.controller;

import com.example.eventmanager.Constain.ResponseMessage;
import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.model.Response;
import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.utils.IRequestHandler;
import com.example.eventmanager.utils.IResponseHandler;
import com.example.eventmanager.utils.request.RequestHandler;
import com.example.eventmanager.utils.response.ResponseHandler;

import java.util.List;

public class EventDetailController extends BaseController{

    public int joinEvent(EventDTO event, UserDTO user, StringBuilder responseMess){
        IRequestHandler requestHandler = new RequestHandler();
        IResponseHandler responseHandler = new ResponseHandler();
        int ret;

        //send login message
        ret = requestHandler.sendJoinEventRequest(event, user);
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
        responseHandler.handlerJoinEventResponse(response, responseMess);

        //check responseMess == OK?
        if (!responseMess.toString().equals(ResponseMessage.OK_MESS))
            return -1;

        return 0;
    }

    public List<UserDTO> getListUser(StringBuilder responseMess){
        IRequestHandler requestHandler = new RequestHandler();
        IResponseHandler responseHandler = new ResponseHandler();
        int ret;
        List<UserDTO> userDTOList;

        //send login message
        ret = requestHandler.sendGetUserListRequest();
        //check send successfully?
        if (ret != 0) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return null;
        }

        //receive message to cache of client
        ret = responseHandler.receiveResponse();
        //check receive successfully?
        if (ret != 0) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return null;
        }
        //get a message from cache and converse message to response
        Response response = responseHandler.getResponses();

        //handle login response
        userDTOList = responseHandler.handlerGetUserListResponse(response, responseMess);

        //remove user logged in from list
        for (UserDTO u:
                userDTOList) {
            System.out.println(u.getUsername());
        }

        return userDTOList;
    }
}
