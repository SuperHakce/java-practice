package src.superhakce;

import src.superhakce.io.SocketServer;

public class Main {

    public static void main(String[] args) throws Exception {
        SocketServer socketServer = new SocketServer();
        socketServer.start();
    }

}
