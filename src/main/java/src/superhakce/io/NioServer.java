package src.superhakce.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class NioServer {

    private static final Long CHANNEL_LENGTH = 10000L;

    private static final Integer MESSAGE_LENGTH = 1024;

    private static List<SocketChannel> socketChannelList;

    static {
        socketChannelList = new ArrayList<>(MESSAGE_LENGTH);
    }

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress("localhost", 9000));
        serverSocketChannel.configureBlocking(false);
        System.out.println("服务启动成功 ...");
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(Objects.nonNull(socketChannel)){
                System.out.println("连接成功 ...");
                socketChannel.configureBlocking(false);
                socketChannelList.add(socketChannel);
            }
            Iterator<SocketChannel> iterator = socketChannelList.listIterator();
            while (iterator.hasNext()){
                SocketChannel channel = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(MESSAGE_LENGTH);
                int read = channel.read(byteBuffer);
                if(read > 0){
                    System.out.println("接收到消息: " + new String(byteBuffer.array(), 0 , read));
                }else if(read == -1){
                    iterator.remove();
                    System.out.println("客户端断开连接 ...");
                }
            }
        }
    }

}
