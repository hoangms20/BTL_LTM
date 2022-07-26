package com.example.eventmanager.utils;

import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.model.EventRequestDTO;
import com.example.eventmanager.model.Response;
import com.example.eventmanager.model.UserDTO;

import java.util.List;

public interface IResponseHandler {

    int receiveResponse();

    Response getResponses();

    void handlerLoginResponse(Response response, StringBuilder responseMess);

    void handlerSignUpResponse(Response response, StringBuilder responseMess);

    void handlerLogoutResponse(Response response, StringBuilder responseMess);

    int handlerReplyRequestResponse(Response response, StringBuilder responseMess);

    List<EventRequestDTO> handlerGetInvitationListResponse(Response response, StringBuilder responseMess);

    List<EventRequestDTO> handlerGetRequestListResponse(Response response, StringBuilder responseMess);

    List<UserDTO> handlerGetUserListResponse(Response response, StringBuilder responseMess);

    List<EventDTO> handlerGetEventListResponse(Response response, StringBuilder responseMess);

    EventDTO handlerCreateEventResponse(Response response, StringBuilder responseMess);

    List<UserDTO> handlerGetUserAttendListResponse(Response response, StringBuilder responseMess);

    int handlerRequestResponse(Response response, StringBuilder responseMess);

    int handlerInviteResponse(Response response, StringBuilder responseMess);

    int handlerReplyInvitationResponse(Response response, StringBuilder responseMess);
}
