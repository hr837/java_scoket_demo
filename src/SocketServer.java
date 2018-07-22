import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void  main(String[] args){
        try {
            // 创建webSocket服务端
            ServerSocket serverSocket = new ServerSocket(12345);
            // 打开监听器
            System.out.println("服务端webSocket开始监听....");
            Socket socket = serverSocket.accept();

            //
            InputStream is = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String str;
            do {
                str = bufferedReader.readLine();
                if(str !=null){
                    stringBuilder.append(str);
                }
            } while (str != null);

            System.out.println("服务端Socket接收到的数据为:" +stringBuilder.toString());
            socket.shutdownInput();

            // 向客户端回应
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("我在！！");
            // 刷新流 以输出
            pw.flush();
            socket.shutdownOutput();

            // 关闭写入流
            pw.close();
            os.close();
            bufferedReader.close();
            inputStreamReader.close();
            is.close();
            // 关闭socket 关闭服务端
            socket.close();
            serverSocket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
