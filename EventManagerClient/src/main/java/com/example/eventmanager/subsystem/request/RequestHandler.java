package com.example.eventmanager.subsystem.request;

import com.example.eventmanager.Constant.RequestPrefix;
import com.example.eventmanager.subsystem.Client;
import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.subsystem.IRequestHandler;

import java.util.ArrayList;
import java.util.List;

import static com.example.eventmanager.Constant.SocketConfig.SEPARATOR_LEVEL_1;
import static com.example.eventmanager.Constant.SocketConfig.DELIMITER;

/**
 * This class is to send message
 *
 * @author hoangnguyenthe20183925
 */
public class RequestHandler implements IRequestHandler {
    private Client client = Client.getClient();

    private String setRequestMessage(String prefix, List<String> messList) {
        StringBuilder rq = new StringBuilder("");
        rq.append(prefix);//set prefix

        if (messList.size() > 0) {
            for (String s :
                    messList) {
                rq.append(s);
                rq.append(SEPARATOR_LEVEL_1);//set separator
            }
        }

        rq.append(DELIMITER);//set delimiter

        return rq.toString();
    }

    private int sendRequest(String requestMess){
        if (client.send(requestMess, requestMess.length()) == -1) {
            return -1;
        }

        return 0;
    }

    @Override
    public int sendLoginRequest(String username, String password) {
        //add every message info to a list
        List<String> messList = new ArrayList<>();
        messList.add(username);
        messList.add(password);

        //set request message
        String rq = setRequestMessage(RequestPrefix.LOG_IN, messList);

        if (client.send(rq, rq.length()) == -1) {
            return -1;
        }

        return 0;
    }

    @Override
    public int sendSignUpRequest(String username, String password) {
        //add every message info to a list
        List<String> messList = new ArrayList<>();
        messList.add(username);
        messList.add(password);

        //set request message
        String rq = setRequestMessage(RequestPrefix.CREATE_NEW_USER, messList);

        if (sendRequest(rq) == -1) {
            return -1;
        }

        return 0;
    }

    @Override
    public int sendLogOutRequest() {
        //add every message info to a list
        List<String> messList = new ArrayList<>();

        //set request message
        String rq = RequestPrefix.LOG_OUT + DELIMITER;

        if (sendRequest(rq) == -1) {
            return -1;
        }

        return 0;
    }

    @Override
    public int sendCreateEventRequest(EventDTO event) {
        //add every message info to a list
        List<String> messList = new ArrayList<>();
        messList.add(event.getCreatedBy());
        messList.add(event.getName());
        messList.add(event.getPlace());
        messList.add(event.getTime());
        messList.add(event.getDescription());

        //set request message
        String rq = setRequestMessage(RequestPrefix.CREATE_EVENT, messList);

        if (sendRequest(rq) == -1) {
            return -1;
        }

        return 0;
    }

    @Override
    public int sendGetInvitationListRequest(UserDTO userDTO) {
        //add every message info to a list
        List<String> messList = new ArrayList<>();
        messList.add(userDTO.getUsername());

        //set request message
        String rq = setRequestMessage(RequestPrefix.GET_INVITATION_LIST, messList);
        StringBuilder s =  new StringBuilder(rq.substring(0,rq.length()-3));
        s.append(DELIMITER);

        if (sendRequest(s.toString()) == -1) {
            return -1;
        }

        return 0;
    }

    @Override
    public int sendGetRequestListRequest(UserDTO userDTO) {
        //add every message info to a list
        List<String> messList = new ArrayList<>();
        messList.add(userDTO.getUsername());

        //set request message
        String rq = setRequestMessage(RequestPrefix.GET_REQUEST_LIST, messList);
        StringBuilder s =  new StringBuilder(rq.substring(0,rq.length()-3));
        s.append(DELIMITER);

        if (sendRequest(s.toString()) == -1) {
            return -1;
        }

        return 0;
    }

    @Override
    public int sendGetUserListRequest() {
        //add every message info to a list
        List<String> messList = new ArrayList<>();

        //set request message
        String rq = setRequestMessage(RequestPrefix.GET_USER_LIST, messList);

        if (sendRequest(rq) == -1) {
            return -1;
        }

        return 0;
    }

    @Override
    public int sendGetEventListRequest() {
        //add every message info to a list
        List<String> messList = new ArrayList<>();

        //set request message
        String rq = setRequestMessage(RequestPrefix.GET_EVENT_LIST, messList);

        if (sendRequest(rq) == -1) {
            return -1;
        }

        return 0;
    }


    @Override
    public int sendJoinEventRequest(EventDTO event, UserDTO userDTO) {
        //add every message info to a list
        List<String> messList = new ArrayList<>();
        messList.add(userDTO.getUsername());
        messList.add(event.getCreatedBy());
        messList.add(event.getId());

        //set request message
        String rq = setRequestMessage(RequestPrefix.JOIN_EVENT, messList);

        if (sendRequest(rq) == -1) {
            return -1;
        }

        return 0;
    }

    @Override
    public int sendReplyJoinEventRequest(EventDTO event, UserDTO userDTO, String reply) {
        //add every message info to a list
        List<String> messList = new ArrayList<>();
        messList.add(event.getCreatedBy());
        messList.add(userDTO.getUsername());
        messList.add(event.getId());
        messList.add(reply);

        //set request message
        String rq = setRequestMessage(RequestPrefix.JOIN_EVENT_REPLY, messList);

        if (sendRequest(rq) == -1) {
            return -1;
        }

        return 0;
    }

    @Override
    public int sendInvitationRequest(UserDTO userDTO, EventDTO eventDTO) {
        //add every message info to a list
        List<String> messList = new ArrayList<>();
        messList.add(eventDTO.getCreatedBy());
        messList.add(userDTO.getUsername());
        messList.add(eventDTO.getId());

        //set request message
        String rq = setRequestMessage(RequestPrefix.INVITATION, messList);

        if (sendRequest(rq) == -1) {
            return -1;
        }

        return 0;
    }

    @Override
    public int sendReplyInvitationRequest(EventDTO event, UserDTO userDTO, String reply) {
        //add every message info to a list
        List<String> messList = new ArrayList<>();
        messList.add(userDTO.getUsername());
        messList.add(event.getCreatedBy());
        messList.add(event.getId());
        messList.add(reply);

        //set request message
        String rq = setRequestMessage(RequestPrefix.INVITATION_REPLY, messList);

        if (sendRequest(rq) == -1) {
            return -1;
        }

        return 0;
    }

    @Override
    public int sendGetAttendedUserListRequest(EventDTO eventDTO) {
        //add every message info to a list
        List<String> messList = new ArrayList<>();
        messList.add(eventDTO.getId());

        //set request message
        String rq = RequestPrefix.GET_ATTENDED_USER_LIST + eventDTO.getId() + DELIMITER;

        if (sendRequest(rq) == -1) {
            return -1;
        }

        return 0;
    }

    @Override
    public int sendGetRequestReplyListRequest(UserDTO userDTO) {
        //set request message
        String rq = RequestPrefix.GET_REQUEST_REPLY_LIST + userDTO.getUsername() + DELIMITER;

        if (sendRequest(rq) == -1) {
            return -1;
        }

        return 0;
    }

    @Override
    public int sendGetInvitationReplyListRequest(UserDTO userDTO) {
        //set request message
        String rq = RequestPrefix.GET_INVITATION_REPLY_LIST + userDTO.getUsername() + DELIMITER;

        if (sendRequest(rq) == -1) {
            return -1;
        }

        return 0;
    }
}
