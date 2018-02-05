import com.maxshannon.functionality.LoginRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RequestReceiver {

    private int port = 4000;
    private ServerSocket serverSocket;

    public RequestReceiver(){


    }

    public void recieveLoginRequest(){

        try{

            serverSocket = new ServerSocket(port);
            System.out.println("Socket Established");

            while (true){

                Socket clientSocket = serverSocket.accept();


                ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream());

                LoginRequest recievedRequest = (LoginRequest) inFromClient.readObject();
                System.out.println("Object Read:  request username: " + recievedRequest.getUsername());

                clientSocket.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
