package Servlet;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Scanner;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import HtmlTool.StringCodeSolver;
import HtmlTool.TimeSetter;
import HtmlTool.UserInfo;
import HtmlTool.UserLogger;
import MsgProcessor.MessageSender;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

public class UploadServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        //RemoteUserName是加密的
        String RemoteUserPath =  (String) context.getAttribute("RemoteUserName")+(String)context.getAttribute("RemoteUserId");
        //建立聊天对象文件保存的路径,这里已经确定用户
        String cachePath = (String) context.getAttribute("cachePath");


        //uploadServlet测试
        System.out.println("uploadServlet:39:"+RemoteUserPath);

        String path = cachePath+File.separator+ StringCodeSolver.Decode(RemoteUserPath) ;

        //文件工厂
        DiskFileItemFactory filefactory = new DiskFileItemFactory();
        filefactory.setSizeThreshold(1024 * 8);// 设置8k的缓存空间
        filefactory.setRepository(new File("\\tempCache"));

        ServletFileUpload upload = new ServletFileUpload(filefactory);
        upload.setHeaderEncoding("UTF-8");// 设置文件名处理中文编码

        JSONObject fileJson = new JSONObject();
        int imgcount = 0;
        int filecount=0;
        try {
            FileItemIterator fii = upload.getItemIterator(request);// 使用遍历类
            while (fii.hasNext()) {
                FileItemStream fis = fii.next();
                if (fis.isFormField()) {//FileItemStream同样使用OpenStream获取普通表单的值
                    InputStreamReader in = new InputStreamReader(fis.openStream(),"UTF-8");
                    Scanner sc = new Scanner(in);
                    StringBuffer sb = new StringBuffer();
                    if(sc.hasNextLine()){
                        sb.append(sc.nextLine());
                    }
                    System.err.println("UploadServlet:text:65:"+sb.toString());
                    fileJson.put("text",sb.toString());
                } else {
                    if (fis.getFieldName().equals("ImgMsgHidden")&&(!fis.getName().equals(""))&&(!fis.getName().equals(null))){

                        String fileName = fis.getName();
                        System.err.println("文件名为：" + fileName);
                        InputStream in = fis.openStream();
                        String imgPath = path+"\\imgCache\\";
                        System.out.println(imgPath+fileName);
                        //按照文件名和对应路径创建对象
                        File imgFile = new File(imgPath+fileName);
                        FileUtils.copyInputStreamToFile(in, imgFile);
                        //测试文件输入
                        System.out.println("UploadServlet test file size"+imgFile.length());
                        imgcount++;
                        //保存文件名
                        fileJson.put("img"+imgcount,fileName);
                        //保存文件大小
                        fileJson.put("imgsize"+imgcount,(imgFile.length()/1024/1024));

                    }else if(fis.getFieldName().equals("FileMsgHidden")&&(!fis.getName().equals(""))&&(!fis.getName().equals(null))){
                        String fileName = fis.getName();

                        System.err.println("文件名为：" + fileName);
                        InputStream in = fis.openStream();

                        String filePath = path+"\\fileCache\\";
                        System.out.println(filePath+fileName);
                        File file = new File(filePath+fileName);
                        FileUtils.copyInputStreamToFile(in, file);
                        filecount++;
                        fileJson.put("file"+filecount,fileName);
                        //保存文件大小，转成mb
                        fileJson.put("filesize"+filecount,(file.length()/1024/1024));
                    }else {
                        System.out.println("UploadServlet:error:90"+fis.getFieldName());
                    }
                }

            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        //获取本地用户信息
        UserInfo localuser  = (UserInfo)context.getAttribute("localuser");
        //在发送的消息中添加各个字段

        fileJson.put("sender",localuser.getName()+localuser.getId());
        fileJson.put("reciver",context.getAttribute("RemoteUserKey"));//与key相同
        fileJson.put("imgnum",imgcount);
        fileJson.put("filenum",filecount);
        fileJson.put("time",new TimeSetter().gettime());
        fileJson.put("senderip", localuser.getIP());

        fileJson.put("reciverip",((UserInfo)getServletContext().getAttribute("RemoteUser")).getIP());
        fileJson.put("type","msg");
        System.out.println(fileJson);
        //记录日志
        UserLogger.Log(path+File.separator+"log.txt",fileJson.toJSONString());
        //发送消息(发送的所有信息包含在msg内)
        System.out.println("UploadServlet113:"+fileJson.toJSONString());
        new MessageSender().SendMsg(fileJson.toJSONString());
    }
}