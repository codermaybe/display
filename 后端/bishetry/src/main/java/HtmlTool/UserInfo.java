package HtmlTool;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.json.JsonObject;
import java.io.UnsupportedEncodingException;
import java.net.*;

//姓名和备注在userinfo中同一为加密格式，要获取未加密格式使用getDecode方法


public class UserInfo{
    String name = "user";
    String backinfo = "backinfo";
    String status = "";
    double id= 0;
    boolean isMale = true;
    String logintime = "";
    boolean alreadychatted = false;
    //连接建立所需信息

    String userip = "";


    public String getIP(){
        InetAddress ip4 = null;
        try {
          ip4 = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return  ip4.getHostAddress();
    }



    public UserInfo(String name,String backinfo,boolean ismale) throws UnsupportedEncodingException {
        this.name = name;
        this.backinfo = backinfo;
        this.isMale = ismale;
        this.status = "online";
        this.logintime = new TimeSetter().gettime();
        this.id = Math.random();
        this.alreadychatted = false;
        this.userip = getIP();
    }


    public UserInfo(String Json) throws UnsupportedEncodingException {
        JSONObject userJson = JSON.parseObject(Json);

        this.name = userJson.getString("name") ;
        this.backinfo = userJson.getString("backinfo");
        this.isMale = userJson.getBoolean("ismale");
        this.status = userJson.getString("status");
        this.logintime = userJson.getString("logintime");
        this.id = userJson.getDouble("id");
        this.alreadychatted = userJson.getBoolean("alreadychatted");
        this.userip = userJson.getString("ip");

    }





    //避免json转换中文出现乱码，所以用urlencoder加密(urldecoder解密),仅在set方法写(弃用)
    public String getName()  { return this.name; }

    public void setName(String name) throws UnsupportedEncodingException {
        this.name = name;
    }

    public String getBackinfo() throws UnsupportedEncodingException { return this.backinfo; }

    public void setBackinfo(String backinfo) throws UnsupportedEncodingException {
        this.backinfo = backinfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getLogintime() {
        return logintime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }
    public boolean isAlreadychatted() {
        return alreadychatted;
    }

    public void setAlreadychatted(boolean alreadychatted) {
        this.alreadychatted = alreadychatted;
    }
}