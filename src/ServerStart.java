public class ServerStart {

    public static void main(String[] args){

        RequestReceiver server = new RequestReceiver();
        server.recieveLoginRequest();
    }
}
