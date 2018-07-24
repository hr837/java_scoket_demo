

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void  main(String[] args){
        try {
            // 创建webSocket服务端
            ServerSocket serverSocket = new ServerSocket(12345);
            int count = 0;

            // 打开监听器
            System.out.println("服务端webSocket开始监听....");

            while(true){
                Socket socket = serverSocket.accept();
                ThreadSocket threadSocket = new ThreadSocket(socket);
                threadSocket.start();

                // 统计客户端数量
                count ++;
                System.out.println("客户端数量：" + count);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
