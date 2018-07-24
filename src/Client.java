

import java.io.*;
import java.net.Socket;

public class Client {
    public  static  void  main(String[] args) {
        try {
            Socket socket = new Socket("localhost",12345);
            OutputStream os = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(os);
            printWriter.write("are you ok ?");

            // 刷新写入流
            printWriter.flush();

            socket.shutdownOutput();

            // 打开接收流
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String str = null;
            do {
                str = bufferedReader.readLine();
                if(str != null){
                    System.out.println("服务器："+ str);
                }
            }while (str != null);
            socket.shutdownInput();

            // 关闭流写入流
            printWriter.close();
            os.close();
            // 关闭读取流
            bufferedReader.close();
            inputStream.close();
            // 关闭Socket
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
