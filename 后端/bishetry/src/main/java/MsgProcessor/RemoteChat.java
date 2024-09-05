package MsgProcessor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.awt.*;

public class RemoteChat {
    public static String historyMsg(){
        return "<li class=\"d-flex message divider mt-xl-5 mt-md-3 mb-xl-5 mb-md-3\">\n" +
                "<small class=\"text-muted\">历史消息</small>\n" +
                "</li>";
    }
    public static String getHeadIcon(boolean ismale){
        if(ismale){
            return "<div class=\"mr-lg-3 me-2\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t<img class=\"avatar sm rounded-circle\" src=\"assets/images/xs/avatar2.jpg\"  alt=\"avatar\">\n" +
                    "\t\t\t\t\t\t\t\t\t</div>";
        }else {
            return "<div class=\"mr-lg-3 me-2\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t<img class=\"avatar sm rounded-circle\" src=\"assets/images/xs/avatar3.jpg\"  alt=\"avatar\">\n" +
                    "\t\t\t\t\t\t\t\t\t</div>";
        }
    }
    //获取姓名和发送时间
    public static String getNameAndTime(String json,String senderName){
        JSONObject msg  = JSON.parseObject(json);
        String font = "\t\t\t\t\t\t\t\t\t\t<span class=\"date-time text-muted\">姓名时间替换</span>\n";
        return  font.replace("姓名时间替换",senderName+','+msg.get("time"));
    }
    //聊天队形消息和图片的生成

    public static String getRemoteTextImg(String json,String path){
        JSONObject parseObject = JSON.parseObject(json);
        String text = (String) parseObject.get("text");
        int imgcount = parseObject.getIntValue("imgnum");
        if (text.equals("")&&imgcount==0){
            return "";
        }
        String font1 ="<div class=\"message-row d-flex align-items-center\">\n" +
                "\t\t\t\t<div class=\"message-content border p-3\">\n" +
                "\t\t\t\t\t\t文字更换\n" +
                "图片更换"+
                "\t\t\t\t</div>\n" +
                "</div>";

        return font1.replace("文字更换",text)
                .replace("图片更换",imgReplace(parseObject,path));
    }

    public static String imgReplace(JSONObject json,String path){
        StringBuilder str = new StringBuilder();
        int imgcount;
        if ((imgcount=json.getIntValue("imgnum"))==0){return "";}
        String imgFont =
                "\t\t\t\t\t\t<div class=\"attachment\">\n" +
                        "\t\t\t\t\t\tstr替换"+
                        "\t\t\t\t\t\t</div>\n";
        String imgs = "<img class=\"rounded mt-1\" src=\"图片源替换\" >\n";
        for (int i=1;i<=imgcount;i++){
            str.append(imgs.replace("图片源替换",path+"Recive"+json.get("img"+i)));
        }
        return imgFont.replace("str替换",str);
    }
    //获取附件
    public static String getRemoteFileFont(String json){
        JSONObject files = JSON.parseObject(json);
        if(files.getIntValue("filenum")==0){return "";}
        String font = "<div class=\"message-row d-flex align-items-center\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<div class=\"message-content p-3\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"attachment\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t附件替换\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t</div>";
        return font.replace("附件替换",getRemoteFiles(json));
    }

    public static String getRemoteFiles(String json){
        JSONObject files  = JSON.parseObject(json);
        int filenum = files.getIntValue("filenum");
        StringBuilder stringBuilder = new StringBuilder();
        String font = "<div class=\"media mt-2\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"avatar me-2\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"avatar rounded no-image cyan\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"zmdi zmdi-file\"></i>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"media-body overflow-hidden\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<h6 class=\"text-truncate mb-0\">文件名替换</h6>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"file-size\">文件大小替换</span>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</div>";

        for (int i=1;i<=filenum;i++){
            stringBuilder.append(font.replace("文件名替换",(String)files.get("file"+i))
                    .replace("文件大小替换",(String)files.get("filesize"+i))
            );
        }
        return stringBuilder.toString();
    }



    public static String getRemoteMsg(String json,String path,boolean ismale,String senderName){
        String li = "<li class=\"d-flex message\">\n" +
                "\n" +
                "头部icon替换"+
                "\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"message-body\">\n" +
                "姓名时间替换" +
                "\t\t\t\t\t\t\t\t\t\t\n" +
                "聊天文本和图片替换"+"\n"+
                "附件样式替换"+
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</li>";




        return li.replace("头部icon替换",getHeadIcon(ismale))
                .replace("姓名时间替换",getNameAndTime(json,senderName))
                .replace("聊天文本和图片替换",getRemoteTextImg(json,path))
                .replace("附件样式替换",getRemoteFileFont(json));
    }

    public static void main(String[] args) {
        String json = "{\"filenum\":3,\"reciverip\":\"192.168.137.1\",\"type\":\"msg\",\"reciver\":\"%E5%BE%90%E6%9F%90%E4%BA%BA%E5%95%8A0.6992213647066313\",\"sender\":\"%E5%BE%90%E6%9F%90%E4%BA%BA%E5%95%8A0.6992213647066313\",\"file2\":\"api-ms-win-core-datetime-l1-1-0.dll\",\"file3\":\"api-ms-win-core-debug-l1-1-0.dll\",\"text\":\"fuckyou\",\"file1\":\"api-ms-win-core-console-l1-1-0.dll\",\"time\":\"17:13\",\"imgnum\":1,\"senderip\":\"192.168.137.1\",\"img1\":\"autobackup.ico\"}";

        System.out.println(getRemoteMsg(json,"fuckpath",true,"jack"));


    }



}