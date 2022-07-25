package com.example.eventmanager.utils;

import com.example.eventmanager.model.EventDTO;
import com.example.eventmanager.model.RequestedEventDTO;
import com.example.eventmanager.model.Response;
import com.example.eventmanager.model.UserDTO;

import java.util.List;

public interface IResponseHandler {

    int receiveResponse();

    Response getResponses();

    void handlerLoginResponse(Response response, StringBuilder responseMess);

    void handlerSignUpResponse(Response response, StringBuilder responseMess);

    void handlerLogoutResponse(Response response, StringBuilder responseMess);

    int handlerReplyJoinEventResponse(Response response, StringBuilder responseMess);

    List<EventDTO> handlerGetInvitationListResponse(Response response, StringBuilder responseMess);

    List<RequestedEventDTO> handlerGetRequestListResponse(Response response, StringBuilder responseMess);

    List<UserDTO> handlerGetUserListResponse(Response response, StringBuilder responseMess);

    List<EventDTO> handlerGetEventListResponse(Response response, StringBuilder responseMess);

    EventDTO handlerCreateEventResponse(Response response, StringBuilder responseMess);

    int handlerJoinEventResponse(Response response, StringBuilder responseMess);

    int handlerInviteResponse(Response response, StringBuilder responseMess);
}
