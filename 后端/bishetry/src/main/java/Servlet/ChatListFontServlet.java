package Servlet;


import HtmlTool.UserInfo;
import HtmlTool.UserList;
import javafx.application.Application;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;


public class ChatListFontServlet extends HttpServlet {


    // 用于接收get请求
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        //获取application中的哈希图并调用userlist类中的静态方法生成样式
        HashMap<String, UserInfo> list = (HashMap<String, UserInfo>) getServletContext().getAttribute("userlist");
        //获取表并且去除本地用户
        UserInfo localuser = (UserInfo) getServletContext().getAttribute("localuser");
        //暂时注释后能用于调试
     //   list.remove(localuser.getName()+localuser.getId());
        //获取建立连接的用户(替换在线状态)
        ServletContext context = getServletContext();
        String key = (String) context.getAttribute("RemoteUserName")+(String)context.getAttribute("RemoteUserId");
        if(list.containsKey(key)){
           UserInfo Remoter= list.get(key);
           Remoter.setStatus(Remoter.getStatus()+" active");
           list.put(key,Remoter);
        }

        //生成用户表样式(这里替换的状态仅仅在本地变量修改过。每次调用servlet都会重置为传入的参数。所以不担心原本的列表无法修改回来)

        String Font = UserList.getAllUserFonts(list);
        response.getWriter().write(Font);

    }

    // 用于接收post请求
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //获取application中的哈希图并调用userlist类中的静态方法生成样式
        HashMap<String, UserInfo> list = (HashMap<String, UserInfo>) getServletContext().getAttribute("userlist");
        //获取表并且去除本地用户
        UserInfo localuser = (UserInfo) getServletContext().getAttribute("localuser");
        //暂时注释后能用于调试
        //   list.remove(localuser.getName()+localuser.getId());
        //获取建立连接的用户(替换在线状态)
        ServletContext context = getServletContext();
        String key = (String) context.getAttribute("RemoteUserName")+(String)context.getAttribute("RemoteUserId");
        if(list.containsKey(key)){
            UserInfo Remoter= list.get(key);
            Remoter.setStatus(Remoter.getStatus()+" active");
            list.put(key,Remoter);
        }

        //生成用户表样式(这里替换的状态仅仅在本地变量修改过。每次调用servlet都会重置为传入的参数。所以不担心原本的列表无法修改回来)

        String Font = UserList.getAllUserFonts(list);
        response.getWriter().write(Font);
    }





}
