package SocketTool;

import HtmlTool.UserInfo;
import HtmlTool.UserJsonGenerator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.LoadClass;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

public class UdpBroadcast extends Thread {
    public UserInfo localuser;
    public UdpBroadcast(UserInfo user){
        localuser = user;
    }
    public void UpdateUser(UserInfo user){
        localuser = user;
    }
    public Timer timecount = new Timer();
    @Override
    public void run() {
        timecount.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Broadcast();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0,2000);

    }
    //广播程序端口为65432
    //默认监听端口是65433


    //传入本地用户信息，调用此方法则自动发送报文
    public void Broadcast() throws IOException {
    DatagramSocket socket = new DatagramSocket(65432);
    socket.setBroadcast(true);

    //生成用户json信息

    String UserJson = UserJsonGenerator.getUserJson(this.localuser);
    //打包

    DatagramPacket packet = new DatagramPacket(UserJson.getBytes(),UserJson.length(), InetAddress.getByName("255.255.255.255"),65433);
    socket.send(packet);
    socket.close();

}


}
