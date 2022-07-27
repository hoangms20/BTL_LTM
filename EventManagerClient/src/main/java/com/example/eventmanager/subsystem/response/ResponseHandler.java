package com.example.eventmanager.subsystem.response;

import com.example.eventmanager.constant.ResponseCode;
import com.example.eventmanager.constant.ResponseMessage;
import com.example.eventmanager.model.*;
import com.example.eventmanager.subsystem.Client;
import com.example.eventmanager.subsystem.IResponseHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.example.eventmanager.constant.SocketConfig.*;

/**
 * This class is to receive message and handler response
 *
 * @author hoangnguyenthe20183925
 */
public class ResponseHandler implements IResponseHandler {
    //client info
    private Client client = Client.getClient();

    /**
     * This function is to get a message from cache of client
     *
     * @return message
     */
    private String getAMessage() {
        StringBuilder cache = client.getCache();
        String mess = cache.toString();

        return mess;
    }

    /**
     * This function is to converse a message to a response
     */
    public void messToResponse(Response response, String mess) {
        int n = mess.length();
        if (n == 0) {
            response.setCode("");
            response.setMessage("");
            return;
        }

        int index = -1;

        //find index that is space character
        for (int i = 0; i < n; i++) {
            if (mess.charAt(i) == ' ') {
                index = i;
                break;
            }
        }

        StringBuilder code = new StringBuilder("");

        StringBuilder message = new StringBuilder("");

        //cut message
        if (index == -1 || index > 2) {
            code.append(mess);
            message.append(mess);
        } else {
            code.append(mess.substring(0, index));
            if (index < n - 1) {
                message.append(mess.substring(index + 1, n));
            }
        }

        response.setCode(code.toString());
        response.setMessage(message.toString());

    }

    /**
     * This function is to remove a message from cache
     */
    private void updateCache() {
        client.getCache().delete(0, client.getCache().length());
    }


    /**
     * This function is to split a string by regex
     *
     * @param mess:  string is splitted
     * @param regex: regex
     * @return string list
     */
    public List<String> splitMessage(String mess, String regex) {
        List<String> list = new ArrayList<>();
        String[] strings = mess.split(Pattern.quote(regex));

        for (String s :
                strings) {
            list.add(s);
        }

        return list;
    }

    /**
     * This function is to match dato to user list
     *
     * @param mess: data
     * @return userDTOList
     */
    public List<UserDTO> responseMessToUserList(String mess) {
        List<UserDTO> userDTOList = new ArrayList<>();

        if (mess == null || mess.equals("")) return userDTOList;

        List<String> list = splitMessage(mess, SEPARATOR_LEVEL_1);

        for (String s :
                list) {
            userDTOList.add(new UserDTO(s));
        }

        return userDTOList;
    }

    /**
     * This function is to match dato to a user
     *
     * @param mess: data
     * @return event
     */
    public EventDTO dataToAEvent(String mess) {
        List<String> list = splitMessage(mess, SEPARATOR_LEVEL_1);
        EventDTO event = new EventDTO();

        for (int i = list.size(); i <= 5; i++) {
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

    /**
     * This function is to match dato to a request event
     *
     * @param mess: data
     * @return event
     */
    public EventRequestDTO dataToARequestedEvent(String mess) {
        List<String> list = splitMessage(mess, SEPARATOR_LEVEL_1);
        EventRequestDTO event = new EventRequestDTO();

        for (int i = list.size(); i <= 1; i++) {
            list.add("");
        }

        event.setSender(list.get(0));
        event.setEventId(list.get(1));

        return event;
    }

    /**
     * This function is to match dato to a invitation event
     *
     * @param mess: data
     * @return event
     */
    public EventRequestDTO dataToAInvitedEvent(String mess) {
        List<String> list = splitMessage(mess, SEPARATOR_LEVEL_1);
        EventRequestDTO event = new EventRequestDTO();

        for (int i = list.size(); i <= 2; i++) {
            list.add("");
        }

        event.setSender(list.get(0));
        event.setReceiver(list.get(1));
        event.setEventId(list.get(2));

        return event;
    }

    /**
     * This function is to match dato to a reply
     *
     * @param mess: data
     * @return event
     */
    public ReplyDTO dataToAReply(String mess) {
        List<String> list = splitMessage(mess, SEPARATOR_LEVEL_1);
        ReplyDTO replyDTO = new ReplyDTO();

        for (int i = list.size(); i <= 3; i++) {
            list.add("");
        }

        replyDTO.setSender(list.get(0));
        replyDTO.setReceiver(list.get(1));
        replyDTO.setEventId(list.get(2));
        replyDTO.setReply(list.get(3));

        return replyDTO;
    }

    /**
     * This function is to match dato to event list
     *
     * @param mess: data
     * @return eventDTOS
     */
    public List<EventDTO> responseMessToEventList(String mess) {
        List<EventDTO> eventDTOS = new ArrayList<>();

        if (mess == null || mess.equals("")) return eventDTOS;

        List<String> list = splitMessage(mess, SEPARATOR_LEVEL_2);

        for (String s :
                list) {
            eventDTOS.add(dataToAEvent(s));
        }

        return eventDTOS;
    }

    /**
     * This function is to match dato to request list
     *
     * @param mess: data
     * @return eventRequestDTOS
     */
    public List<EventRequestDTO> responseMessToEventRequestList(String mess) {
        List<EventRequestDTO> eventRequestDTOS = new ArrayList<>();

        if (mess == null || mess.equals("")) return eventRequestDTOS;

        List<String> list = splitMessage(mess, SEPARATOR_LEVEL_2);

        for (String s :
                list) {
            eventRequestDTOS.add(dataToARequestedEvent(s));
        }

        return eventRequestDTOS;
    }

    /**
     * This function is to match dato to invitation list
     *
     * @param mess: data
     * @return eventRequestDTOS
     */
    public List<EventRequestDTO> responseMessToEventInvitationList(String mess) {
        List<EventRequestDTO> eventRequestDTOS = new ArrayList<>();

        if (mess == null || mess.equals("")) return eventRequestDTOS;

        List<String> list = splitMessage(mess, SEPARATOR_LEVEL_2);

        for (String s :
                list) {
            eventRequestDTOS.add(dataToAInvitedEvent(s));
        }

        return eventRequestDTOS;
    }

    /**
     * This function is to match dato to a reply list
     *
     * @param mess: data
     * @return replyDTOS
     */
    public List<ReplyDTO> responseMessToReplyList(String mess) {
        List<ReplyDTO> replyDTOS = new ArrayList<>();

        if (mess == null || mess.equals("")) return replyDTOS;

        List<String> list = splitMessage(mess, SEPARATOR_LEVEL_2);

        for (String s :
                list) {
            replyDTOS.add(dataToAReply(s));
        }

        return replyDTOS;
    }

    /**
     * This function is to receive message from server
     *
     * @return 0 if success
     */
    @Override
    public int receiveResponse() {
        if (client.recv() == -1) {
            return -1;
        }
        return 0;
    }

    /**
     * This function is to get response from cache of client
     *
     * @return response
     */
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

    /**
     * This function is to handler login response
     *
     * @param responseMess: save response message after handler response
     * @param response:     response
     */
    @Override
    public void handlerLoginResponse(Response response, StringBuilder responseMess) {
        if (response == null) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return;
        }

        if (response.getCode().equals(ResponseCode.OK_LOGIN_CODE)) {
            responseMess.append(ResponseMessage.OK_MESS);
            return;
        }

        if (response.getCode().equals(ResponseCode.WRONG_USER_OR_PASSWORD_LOGIN_CODE)) {
            responseMess.append(ResponseMessage.WRONG_USER_OR_PASSWORD_MESS);
            return;
        }

        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return;
        }

        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
    }

    /**
     * This function is to handler sign up response
     *
     * @param responseMess: save response message after handler response
     * @param response:     response
     */
    @Override
    public void handlerSignUpResponse(Response response, StringBuilder responseMess) {
        if (response == null) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return;
        }

        if (response.getCode().equals(ResponseCode.OK_SIGN_UP_CODE)) {
            responseMess.append(ResponseMessage.OK_MESS);
            return;
        }

        if (response.getCode().equals(ResponseCode.NOT_ALLOWED_CHARACTER_SIGN_UP_CODE)) {
            responseMess.append(ResponseMessage.NOT_ALLOWED_CHARACTER_MESS);
            return;
        }

        if (response.getCode().equals(ResponseCode.NO_USER_OR_PASSWORD_SIGN_UP_CODE)) {
            responseMess.append(ResponseMessage.NO_USER_OR_PASSWORD_SIGN_UP_MESS);
            return;
        }


        if (response.getCode().equals(ResponseCode.EXITED_USER_SIGN_UP_CODE)) {
            responseMess.append(ResponseMessage.EXITED_USER_MESS);
            return;
        }

        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return;
        }

        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
    }

    /**
     * This function is to handler log out response
     *
     * @param responseMess: save response message after handler response
     * @param response:     response
     */
    @Override
    public void handlerLogoutResponse(Response response, StringBuilder responseMess) {
        if (response == null) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return;
        }

        if (response.getCode().equals(ResponseCode.OK_LOG_OUT_CODE)) {
            responseMess.append(ResponseMessage.OK_MESS);
            return;
        }

        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return;
        }

        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
    }

    /**
     * This function is to handler create event response
     *
     * @param responseMess: save response message after handler response
     * @param response:     response
     */
    @Override
    public EventDTO handlerCreateEventResponse(Response response, StringBuilder responseMess) {
        if (response == null) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return null;
        }

        if (response.getCode().equals(ResponseCode.OK_CREATE_EVENT_CODE)) {
            responseMess.append(ResponseMessage.OK_MESS);
            return dataToAEvent(response.getMessage());
        }

        if (response.getCode().equals(ResponseCode.ILLEGAL_TIME_CREATE_EVENT_CODE)) {
            responseMess.append(ResponseMessage.ILLEGAL_TIME_CREATE_EVENT_MESS);
            return dataToAEvent(response.getMessage());
        }

        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return null;
        }

        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
        return null;
    }

    /**
     * This function is to handler get invitation list response
     *
     * @param responseMess: save response message after handler response
     * @param response:     response
     * @return EventRequestDTO list
     */
    @Override
    public List<EventRequestDTO> handlerGetInvitationListResponse(Response response, StringBuilder responseMess) {

        return responseMessToEventInvitationList(response.getMessage());
    }

    /**
     * This function is to handler get request list response
     *
     * @param responseMess: save response message after handler response
     * @param response:     response
     * @return EventRequestDTO list
     */
    @Override
    public List<EventRequestDTO> handlerGetRequestListResponse(Response response, StringBuilder responseMess) {

        return responseMessToEventRequestList((response.getMessage()));
    }

    /**
     * This function is to handler get user list response
     *
     * @param responseMess: save response message after handler response
     * @param response:     response
     * @return userDTO list
     */
    @Override
    public List<UserDTO> handlerGetUserListResponse(Response response, StringBuilder responseMess) {

        return responseMessToUserList(response.getMessage());
    }

    /**
     * This function is to handler get event list response
     *
     * @param responseMess: save response message after handler response
     * @param response:     response
     * @return eventDTO list
     */
    @Override
    public List<EventDTO> handlerGetEventListResponse(Response response, StringBuilder responseMess) {

        return responseMessToEventList(response.getMessage());
    }

    /**
     * This function is to handler get user attended list response
     *
     * @param responseMess: save response message after handler response
     * @param response:     response
     * @return user list
     */
    @Override
    public List<UserDTO> handlerGetUserAttendListResponse(Response response, StringBuilder responseMess) {

        return responseMessToUserList(response.getMessage());
    }

    /**
     * This function is to handler get reply request list response
     *
     * @param responseMess: save response message after handler response
     * @param response:     response
     * @return reply list
     */
    @Override
    public List<ReplyDTO> handlerGetRequestReplyListResponse(Response response, StringBuilder responseMess) {

        return responseMessToReplyList(response.getMessage());
    }

    /**
     * This function is to handler get reply invitation list response
     *
     * @param responseMess: save response message after handler response
     * @param response:     response
     * @return reply list
     */
    @Override
    public List<ReplyDTO> handlerGetInvitationReplyListResponse(Response response, StringBuilder responseMess) {

        return responseMessToReplyList(response.getMessage());
    }

    /**
     * This function is to handler request response
     *
     * @param responseMess: save response message after handler response
     * @param response:     response
     * @return 0 if success
     */
    @Override
    public int handlerRequestResponse(Response response, StringBuilder responseMess) {
        if (response == null) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return -1;
        }

        if (response.getCode().equals(ResponseCode.OK_REQUEST_CODE)) {
            responseMess.append(ResponseMessage.OK_MESS);
            return 0;
        }

        if (response.getCode().equals(ResponseCode.NOT_EXIST_RECEIVER_OR_SENDER_REQUEST_CODE)) {
            responseMess.append(ResponseMessage.NOT_EXIST_RECEIVER_OR_SENDER_MESS);
            return -1;
        }

        if (response.getCode().equals(ResponseCode.REQUESTED_REQUEST_CODE)) {
            responseMess.append(ResponseMessage.REQUESTED_REQUEST_MESS);
            return -1;
        }

        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return -1;
        }

        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
        return -1;
    }

    /**
     * This function is to handler reply request response
     *
     * @param responseMess: save response message after handler response
     * @param response:     response
     * @return 0 if success
     */
    @Override
    public int handlerReplyRequestResponse(Response response, StringBuilder responseMess) {
        if (response == null) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return -1;
        }

        if (response.getCode().equals(ResponseCode.OK_REPLY_REQUEST_CODE)) {
            responseMess.append(ResponseMessage.OK_MESS);
            return 0;
        }

        if (response.getCode().equals(ResponseCode.REPLIED_REQUEST_CODE)) {
            responseMess.append(ResponseMessage.REPLIED_REQUEST_MESS);
            return -1;
        }

        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return -1;
        }

        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
        return -1;
    }

    /**
     * This function is to handler invitation response
     *
     * @param responseMess: save response message after handler response
     * @param response:     response
     * @return 0 if success
     */
    @Override
    public int handlerInviteResponse(Response response, StringBuilder responseMess) {
        if (response == null) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return -1;
        }

        if (response.getCode().equals(ResponseCode.OK_INVITATION_CODE)) {
            responseMess.append(ResponseMessage.OK_MESS);
            return 0;
        }

        if (response.getCode().equals(ResponseCode.NOT_EXIST_RECEIVER_OR_SENDER_INVITATION_CODE)) {
            responseMess.append(ResponseMessage.NOT_EXIST_RECEIVER_OR_SENDER_MESS);
            return -1;
        }

        if (response.getCode().equals(ResponseCode.INVITED_INVITATION_CODE)) {
            responseMess.append(ResponseMessage.INVITED_INVITATION_MESS);
            return -1;
        }

        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return -1;
        }

        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
        return -1;
    }

    /**
     * This function is to handler reply invitation response
     *
     * @param responseMess: save response message after handler response
     * @param response:     response
     * @return 0 if success
     */
    @Override
    public int handlerReplyInvitationResponse(Response response, StringBuilder responseMess) {
        if (response == null) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return -1;
        }

        if (response.getCode().equals(ResponseCode.OK_REPLY_INVITATION_CODE)) {
            responseMess.append(ResponseMessage.OK_MESS);
            return 0;
        }

        if (response.getCode().equals(ResponseCode.REPLIED_INVITATION_CODE)) {
            responseMess.append(ResponseMessage.REPLIED_INVITATION_MESS);
            return -1;
        }

        if (response.getCode().equals(ResponseCode.WRONG_REQUEST_CODE)) {
            responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
            return -1;
        }

        responseMess.append(ResponseMessage.SOMETHING_WRONG_MESS);
        return -1;
    }
}
