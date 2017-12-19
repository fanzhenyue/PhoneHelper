package com.demo.phonehelper.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2017/12/18.
 */

public class DateUtil {

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static String strToDate(long strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(strDate);
        String date = dateFormat.format(cal.getTime());
        return date;
    }
}
