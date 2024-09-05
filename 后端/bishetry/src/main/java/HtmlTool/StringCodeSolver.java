package HtmlTool;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class StringCodeSolver {
    public static String Encode(String inputString){
        try {
          return URLEncoder.encode(inputString,"utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "StringCodeSolver:EncodeError";

    }
    public static String Decode(String inputString){


        try {
            return URLDecoder.decode(inputString,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
         return "StringCodeSolver:DecodeError";
    }

}
