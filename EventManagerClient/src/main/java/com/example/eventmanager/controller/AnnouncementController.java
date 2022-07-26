package com.example.eventmanager.controller;

import com.example.eventmanager.Constain.ResponseMessage;
import com.example.eventmanager.model.EventRequestDTO;
import com.example.eventmanager.model.Response;
import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.utils.IRequestHandler;
import com.example.eventmanager.utils.IResponseHandler;
import com.example.eventmanager.utils.request.RequestHandler;
import com.example.eventmanager.utils.response.ResponseHandler;

import java.util.List;

public class AnnouncementController extends BaseController{

    public List<EventRequestDTO> getListInvitedEvent(StringBuilder responseMess){
        IRequestHandler requestHandler = new RequestHandler();
        IResponseHandler responseHandler = new ResponseHandler();
        int ret;
        List<EventRequestDTO> eventRequestDTOList;

        //send login message
        ret = requestHandler.sendGetInvitationListRequest(new UserDTO(getUserName()));
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
        eventRequestDTOList = responseHandler.handlerGetInvitationListResponse(response, responseMess);

        return eventRequestDTOList;
    }

    public List<EventRequestDTO> getListRequestedEvent(StringBuilder responseMess){
        IRequestHandler requestHandler = new RequestHandler();
        IResponseHandler responseHandler = new ResponseHandler();
        int ret;
        List<EventRequestDTO> eventRequestDTOList;

        //send login message
        ret = requestHandler.sendGetRequestListRequest(new UserDTO(getUserName()));
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
        eventRequestDTOList = responseHandler.handlerGetRequestListResponse(response, responseMess);

        return eventRequestDTOList;
    }

}
