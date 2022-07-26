package com.example.eventmanager.controller;

import com.example.eventmanager.constant.ResponseMessage;
import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.model.Response;
import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.subsystem.IRequestHandler;
import com.example.eventmanager.subsystem.IResponseHandler;
import com.example.eventmanager.subsystem.request.RequestHandler;
import com.example.eventmanager.subsystem.response.ResponseHandler;

import java.util.List;

/**
 * This class is controller of Event Detail Screen
 *
 * @author hoangnguyenthe20183925
 */
public class EventDetailController extends BaseController {

    /**
     * This function is to request to join event
     *
     * @param event:        event info
     * @param user:         requester
     * @param responseMess: message after handler response
     * @return 0 if success
     */
    public int requestToJoin(EventDTO event, UserDTO user, StringBuilder responseMess) {
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
        responseHandler.handlerRequestResponse(response, responseMess);

        //check responseMess == OK?
        if (!responseMess.toString().equals(ResponseMessage.OK_MESS))
            return -1;

        return 0;
    }

    /**
     * This function is to get list user attend
     *
     * @param eventDTO:     event info
     * @param responseMess: message after handler response
     * @return UserDTO list
     */
    public List<UserDTO> getListUserAttend(EventDTO eventDTO, StringBuilder responseMess) {
        IRequestHandler requestHandler = new RequestHandler();
        IResponseHandler responseHandler = new ResponseHandler();
        int ret;
        List<UserDTO> userDTOList;

        //send login message
        ret = requestHandler.sendGetAttendedUserListRequest(eventDTO);
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
        userDTOList = responseHandler.handlerGetUserAttendListResponse(response, responseMess);

        return userDTOList;
    }

    /**
     * This function is to reply invitation
     *
     * @param event:        event info
     * @param user:         replier
     * @param responseMess: message after handler response
     * @return 0 if success
     */
    public int replyInvitation(EventDTO event, UserDTO user, String reply, StringBuilder responseMess) {
        IRequestHandler requestHandler = new RequestHandler();
        IResponseHandler responseHandler = new ResponseHandler();
        int ret;

        //send login message
        ret = requestHandler.sendReplyInvitationRequest(event, user, reply);
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
        responseHandler.handlerReplyInvitationResponse(response, responseMess);

        //check responseMess == OK?
        if (!responseMess.toString().equals(ResponseMessage.OK_MESS))
            return -1;

        return 0;
    }
}
