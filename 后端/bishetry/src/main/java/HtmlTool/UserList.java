package HtmlTool;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.json.Json;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class UserList {
    public  HashMap<String,UserInfo> userInfoHashMap = new HashMap<>();


    public void addUserWithJson(String Json) throws UnsupportedEncodingException {
        userInfoHashMap.put(UserListAdder.GetKeyFromJson(Json), UserListAdder.GetUserInfoFromJson(Json));
       }

    public void addUserWithUserInfo(UserInfo user) throws UnsupportedEncodingException {
        userInfoHashMap.put(user.getName()+user.getId(),user);
    }
    //外部传入用户列表输出信息
    public static String getAllUserFonts(HashMap<String,UserInfo> userInfoHashMap) throws UnsupportedEncodingException {
        Iterator<String> iterator = userInfoHashMap.keySet().iterator();
        StringBuilder allFonts = new StringBuilder();
        while (iterator.hasNext()){
            allFonts.append(UserListAdder.AddUser(userInfoHashMap.get(iterator.next())));
        }
        return UserListAdder.FirstLi()+allFonts.toString();
    }


}
