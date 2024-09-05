package MsgProcessor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MsgReciver extends ServerSocket implements Runnable {
    //持续监听65434端口
    private static final int SERVER_PORT = 65434;
    public  String path = "";

    @Override
    public void run() {
        try {
            startListen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//这里在jsp中创建时传入的是CaChe的绝对路径xxx\CaChe
    public MsgReciver(String path) throws IOException {
        super(SERVER_PORT,100, InetAddress.getLocalHost());
        this.path = path;
    }
    public void startListen() throws IOException {
        try{
            System.out.println("MsgReciver:启动服务器");
            while(true){
                Socket socket = this.accept();
                new ReciverThread(socket,path);//每当收到一个socket就创建一个线程
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        finally{
            this.close();
        }
    }

}
