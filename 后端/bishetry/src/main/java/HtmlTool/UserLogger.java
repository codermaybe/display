package HtmlTool;

import java.io.*;
import java.util.ArrayList;

public class UserLogger {
    public static void Log(String filepath,String info){
        FileWriter fw = null;
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            //filepath为log文件的完整路径
            File f = new File(filepath);
            if (!f.exists()){
                //文件不存在则创建文件
                f.createNewFile();
            }
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(info);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String[] readlog(String filepath){
        ArrayList<String> strings = new ArrayList<String>();
        File file = new File(filepath);
        if(!file.exists()){
            return new String[0];
        }
        String temp = "";
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fr);
            while ((temp=bufferedReader.readLine())!=null){
                strings.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(strings.size());
        return strings.toArray(new String[0]);

    }

}
