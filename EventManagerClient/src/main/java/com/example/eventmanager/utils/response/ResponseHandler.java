package com.example.eventmanager.utils.response;

import com.example.eventmanager.Constain.ResponseCode;
import com.example.eventmanager.Constain.ResponseMessage;
import com.example.eventmanager.model.*;
import com.example.eventmanager.utils.IResponseHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.example.eventmanager.Constain.SocketConfig.*;

public class ResponseHandler implements IResponseHandler {
    private Client client = Client.getClient();

    private String getAMessage() {
        StringBuilder cache = client.getCache();
        String mess = cache.toString();

        return mess;
    }

    public void messToResponse(Response response, String mess) {
        int n = mess.length();
        if (n == 0) {
            response.setCode("");
            response.setMessage("");
            return;
        }

        int index = -1;

        for (int i = 0; i < n; i++) {
            if(mess.charAt(i) == ' ') {
                index = i;
                break;
            }
        }

        StringBuilder code = new StringBuilder("");

        StringBuilder message = new StringBuilder("");

        if (index == -1) {
            code.append(mess);
            message.append(mess);
        }else {
            code.append(mess.substring(0, index));
            if (index < n - 1) {
                message.append(mess.substring(index + 1, n));
            }
        }

        response.setCode(code.toString());
        response.setMessage(message.toString());

    }

    private void updateCache(){
        client.getCache().delete(0, client.getCache().length());

    }


    public List<String> splitMessage(String mess, String regex) {
        List<String> list = new ArrayList<>();
        String[] strings = mess.split(Pattern.quote(regex));

        for (String s:
                strings) {
            list.add(s);
        }

        return list;
    }

    public List<UserDTO> responseMessToUserList(String mess){
        List<UserDTO> userEntityList = new ArrayList<>();

        if (mess == null || mess.equals(""))    return userEntityList;

        List<String> list = splitMessage(mess, SEPARATOR_LEVEL_1);

        for (String s:
                list) {
            userEntityList.add(new UserDTO(s));
        }

        return userEntityList;
    }

    public EventDTO dataToAEvent(String mess){
        List<String> list = splitMessage(mess, SEPARATOR_LEVEL_1);
        EventDTO event = new EventDTO();

        for (int i = list.size(); i <= 5 ; i++){
            list.add("");
        }

        event.setId(list.get(0));
        event.setCreatedBy(list.get(1));
        event.setName(list.get(2));
        event.setPlace(list.get(3));
        event.setTime(list.get(4));
        event.setDescription(list.get(5));

        return event;
    }

    public RequestedEventDTO dataToARequestedEvent(String mess){
        List<String> list = splitMessage(mess, SEPARATOR_LEVEL_1);
        RequestedEventDTO event = new RequestedEventDTO();

        for (int i = list.size(); i <= 6 ; i++){
            list.add("");
        }

        event.setSender(list.get(0));
        event.setId(list.get(1));
        event.setCreatedBy(list.get(2));
        event.setName(list.get(3));
        event.setPlace(list.get(4));
        event.setTime(list.get(5));
        event.setDescription(list.get(6));

        return event;
    }

    public List<EventDTO> responseMessToEventList(String mess){
        List<EventDTO> eventEntityList = new ArrayList<>();

        if (mess == null || mess.equals(""))    return eventEntityList;

        List<String> list = splitMessage(mess, SEPARATOR_LEVEL_2);

        for (String s:
                list) {
            eventEntityList.add(dataToAEvent(s));
        }

        return eventEntityList;
    }

    public List<RequestedEventDTO> responseMessToRequestedEventList(String mess){
        List<RequestedEventDTO> requestedEventDTOS = new ArrayList<>();

        if (mess == null || mess.equals(""))    return requestedEventDTOS;

        List<String> list = splitMessage(mess, SEPARATOR_LEVEL_2);

        for (String s:
                list) {
            requestedEventDTOS.add(dataToARequestedEvent(s));
        }

        return requestedEventDTOS;
    }

    @Override
    public int receiveResponse() {
        if (client.recv() == -1) {
            return -1;
        }
        return 0;
    }

    @Override
    public Response getResponses() {

        String mess = getAMessage();
        Response response = new Response();
        messToResponse(response, mess);
        updateCache();

        System.out.println("code=" + response.getCode());
        System.out.println("mess=" + response.getMessage());
        System.out.println("*********");

        return response;
    }

    @Override
    public void handlerLoginResponse(Response response, StringBuilder responseMess) {
        if (response == null){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return;
        }

        if (response.getCode().equals(ResponseCode.OK_LOGIN_CODE)){
            responseMess.append(ResponseMessage.OK_MESS);
            return;
        }

        if (response.getCode().equals(ResponseCode.WRONG_USER_OR_PASSWORD_LOGIN_CODE)){
            responseMess.append(ResponseMessage.WRONG_USER_OR_PASSWORD_MESS);
            return;
        }

        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return;
        }

        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
    }

    @Override
    public void handlerSignUpResponse(Response response, StringBuilder responseMess) {
        if (response == null){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return;
        }

        if (response.getCode().equals(ResponseCode.OK_SIGN_UP_CODE)){
            responseMess.append(ResponseMessage.OK_MESS);
            return;
        }

        if (response.getCode().equals(ResponseCode.NOT_ALLOWED_CHARACTER_SIGN_UP_CODE)){
            responseMess.append(ResponseMessage.NOT_ALLOWED_CHARACTER_MESS);
            return;
        }


        if (response.getCode().equals(ResponseCode.EXITED_USER_SIGN_UP_CODE)){
            responseMess.append(ResponseMessage.EXITED_USER_MESS);
            return;
        }

        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return;
        }

        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
    }

    @Override
    public void handlerLogoutResponse(Response response, StringBuilder responseMess) {
        if (response == null){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return;
        }

        if (response.getCode().equals(ResponseCode.OK_LOG_OUT_CODE)){
            responseMess.append(ResponseMessage.OK_MESS);
            return;
        }

        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return;
        }

        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
    }

    @Override
    public EventDTO handlerCreateEventResponse(Response response, StringBuilder responseMess) {
        if (response == null){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return null;
        }

        if (response.getCode().equals(ResponseCode.OK_CREATE_EVENT_CODE)){
            responseMess.append(ResponseMessage.OK_MESS);
            return dataToAEvent(response.getMessage());
        }

        if (response.getCode().equals(ResponseCode.ILLEGAL_TIME_CREATE_EVENT_CODE)){
            responseMess.append(ResponseMessage.ILLEGAL_TIME_CREATE_EVENT_MESS);
            return dataToAEvent(response.getMessage());
        }

        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return null;
        }

        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
        return null;
    }

    @Override
    public int handlerJoinEventResponse(Response response, StringBuilder responseMess) {
        if (response == null){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return -1;
        }

        if (response.getCode().equals(ResponseCode.OK_JOIN_EVENT_CODE)){
            responseMess.append(ResponseMessage.OK_MESS);
            return 0;
        }

        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return -1;
        }

        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
        return -1;
    }

    @Override
    public int handlerReplyJoinEventResponse(Response response, StringBuilder responseMess) {
        if (response == null){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return -1;
        }

        if (response.getCode().equals(ResponseCode.OK_REPLY_JOIN_EVENT_CODE)){
            responseMess.append(ResponseMessage.OK_MESS);
            return 0;
        }

        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return -1;
        }

        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
        return -1;
    }

    @Override
    public List<EventDTO> handlerGetInvitationListResponse(Response response, StringBuilder responseMess) {
        List<EventDTO> list = new ArrayList<>();
        if (response == null){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return list;
        }

        if (response.getCode().equals(ResponseCode.OK_LISTINVITATION_CODE)){
            responseMess.append(ResponseMessage.OK_MESS);
            return responseMessToEventList(response.getMessage());
        }

        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return list;
        }

        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
        return list;
    }

    @Override
    public List<RequestedEventDTO> handlerGetRequestListResponse(Response response, StringBuilder responseMess) {
        List<RequestedEventDTO> list = new ArrayList<>();
        if (response == null){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return list;
        }

        if (response.getCode().equals(ResponseCode.OK_LISTREQUEST_CODE)){
            responseMess.append(ResponseMessage.OK_MESS);
            return responseMessToRequestedEventList((response.getMessage()));
        }

        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return list;
        }

        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
        return list;
    }

    @Override
    public List<UserDTO> handlerGetUserListResponse(Response response, StringBuilder responseMess) {
        List<UserDTO> list = new ArrayList<>();
        if (response == null){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return list;
        }

        if (response.getCode().equals(ResponseCode.OK_LISTUSER_CODE)){
            responseMess.append(ResponseMessage.OK_MESS);
            return responseMessToUserList(response.getMessage());
        }

        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return list;
        }

        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
        return list;
    }

    @Override
    public List<EventDTO> handlerGetEventListResponse(Response response, StringBuilder responseMess) {
//        List<EventDTO> list = new ArrayList<>();
//        if (response == null){
//            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
//            return list;
//        }
//
//        if (response.getCode().equals(ResponseCode.OK_LISTEVENT_CODE)){
//            responseMess.append(ResponseMessage.OK_MESS);
//            return responseMessToEventList(response.getMessage());
//        }
//
//        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)){
//            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
//            return list;
//        }
//
//        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
//        return list;

        return responseMessToEventList(response.getMessage());
    }

    @Override
    public int handlerInviteResponse(Response response, StringBuilder responseMess) {
        if (response == null){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return -1;
        }

        if (response.getCode().equals(ResponseCode.OK_INVITATION_CODE)){
            responseMess.append(ResponseMessage.OK_MESS);
            return 0;
        }

        if (response.getCode().equals(ResponseCode.NOT_EXIST_RECEIVER_INVITATION_CODE)){
            responseMess.append(ResponseMessage.NOT_EXIST_RECEIVER_INVITATION_MESS);
            return 0;
        }

        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)){
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return -1;
        }

        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
        return -1;
    }
}
