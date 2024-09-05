package SocketTool;

import HtmlTool.UserInfo;
import HtmlTool.UserList;
import com.alibaba.fastjson.JSON;
import sun.awt.windows.ThemeReader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.URLDecoder;
import java.util.HashMap;

public class BroadcastListener extends Thread {
    public DatagramSocket Listener;
    public UserList list;
    public BroadcastListener() throws SocketException {
         this.Listener= new DatagramSocket(65433);
         this.list = new UserList();
    }

    @Override
    public void run() {
        try {
            StartListen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, UserInfo> getHashMapFromListener() throws UnsupportedEncodingException {
         return this.list.userInfoHashMap;
    }
    public void StartListen() throws IOException {

        byte buffer[] = new byte[1024*1024];
        DatagramPacket receivePack = new DatagramPacket(buffer,buffer.length);
        String JsonData = "";

        while (true) {
            Listener.receive(receivePack);
            JsonData = new String(receivePack.getData());
            list.addUserWithJson(JsonData);
        }
    }


}
