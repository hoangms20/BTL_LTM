package com.example.eventmanager.controller;

import com.example.eventmanager.constant.ResponseMessage;
import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.model.Response;
import com.example.eventmanager.subsystem.IRequestHandler;
import com.example.eventmanager.subsystem.IResponseHandler;
import com.example.eventmanager.subsystem.request.RequestHandler;
import com.example.eventmanager.subsystem.response.ResponseHandler;

/**
 * This class is controller of Create Event Screen
 *
 * @author hoangnguyenthe20183925
 */
public class CreateEventController extends BaseController{
    public int createNewEvent(EventDTO eventDTO, StringBuilder responseMess){
        IRequestHandler requestHandler = new RequestHandler();
        IResponseHandler responseHandler = new ResponseHandler();
        int ret;

        //send login message
        ret = requestHandler.sendCreateEventRequest(eventDTO);
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
        responseHandler.handlerCreateEventResponse(response, responseMess);

        //check responseMess == OK?
        if (!responseMess.toString().equals(ResponseMessage.OK_MESS))
            return -1;

        return 0;
    }
}
