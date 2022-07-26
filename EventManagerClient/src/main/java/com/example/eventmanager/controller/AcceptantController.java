package com.example.eventmanager.controller;

import com.example.eventmanager.Constant.ResponseMessage;
import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.model.EventRequestDTO;
import com.example.eventmanager.model.Response;
import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.subsystem.IRequestHandler;
import com.example.eventmanager.subsystem.IResponseHandler;
import com.example.eventmanager.subsystem.request.RequestHandler;
import com.example.eventmanager.subsystem.response.ResponseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is controller of Acceptant Screen
 *
 * @author hoangnguyenthe20183925
 */
public class AcceptantController extends BaseController {

    private void filterEventById(List<EventRequestDTO> eventRequestDTOS, String eventId) {
        List<EventRequestDTO> eventRequestDTOList = new ArrayList<>();

        for (EventRequestDTO u :
                eventRequestDTOS) {
            if (u.getEventId().equals(eventId)) {
                eventRequestDTOList.add(u);
            }
        }

        eventRequestDTOS = eventRequestDTOList;

    }

    public List<EventRequestDTO> getRequestedEventList(StringBuilder responseMess, String eventId) {
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

        filterEventById(eventRequestDTOList, eventId);

        return eventRequestDTOList;
    }

    public int replyJoinRequest(UserDTO user, EventDTO eventDTO, String reply, StringBuilder responseMess) {
        IRequestHandler requestHandler = new RequestHandler();
        IResponseHandler responseHandler = new ResponseHandler();
        int ret;

        //send login message
        ret = requestHandler.sendReplyJoinEventRequest(eventDTO, user, reply);
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
        responseHandler.handlerReplyRequestResponse(response, responseMess);

        //check responseMess == OK?
        if (!responseMess.toString().equals(ResponseMessage.OK_MESS))
            return -1;

        return 0;
    }

}
