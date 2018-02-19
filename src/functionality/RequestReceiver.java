package functionality;

import models.ResponseMessage;
import models.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

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
            System.out.println("Socket Established @ awaiting Register requests...");


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

            DatabaseInteractionService dbService = new DatabaseInteractionService();
            User tempUser = new User();
            User userRetrieved = null;

            serverSocket = new ServerSocket(port);
            System.out.println("Socket Established @ awaiting Login requests...");

            while (true){
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream());

                LoginRequest recievedRequest = (LoginRequest) inFromClient.readObject();

                System.out.println(recievedRequest.getUsername());

                tempUser.setEmail(recievedRequest.getUsername());
                tempUser.setPassword(recievedRequest.getPassword());

                if(dbService.checkDoesUserExist(tempUser)){

                    userRetrieved = dbService.getExistingUser(tempUser.getEmail());

                    if(BCrypt.checkpw(tempUser.getPassword(), userRetrieved.getPassword())){

                        outToClient.writeObject(userRetrieved);
                    }

                }else {
                    outToClient.writeObject(userRetrieved);
                }

                clientSocket.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
