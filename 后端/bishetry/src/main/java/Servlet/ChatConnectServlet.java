package Servlet;

import HtmlTool.StringCodeSolver;
import HtmlTool.UserInfo;
import HtmlTool.UserLogger;
import MsgProcessor.RemoteChat;
import MsgProcessor.SelfChat;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


//访问此servlet获取相应的聊天样式
public class ChatConnectServlet extends HttpServlet {

    // 用于接收get请求（暂用post）
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        ServletContext context = getServletContext();
        String RemoteUserKey = (String) context.getAttribute("RemoteUserKey");
        String RelativeCachepath = "CaChe" + File.separator+RemoteUserKey;


        String[] arr = UserLogger.readlog(RelativeCachepath+File.separator+"log.txt");
        System.out.println(arr);

        response.getWriter().write("ChatConnectServlet:GET:36");

    }

    // 用于接收post请求
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        ServletContext context = getServletContext();
        //此处时加密姓名和id
        String RemoteUserKey = (String) context.getAttribute("RemoteUserKey");
        //当前用户的相对路径(含用户key) 解密后，这样的解密到底对不对

        String RelativeCachepath = "CaChe" + File.separator+ StringCodeSolver.Decode(RemoteUserKey) ;
        String localuserKey = (String) context.getAttribute("localuserKey");
        UserInfo remoteUser = (UserInfo)context.getAttribute("RemoteUser");
        boolean ismale =remoteUser.isMale();
        //缓存的绝对路径(不含用户key)
        String absoluteCachePath = context.getRealPath("CaChe");

        //获取每条聊天记录
        String[] arr = UserLogger.readlog(absoluteCachePath+File.separator+StringCodeSolver.Decode(RemoteUserKey)+File.separator+"log.txt");

        JSONObject msgJudge;
        StringBuilder allMsg = new StringBuilder();
        for(int i=0;i<arr.length;i++){
            msgJudge = JSON.parseObject(arr[i]);
            if (msgJudge.get("sender").equals(localuserKey)){
                allMsg.append(SelfChat.getselfMsg(arr[i],RelativeCachepath));
            }else {
                allMsg.append(RemoteChat.getRemoteMsg(arr[i],RelativeCachepath,ismale,remoteUser.getName()));
            }
        }

        response.getWriter().write(allMsg.toString());
    }



}
