package com.example.eventmanager.subsystem;

import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.model.UserDTO;

public interface IRequestHandler {
    int sendLoginRequest(String username, String password);

    int sendSignUpRequest(String username, String password);

    int sendLogOutRequest();

    int sendReplyJoinEventRequest(EventDTO event, UserDTO userDTO, String reply);

    int sendGetInvitationListRequest(UserDTO userDTO);

    int sendGetRequestListRequest(UserDTO userDTO);

    int sendGetUserListRequest();

    int sendGetEventListRequest();

    int sendCreateEventRequest(EventDTO event);

    int sendJoinEventRequest(EventDTO event, UserDTO userDTO);

    int sendInvitationRequest(UserDTO userDTO, EventDTO eventDTO);

    int sendReplyInvitationRequest(EventDTO event, UserDTO userDTO, String reply);

    int sendGetAttendedUserListRequest(EventDTO eventDTO);

    int sendGetRequestReplyListRequest(UserDTO userDTO);

    int sendGetInvitationReplyListRequest(UserDTO userDTO);
}
