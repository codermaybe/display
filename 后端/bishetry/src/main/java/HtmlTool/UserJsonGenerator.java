package HtmlTool;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;

public class UserJsonGenerator {
    public static String getUserJson(UserInfo LocalUser) throws UnsupportedEncodingException {
        JSONObject user = new JSONObject();
        user.put("name",LocalUser.getName());
        user.put("backinfo",LocalUser.getBackinfo());
        user.put("ismale",LocalUser.isMale());
        user.put("ip",LocalUser.getIP());
        user.put("id",LocalUser.getId());
        user.put("logintime",LocalUser.getLogintime());
        user.put("alreadychatted",LocalUser.isAlreadychatted());
        user.put("status",LocalUser.getStatus());
        return user.toJSONString();
    }
}
