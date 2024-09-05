package Servlet;

import HtmlTool.StringCodeSolver;
import HtmlTool.UserInfo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class RemoteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        HashMap<String, UserInfo> list = (HashMap<String, UserInfo>) context.getAttribute("userlist");
        //设置对话对象姓名
        String name = req.getParameter("name");
        //设置对话对象id
        String id = req.getParameter("id");
        //获取用户列表中的这个用户
        UserInfo remoteuser = list.get(name+id);
        getServletContext().setAttribute("RemoteUserName",name);
        getServletContext().setAttribute("RemoteUserId",id);
        getServletContext().setAttribute("RemoteUser",remoteuser);
        System.out.println(name);
        System.out.println(id);
        System.out.println(context.getAttribute("RemoteUserName"));
        System.out.println(context.getAttribute("RemoteUserId"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        HashMap<String, UserInfo> list = (HashMap<String, UserInfo>) context.getAttribute("userlist");
        //设置对话对象姓名（并加密）
        String DecodeName = req.getParameter("name");
        String name = StringCodeSolver.Encode(req.getParameter("name"));
        //设置对话对象id
        String id = req.getParameter("id");
        //获取用户列表中的这个用户
        UserInfo remoteuser = list.get(name+id);
        getServletContext().setAttribute("RemoteUserName", name);
        getServletContext().setAttribute("RemoteUserId",id);
        //直接存相应的文件夹名称(加密的)
        getServletContext().setAttribute("RemoteUserKey",name+id);
        getServletContext().setAttribute("RemoteUser",remoteuser);
        //创建对应文件夹（解密姓名）
        File file = new File((String) context.getAttribute("cachePath")+File.separator+DecodeName+id);
        if((!file.exists())&&(!file.isDirectory())){
            file.mkdir();
            new File((String) context.getAttribute("cachePath")+File.separator+DecodeName+id+File.separator+"imgCache").mkdir();
            new File((String) context.getAttribute("cachePath")+File.separator+DecodeName+id+File.separator+"fileCache").mkdir();
            new File((String) context.getAttribute("cachePath")+File.separator+DecodeName+id+File.separator+"log.txt").createNewFile();
        }

    }
}
