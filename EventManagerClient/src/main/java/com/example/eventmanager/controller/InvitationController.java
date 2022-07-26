package com.example.eventmanager.controller;

import com.example.eventmanager.Constant.ResponseMessage;
import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.model.Response;
import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.subsystem.IRequestHandler;
import com.example.eventmanager.subsystem.IResponseHandler;
import com.example.eventmanager.subsystem.request.RequestHandler;
import com.example.eventmanager.subsystem.response.ResponseHandler;

import java.util.List;

/**
 * This class is controller of Invitation Screen
 *
 * @author hoangnguyenthe20183925
 */
public class InvitationController extends BaseController {

    private void removeAUserByUsername(List<UserDTO> userDTOList, String username) {
        for (UserDTO u :
                userDTOList) {
            if (u.getUsername().equals(username)) {
                userDTOList.remove(u);
                break;
            }
        }
    }

    /**
     * this method ...
     */
    public List<UserDTO> getListUser(StringBuilder responseMess) {
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
        removeAUserByUsername(userDTOList, getUserName());
        for (UserDTO u :
                userDTOList) {
            System.out.println(u.getUsername());
        }

        return userDTOList;
    }

    public int invite(UserDTO user, EventDTO eventDTO, StringBuilder responseMess) {
        IRequestHandler requestHandler = new RequestHandler();
        IResponseHandler responseHandler = new ResponseHandler();
        int ret;

        //send login message
        ret = requestHandler.sendInvitationRequest(user, eventDTO);
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
        responseHandler.handlerInviteResponse(response, responseMess);

        //check responseMess == OK?
        if (!responseMess.toString().equals(ResponseMessage.OK_MESS))
            return -1;

        return 0;
    }
}
