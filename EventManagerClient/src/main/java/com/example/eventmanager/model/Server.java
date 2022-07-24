package com.example.eventmanager.model;

import com.example.eventmanager.Constain.RequestPrefix;
import com.example.eventmanager.Constain.SocketConfig;
import com.example.eventmanager.utils.IResponseHandler;
import com.example.eventmanager.utils.response.ResponseHandler;

import java.net.*;
import java.io.*;

import static com.example.eventmanager.Constain.SocketConfig.DELIMITER;

public class Server {
    //initialize socket and input stream
    private Socket client;
    private ServerSocket server;
    private DataInputStream input;

    private DataOutputStream out;

    // constructor with port
    public Server(int port) {
        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            // reads message from client until "Over" is sent
            while (true) {
                client = server.accept();
                System.out.println("Client accepted");

                // takes input from the client socket
                input = new DataInputStream(
                        new BufferedInputStream(client.getInputStream()));

                out = new DataOutputStream(this.client.getOutputStream());

                String line = "";
                StringBuilder cache = new StringBuilder("");
                while (true) {
                    try {
                        cache = new StringBuilder("");
                        do {
                            line = input.readUTF();
                            System.out.println("line=" + line);
                            cache.append(line);
                        }while (!cache.substring(cache.length() - 2, cache.length()).equals(DELIMITER));
                        System.out.println("cache="+cache.toString());

                        ResponseHandler responseHandler = new ResponseHandler();
                        Response response = new Response();

                        responseHandler.messToResponse(response, cache.toString());
                        response.setCode(response.getCode() + " ");

                        System.out.println("code=" + response.getCode());

                        if (response.getCode().equals(RequestPrefix.CREATE_NEW_USER)) {
                            out.writeUTF("10\r\n");
                            continue;
                        }

                        if (response.getCode().equals(RequestPrefix.LOG_IN)) {
                            out.writeUTF("20\r\n");
                            continue;
                        }

                        if (response.getCode().equals(RequestPrefix.CREATE_EVENT)) {
                            out.writeUTF("30 1|user1|event1|1/1/2023|HUST|abc|\r\n");
                            continue;
                        }

                        if (response.getCode().equals(RequestPrefix.JOIN_EVENT)) {
                            out.writeUTF("60\r\n");
                            continue;
                        }

                        if (response.getCode().equals(RequestPrefix.GET_INVITATION_LIST)) {
                            out.writeUTF("80 1|user1|event1|1/1/2023|HUST|ds1|#2|user1|event2|1/1/2023|HUST|ds2|#3|user3|event3|1/1/2023|HUST|ds3|#\r\n");
                            continue;
                        }

                        if (response.getCode().equals(RequestPrefix.GET_REQUEST_LIST)) {
                            out.writeUTF("90 user3|1|user1|event1|1/1/2023|HUST|ds1|#user2|2|user1|event22222222222222222222222222222222222222|1/1/2023|HUST|ds2|#\r\n");
                            continue;
                        }

                        if (response.getCode().equals(RequestPrefix.GET_USER_LIST)) {
                            out.writeUTF("100 user1|user2|user3|\r\n");
                            continue;
                        }

                        if (response.getCode().equals(RequestPrefix.GET_EVENT_LIST)) {
                            out.writeUTF("110 1|user1|event1|1/1/2023|HUST|ds1|#2|user1|event2|1/1/2023|HUST|ds2|#3|user3|event3|1/1/2023|HUST|ds3|#\r\n");
                            continue;
                        }

                        if (response.getCode().equals(RequestPrefix.LOG_OUT)) {
                            out.writeUTF("120\r\n");
                            continue;
                        }

                        out.writeUTF("99\r\n");

                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
                if (cache.equals("asdasd"))
                    break;
            }

            System.out.println("Closing connection");

            // close connection
            client.close();
            input.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        Server server = new Server(5500);
    }
}
