package com.example.eventmanager.controller;

import com.example.eventmanager.constant.ResponseMessage;
import com.example.eventmanager.model.EventRequestDTO;
import com.example.eventmanager.model.ReplyDTO;
import com.example.eventmanager.model.Response;
import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.subsystem.IRequestHandler;
import com.example.eventmanager.subsystem.IResponseHandler;
import com.example.eventmanager.subsystem.request.RequestHandler;
import com.example.eventmanager.subsystem.response.ResponseHandler;

import java.util.List;

/**
 * This class is controller of Announcement Screen
 *
 * @author hoangnguyenthe20183925
 */
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

    public List<ReplyDTO> getListRequestReply(StringBuilder responseMess){
        IRequestHandler requestHandler = new RequestHandler();
        IResponseHandler responseHandler = new ResponseHandler();
        int ret;
        List<ReplyDTO> replyDTOList;

        //send login message
        ret = requestHandler.sendGetRequestReplyListRequest(new UserDTO(getUserName()));
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
        replyDTOList = responseHandler.handlerGetRequestReplyListResponse(response, responseMess);

        return replyDTOList;
    }

    public List<ReplyDTO> getListInvitationReply(StringBuilder responseMess){
        IRequestHandler requestHandler = new RequestHandler();
        IResponseHandler responseHandler = new ResponseHandler();
        int ret;
        List<ReplyDTO> replyDTOList;

        //send login message
        ret = requestHandler.sendGetInvitationReplyListRequest(new UserDTO(getUserName()));
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
        replyDTOList = responseHandler.handlerGetInvitationReplyListResponse(response, responseMess);

        return replyDTOList;
    }

}
