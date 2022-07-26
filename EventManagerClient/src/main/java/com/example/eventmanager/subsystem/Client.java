package com.example.eventmanager.subsystem;

import com.example.eventmanager.Constain.SocketConfig;

import java.io.*;
import java.net.Socket;

import static com.example.eventmanager.Constain.SocketConfig.BUFF_SIZE;


/**
 * This class is the client connecting to server
 *
 * @author hoangnguyenthe20183925
 */
public class Client {
    private static Client client;//only client over all application
    private Socket clientSocket;//socket of client

    private PrintWriter out;//to send message
    private BufferedReader in;//to receive message

    private StringBuilder cache;//save message block

    /**
     * Constructor client
     */
    private Client() {
        this.cache = new StringBuilder("");
    }

    /**
     * Get only client object
     * Single Skeleton design pattern
     */
    public static Client getClient() {
        if (client == null) {
            client = new Client();
        }

        return client;
    }

    /**
     * This function to connect to server and instance out, in
     */
    public int connect() {
        try {
            //connect server
            this.clientSocket = new Socket(SocketConfig.SERVER_ADDRESS, SocketConfig.SERVER_PORT);
            System.out.println("Connected");

            // instance out and in
            this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

        return 0;
    }

    /**
     * This function is to send message to server
     *
     * @param buffSend: message
     * @param size:     length of message
     */
    public int send(String buffSend, int size) {
        int left = size;//save num of left byte to send
        StringBuilder mess = new StringBuilder(buffSend);//converse to stringbuilder
        int start = 0;

        try {
            //send each block with BUFF_SIZE
            while (left >= BUFF_SIZE) {
                String s = mess.substring(start, start + BUFF_SIZE);
                left = left - BUFF_SIZE;
                start = start + BUFF_SIZE;
                this.out.print(s);//copy message to socket cache
            }

            //send final block
            if (left > 0) {
                String s = mess.substring(start, start + left);
                left = left - s.length();
                this.out.print(s);//copy message to socket cache
            }
            this.out.flush();
            System.out.println("buffSend=" + buffSend);

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

        return size;
    }

    /**
     * This function is to receive message
     */
    public int recv() {
        String line = "";

        try {
            System.out.println("receiving");
            line = this.in.readLine();//read message from socket cache
            this.cache.append(line);//append to cache of client
            System.out.println("buffRecv=" + this.cache.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

        return this.cache.length();
    }

    /**
     * This function is to close socket
     */
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

    /**
     * This function is to get cache
     */
    public StringBuilder getCache() {
        return cache;
    }

}
