package MsgProcessor;

import HtmlTool.UserLogger;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.json.Json;
//判断相应从远程接收来的消息的类型并调用相应方法
//type = msg ,存放入log文件。
//type = reqFile,本机像对象发出文件请求并将请求放入相应的文件夹。这里的消息只附带文件名。文件传送需要调用其他的方法
public class MsgJsonProcessor {
    String path = "";
    //传入文件路径
    public MsgJsonProcessor(String filepath){
        this.path = filepath;
    }
    public void judge(String json){
        JSONObject msg = JSON.parseObject(json);
        if(msg.get("type").equals("msg")){
            UserLogger.Log(path, msg.toJSONString());
            int imgcount = (int) msg.get("imgcount");
            int filecount =(int) msg.get("filecount");
            for(int i=0;i<imgcount;i++){

            }
        }

    }
}
