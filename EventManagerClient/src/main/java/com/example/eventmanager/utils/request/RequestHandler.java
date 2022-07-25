package com.example.eventmanager.utils.request;

import com.example.eventmanager.Constain.RequestPrefix;
import com.example.eventmanager.model.Client;
import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.model.UserDTO;
import com.example.eventmanager.utils.IRequestHandler;

import java.util.ArrayList;
import java.util.List;

import static com.example.eventmanager.Constain.SocketConfig.SEPARATOR_LEVEL_1;
import static com.example.eventmanager.Constain.SocketConfig.DELIMITER;

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
        String rq = setRequestMessage(RequestPrefix.LOG_OUT, messList);

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
        String rq = setRequestMessage(RequestPrefix.REPLY_JOIN_EVENT, messList);

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

        if (sendRequest(rq) == -1) {
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
}
