

import java.io.*;
import java.net.Socket;

public class ThreadSocket extends Thread {
   private Socket socket = null;

    /**
     * 构造函数
     * @param socket socket对象
     */
    public  ThreadSocket(Socket socket){
        this.socket = socket;
    }


    @Override()
    public void run() {
        InputStreamReader inputStreamReader =  null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = null;
        InputStream is = null;
        OutputStream os = null;
        PrintWriter pw = null;
        try {
            is = socket.getInputStream();
             inputStreamReader = new InputStreamReader(is);
             bufferedReader = new BufferedReader(inputStreamReader);
             stringBuilder = new StringBuilder();
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
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            pw.write("我在！！");
            // 刷新流 以输出
            pw.flush();
            socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(is != null) is.close();
                if (inputStreamReader != null) inputStreamReader.close();
                if (bufferedReader != null) bufferedReader.close();
                if(pw != null)    pw.close();
                if(os !=null) os.close();
                if(socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
