package MsgProcessor;


import HtmlTool.StringCodeSolver;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.*;

public class MessageSender  {
    //被调用，用于发送msg和发送reqfile(并保存文件)
    private  Socket socket;
    private  PrintWriter out;//相当于向外写文件，所以用out
    private  FileOutputStream fos;
    private  DataInputStream dis;
    private static int count = 1;




    //发送文件请求并接收
    //这里的sender和reciver还是加密姓名拼接字符串
    //cachePath应该是绝对路径 xxx\CaChe
    public void RequestFile(String sender,String reciver,String reciverip,String cachefolder,String filename,String cachepath) throws IOException {
        JSONObject req = new JSONObject();
        //文件请求包括类型和文件名和发送者标识和缓存文件夹
        //给msgreciver的类型标识
        req.put("type","reqFile");
        //请求的文件名
        req.put("filename",filename);
        //本地用户的名字
        req.put("sender",sender);
        //按照图片文件和其他文件来区分
        req.put("cachefolder",cachefolder);
        //生成文件请求消息
        String reqmsg = req.toJSONString();

        try {
            socket = new Socket(reciverip, 65434);

            out = new PrintWriter(socket.getOutputStream(), true);

            out.println(reqmsg);// 输入信息到服务器
            out.flush();

            //通过路径建立文件并开始写入接收者相应的文件夹内 这里文件夹解密
            File file = new File(cachepath+File.separator+ StringCodeSolver.Decode(reciver) +File.separator+cachefolder+File.separator+"Recive"+filename);
            fos = new FileOutputStream(file);
            dis = new DataInputStream(socket.getInputStream());
            byte[] bytes = new byte[1024];
            int length = 0;
            while((length = dis.read(bytes, 0, bytes.length)) != -1) {
                System.out.println("MsgSender  getRequestFile :"+length);
                fos.write(bytes, 0, length);
                fos.flush();
            }
            //传输成功后关闭
            out.close();
            fos.close();
            dis.close();
            socket.close();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void SendMsg(String msg){
        //这里的msg已经在uploadServlet接收并生成，只需发送
        JSONObject msgjson = JSON.parseObject(msg);
        try {
            socket = new Socket((String) msgjson.get("reciverip"), 65434);
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(msg);// 输入信息到服务器
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
