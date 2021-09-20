package src.superhakce.io;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) {
        for(int i = 0; i <= 100; i ++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Socket socket = new Socket("localhost", 9000);
                        PrintWriter writer = new PrintWriter(socket.getOutputStream());
                        writer.println(Thread.currentThread().getName());
                        System.out.println(Thread.currentThread().getName());
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
