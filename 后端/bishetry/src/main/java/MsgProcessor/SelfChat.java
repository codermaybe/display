package MsgProcessor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.File;

public class SelfChat {
    public static String historyMsg(){
        return "<li class=\"d-flex message divider mt-xl-5 mt-md-3 mb-xl-5 mb-md-3\">\n" +
                "<small class=\"text-muted\">历史消息</small>\n" +
                "</li>";
    }
    //生成发送时间的样式
    public static String getselfTimeSpan(String json){
        String time = (String) JSON.parseObject(json).get("time");
        return "<span class=\"date-time text-muted\">时间替换<i class=\"zmdi zmdi-check-all text-primary\"></i></span>".replace("时间替换",time);
    }
    //生成文本消息和img部分的div
    public static String getselfTextImgFont(String json,String path){
        JSONObject parseObject = JSON.parseObject(json);
        String text = (String) parseObject.get("text");
        int imgcount = parseObject.getIntValue("imgnum");
        if (text.equals("")&&imgcount==0){
            return "";
        }
        String font1 ="<div class=\"message-row d-flex align-items-center justify-content-end\">\n" +
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
                "\t\t\t\t\t\t<div class=\"attachment right-file\">\n" +
                        "\t\t\t\t\t\tstr替换"+
                        "\t\t\t\t\t\t</div>\n";
        String imgs = "<img class=\"rounded mt-1\" src=\"图片源替换\" >\n";
        for (int i=1;i<=imgcount;i++){
            str.append(imgs.replace("图片源替换",path+ File.separator +"imgCache"+File.separator+"Recive"+json.get("img"+i)));
        }
        return imgFont.replace("str替换",str);
    }
    //生成文件部分div
    public static String getSelfFileFont(String json,String path){
        JSONObject msg = JSON.parseObject(json);
        int filecount = 0;
        if ((filecount=msg.getIntValue("filenum"))==0){return "";}

        String FileFont ="<div class=\"message-row d-flex align-items-center justify-content-end\">\n" +
                "\t\t\t\t\t<div class=\"message-content border p-3\">\n" +
                "\t\t\t\t\t\t<div class=\"attachment\">\n" +
                "文件更换" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</div>";

        return FileFont.replace("文件更换",getselfFiles(json));
    }

    public static String getselfFiles(String json) {
        JSONObject msg = JSON.parseObject(json);
        StringBuilder str = new StringBuilder();
        String font = "\t\t<div class=\"media mt-2\">\n" +
                "\t\t\t\t\t\t\t\t<div class=\"avatar me-2\">\n" +
                "\t\t\t\t\t\t\t\t\t<div class=\"avatar rounded no-image cyan\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<i class=\"zmdi zmdi-file\"></i>\n" +
                "\t\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t\t<div class=\"media-body overflow-hidden\">\n" +
                "\t\t\t\t\t\t\t\t\t<h6 class=\"text-truncate mb-0\">文件名称更换</h6>\n" +
                "\t\t\t\t\t\t\t\t\t<span class=\"file-size\">文件大小更换</span>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t</div>";

        for (int i = 1; i <= msg.getIntValue("filenum"); i++) {
            str.append(font.replace("文件名称更换", (String) msg.get("file" + i))
                    .replace("文件大小更换", msg.getString("filesize"+i)+"mb")
            );
        }
        return str.toString();
    }
    //生成完整的聊天部分
    public static String getselfMsg(String json,String path){
        String li="<li class=\"d-flex message right\">\n" +
                "\t\t\t<div class=\"message-body\">\n" +
                "发送时间替换\n" +
                "发送文本和图片替换"+
                "发送附件替换"+
                "\t\t\t</div>\n" +
                "\t\t</li>";
        return li.replace("发送时间替换",getselfTimeSpan(json))
                .replace("发送文本和图片替换",getselfTextImgFont(json,path))
                .replace("发送附件替换",getSelfFileFont(json,path)) ;


    }




    public static void main(String[] args) {
        String json = "{\"filenum\":3,\"reciverip\":\"192.168.137.1\",\"type\":\"msg\",\"reciver\":\"%E5%BE%90%E6%9F%90%E4%BA%BA%E5%95%8A0.6992213647066313\",\"sender\":\"%E5%BE%90%E6%9F%90%E4%BA%BA%E5%95%8A0.6992213647066313\",\"file2\":\"api-ms-win-core-datetime-l1-1-0.dll\",\"file3\":\"api-ms-win-core-debug-l1-1-0.dll\",\"text\":\"fuckyou\",\"file1\":\"api-ms-win-core-console-l1-1-0.dll\",\"time\":\"17:13\",\"imgnum\":1,\"senderip\":\"192.168.137.1\",\"img1\":\"autobackup.ico\"}";


        System.out.println(getselfMsg(json,"fuck"));
    }



}