package functionality;

import models.ResponseMessage;
import models.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RequestReceiver {

    private int port = 4000;
    private ServerSocket serverSocket;
    User authenticatedUser = null;

    private ResponseMessage response;

    public RequestReceiver(){

    }

    public void receiveRegisterRequest(){

        response = new ResponseMessage();

        try{
            serverSocket = new ServerSocket(4045);
            System.out.println("Socket Established @ receiveRegisterRequest");


            while(true){
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream());

                User newUser = (User) inFromClient.readObject();

                DatabaseInteractionService dbService = new DatabaseInteractionService();
                if(dbService.checkDoesUserExist(newUser)){
                    response.setMessage("USER_EXISTS");
                }else {
                    dbService.persistUser(newUser);
                    response.setMessage("USER_REGISTERED");
                }

                outToClient.writeObject(response);
                clientSocket.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void receiveLoginRequest(){

        try{

            serverSocket = new ServerSocket(port);
            System.out.println("Socket Established @ receiveLoginRequest");

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
