package functionality;

import functionality.RequestReceiver;

public class ServerStart {

    public static void main(String[] args){

        RequestReceiver loginServer = new RequestReceiver();
        RequestReceiver registerServer = new RequestReceiver();

        Runnable runnable = loginServer::receiveLoginRequest;
        Runnable registerRunnable = registerServer::receiveRegisterRequest;

        Thread loginThread = new Thread(runnable);
        loginThread.start();
        Thread registerThread = new Thread(registerRunnable);
        registerThread.start();

    }
}
