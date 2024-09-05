package MsgProcessor;

import HtmlTool.StringCodeSolver;
import HtmlTool.UserLogger;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.Socket;


public class ReciverThread extends  Thread{
    private Socket client;
    private BufferedReader in;
    private DataOutputStream dp;
    private String msg;
    private String cachePath;
    private FileInputStream fis;
    public ReciverThread(Socket client,String path) {
        super();
        this.client = client;
        this.cachePath = path;
        try {
            this.start();
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));// 得到客户端的输入流
            System.out.println((msg=in.readLine()));//控制台输入信息并保存到字符串中
            System.out.println("RT msg"+msg);
            JSONObject msgJson = JSON.parseObject(msg);//获取信息格式并生成jsonobject
            //判断消息类型
            if(msgJson.get("type").equals("reqFile")){
                //如果是请求发送文件则返回相应的文件
                //获取请求的文件  此处要改为非加密的话就得把sender改了
                File file = new File(cachePath+File.separator+StringCodeSolver.Decode( (String) msgJson.get("sender")) +File.separator+ msgJson.get("cachefolder")+File.separator+msgJson.get("filename"));

                //获取文件输入流
                fis = new FileInputStream(file);
                //获取输出流
                dp = new DataOutputStream(client.getOutputStream());
                byte[] bytes = new byte[1024];
                int length = 0;
                //传输文件
                while ((length = fis.read(bytes, 0, bytes.length)) != -1) {
                    System.out.println("REciverThread send:51"+length);
                    dp.write(bytes, 0, length);
                    dp.flush();
                }
                //传完就关闭
                in.close();
                fis.close();
                dp.close();
                client.close();
            }else if(msgJson.get("type").equals("msg")){
                //为消息类型则记录日志并调用函数发送文件请求 sender本为加密，此处解密保存到相应文件夹
                System.out.println("ReciverThread:60:" +StringCodeSolver.Decode((String) msgJson.get("sender")));
                UserLogger.Log(cachePath+File.separator+ StringCodeSolver.Decode((String) msgJson.get("sender")) +File.separator +"log.txt",msg);

                MessageSender messageSender = new MessageSender();
                for(int i=1;i<=(int)msgJson.get("imgnum");i++){
                    //在MessageSender中发送文件请求并直接在MessageSender中接收
                    //这里从对方发来的msg中sender 和 reciver是相反的，所以应该反着填(谁发谁就是sender)
                    messageSender.RequestFile((String) msgJson.get("reciver"),(String) msgJson.get("sender"),(String) msgJson.get("senderip"),"imgCache",(String) msgJson.get("img"+i),cachePath);
                }
                for(int i=1;i<=(int)msgJson.get("filenum");i++){
                    //在MessageSender中发送文件请求并直接在MessageSender中接收
                    //这里从对方发来的msg中sender 和 reciver是相反的，所以应该反着填(谁发谁就是sender)
                    messageSender.RequestFile((String) msgJson.get("reciver"),(String) msgJson.get("sender"),(String) msgJson.get("senderip"),"fileCache",(String) msgJson.get("file"+i),cachePath);
                }

            }else {
                System.out.println("ReciverThread:76:error");
            }

        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}
