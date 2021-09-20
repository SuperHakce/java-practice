package src.superhakce.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SocketServer {

    private static ThreadPoolExecutor executor;

    static {
        executor = new ThreadPoolExecutor(10, 100, 100,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));
        executor.prestartCoreThread();
    }

    public void start() throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            while(true){
                System.out.println("等待连接 ...");
                Socket socket = serverSocket.accept();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            handler(socket);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executor.shutdownNow();
            executor.shutdown();
        }
    }

    private void handler(Socket socket) throws IOException {
        byte[] bytes = new byte[1024];
        System.out.println("准备read ...");
        int read = socket.getInputStream().read(bytes);
        if(read != -1){
            System.out.println("接收到客户端的数据：" + new String(bytes, 0, read));
        }
    }

}
