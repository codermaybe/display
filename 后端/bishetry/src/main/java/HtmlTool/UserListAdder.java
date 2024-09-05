package HtmlTool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import java.io.UnsupportedEncodingException;

public class UserListAdder {
    //传入User参数返回相应的样式
   public static String AddUser(UserInfo user) throws UnsupportedEncodingException {

       String Font ="<li class=\"用户状态更换\" name=\"UserCard\" UserId=\"用户ID更换\">\n" +
               "\t\t\t\t\t\t\t\t<div class=\"hover_action\">\n" +
               "\t\t\t\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-link text-info\"><i\n" +
               "                                        class=\"zmdi zmdi-eye\"></i></button>\n" +
               "\t\t\t\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-link text-warning\"><i\n" +
               "                                        class=\"zmdi zmdi-star\"></i></button>\n" +
               "\t\t\t\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-link text-danger\"><i\n" +
               "                                        class=\"zmdi zmdi-delete\"></i></button>\n" +
               "\t\t\t\t\t\t\t\t</div>\n" +
               "\t\t\t\t\t\t\t\t<a href=\"#\" class=\"card\">\n" +
               "\t\t\t\t\t\t\t\t\t<div class=\"card-body\">\n" +
               "\t\t\t\t\t\t\t\t\t\t<div class=\"media\">\n" +
               "\t\t\t\t\t\t\t\t\t\t\t<div class=\"avatar me-3\">\n" +
               "\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"status rounded-circle\"></span>\n" +
               "\t\t\t\t\t\t\t\t\t\t\t\t<img class=\"avatar rounded-circle\" src=\"用户头像更换\" alt=\"avatar\" name=\"ChatCardIcon\">\n" +
               "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
               "\t\t\t\t\t\t\t\t\t\t\t<div class=\"media-body overflow-hidden\">\n" +
               "\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"d-flex align-items-center mb-1\">\n" +
               "\t\t\t\t\t\t\t\t\t\t\t\t\t<h6 class=\"text-truncate mb-0 me-auto\"  name=\"ChatCardName\">用户姓名更换</h6>\n" +
               "\t\t\t\t\t\t\t\t\t\t\t\t\t<p class=\"small text-muted text-nowrap ms-4 mb-0\" name=\"ChatCardTime\" >用户登陆时间更换</p>\n" +
               "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
               "\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"text-truncate\" name='ChatCardInfo'>用户备注更换\n" +
               "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
               "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
               "\t\t\t\t\t\t\t\t\t\t</div>\n" +
               "\t\t\t\t\t\t\t\t\t</div>\n" +
               "\t\t\t\t\t\t\t\t</a>\n" +
               "\t\t\t\t\t\t\t</li>";


         Font = Font.replace("用户状态更换",user.getStatus())
                    .replace("用户ID更换",Double.toString(user.getId()))
                    .replace("用户头像更换",GetHeadIcon(user))
                    .replace("用户姓名更换",StringCodeSolver.Decode(user.getName()) )
                    .replace("用户登陆时间更换",user.getLogintime())
                    .replace("用户备注更换",StringCodeSolver.Decode(user.getBackinfo()));
       return Font;
   }

   public static String FirstLi(){
       return "<li class=\"header d-flex justify-content-between ps-3 pe-3 mb-1\">\n" +
               "\t\t\t\t\t\t\t\t<span>RECENT CHATS</span>\n" +
               "\t\t\t\t\t\t\t</li>";
   }



   //返回性别匹配的头像
   public static String GetHeadIcon(UserInfo user){
       if(user.isMale){
           return "assets/images/xs/avatar2.jpg";
       }else {
           return "assets/images/xs/avatar3.jpg";
       }

   }
   //UserList 传入Json字符串获取key
   public static String GetKeyFromJson(String Json){
       JSONObject user = JSON.parseObject(Json);
       return user.getString("name")+user.getString("id");
   }
   //UserList 传入字符串获取UserInfo对象
    public static UserInfo GetUserInfoFromJson(String Json) throws UnsupportedEncodingException {
       return new UserInfo(Json);
    }

}
