package HtmlTool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeSetter {


    public TimeSetter() {
    }
    public String gettime(){
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(System.currentTimeMillis());
        String cardtime = timeFormat.format(date);
        return cardtime;
    }
}
