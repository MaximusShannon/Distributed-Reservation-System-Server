package com.maxshannon.functionality;

import com.maxshannon.functionality.DatabaseConnection;
import com.maxshannon.functionality.LoginRequest;
import com.maxshannon.models.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RequestReceiver {

    private int port = 4000;
    private ServerSocket serverSocket;
    User authenticatedUser = null;

    public RequestReceiver(){


    }

    public void receiveLoginRequest(){

        try{

            serverSocket = new ServerSocket(port);
            System.out.println("Socket Established");

            while (true){
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream());

                LoginRequest recievedRequest = (LoginRequest) inFromClient.readObject();
                System.out.println("Object Read:  request username: " + recievedRequest.getUsername());

                authenticatedUser = authenticateLoginRequest(recievedRequest);
                outToClient.writeObject(authenticatedUser);

                clientSocket.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private User authenticateLoginRequest(LoginRequest requestReceived) {

        boolean exists;
        User retreivedUser = null;

        DatabaseConnection dbConnection = new DatabaseConnection();
        exists = dbConnection.checkIfUserExists(requestReceived.getUsername());

        if (exists) {

            retreivedUser = dbConnection.retreiveUser(requestReceived.getUsername());
        }

        return retreivedUser;

    }
}
