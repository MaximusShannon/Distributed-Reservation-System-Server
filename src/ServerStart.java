import com.maxshannon.functionality.RequestReceiver;

public class ServerStart {

    public static void main(String[] args){

        RequestReceiver server = new RequestReceiver();
        server.receiveLoginRequest();
    }
}
