package com.example.eventmanager.model;

import com.example.eventmanager.Constain.SocketConfig;
import com.example.eventmanager.utils.IRequestHandler;
import com.example.eventmanager.utils.request.RequestHandler;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static com.example.eventmanager.Constain.SocketConfig.BUFF_SIZE;
import static com.example.eventmanager.Constain.SocketConfig.DELIMITER;


public class Client {
    private static Client client;
    private Socket clientSocket;

    private DataInputStream in;
    private DataOutputStream out;
    private StringBuilder cache;

    private int ret;

    private Client() {
        this.cache = new StringBuilder("");
    }

    public static Client getClient() {
        if (client == null) {
            client = new Client();
        }

        return client;
    }

    public int connect() {
        try {
            this.clientSocket = new Socket(SocketConfig.SERVER_ADDRESS, SocketConfig.SERVER_PORT);
            System.out.println("Connected");

            // takes input from terminal
            //this.input = new DataInputStream(System.in);

            // sends output to the socket
            this.out = new DataOutputStream(this.clientSocket.getOutputStream());

            this.in = new DataInputStream(
                    new BufferedInputStream(this.clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

        return 0;
    }

    public void communicateToServer() {
        if (clientSocket == null) {
            System.out.println("You have not connected to server yet");
            return;
        }

        // string to read message from input
        String line = "";

        // communicate to server
        while (true) {

            // send message
            try {
                line = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            IRequestHandler requestHandler = new RequestHandler();

            requestHandler.sendLogOutRequest();

//            ret = send(line, line.length());
//            if (ret < 0) {
//                System.out.println("Error send data!");
//                return;
//            }

            // receive message from server
            ret = recv();
            if (ret < 0) {
                System.out.println("Error receive data!");
                return;
            }
        }// end while
    }

    public int send(String buffSend, int size) {
        int left = size;
        StringBuilder mess = new StringBuilder(buffSend);
        int start = 0;

        try {
            while (left >= BUFF_SIZE) {
                String s = mess.substring(start, start + BUFF_SIZE);
                left = left - BUFF_SIZE;
                start = start + BUFF_SIZE;
                this.out.writeBytes(s);
            }

            if (left > 0) {
                String s = mess.substring(start, start + left);
                this.out.writeBytes(s);
            }
            System.out.println("buffSend=" + buffSend);

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

        return size;
    }

    public int recv() {
        String line = "";

        try {
            line = this.in.readLine();
            this.cache.append(line);
            System.out.println("buffRecv=" + this.cache.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

        return this.cache.length();
    }

    public void close() {
        // close the connection
        try {
            this.in.close();
            this.out.close();
            this.clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StringBuilder getCache() {
        return cache;
    }

    public static void main(String args[]) {
        Client client1 = Client.getClient();

        // connection to server
        if (client1.connect() != 0) {
            System.out.println("Can not connect to server!");
            return;
        }

        // communicate to server
        client1.communicateToServer();
        client1.close();
    }

}
