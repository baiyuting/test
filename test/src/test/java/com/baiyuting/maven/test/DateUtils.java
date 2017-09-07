package com.baiyuting.maven.test;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.HOUR_OF_DAY;

/**
 * <li>日期工具类, 继承org.apache.commons.lang.time.DateUtils类</li>
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static final char BEGIN = 'B';
    public static final char END = 'E';
    private static final int MIDNOON = 12;

    /**
     * <li>参数数组</li><br/>
     * 0:yyyyMMdd<br/>1:yyyy-MM-dd<br/>2:yyyy-MM-dd HH:mm<br/>3:yyyy-MM-dd
     * HH:mm:ss<br/>4:yyyy/MM/dd<br/>
     * 5:yyyy/MM/dd HH:mm<br/>6:yyyy/MM/dd
     * HH:mm:ss<br/>7:HH:mm:ss[时间]<br/>8:yyyy[年份]<br/>9:MM[月份]<br/>
     * 10.dd[天]<br/>11.E[星期几]<br/>
     */
    public static String[] parsePatterns = {"yyyyMMdd", "yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH:mm:ss", "HH:mm:ss", "yyyy", "MM", "dd", "E", "yyyyMMddHHmmss"};

    /**
     * <li>得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"</li>
     *
     * @param pattern 可参考参数组parsePatterns
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(Calendar.getInstance().getTime(), pattern);
    }

    /**
     * <li>得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"</li>
     *
     * @param date    日期类
     * @param pattern 可参考参数组parsePatterns
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, parsePatterns[1]);
        }
        return formatDate;
    }

    /**
     * <li>日期型字符串转化为日期 格式</li>
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
     * "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * <li>获取过去的天数</li>
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = Calendar.getInstance().getTimeInMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * <li>获取过去的分钟</li>
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * <li>获取输入日期开始时间</li>
     *
     * @param date
     * @return
     */
    public static Date getDateStart(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * <li>获取输入日期结束时间</li>
     *
     * @param date
     * @return
     */
    public static Date getDateEnd(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * <li>按输入日期加分钟</li>
     *
     * @param recordPeriodMinute 分钟数
     * @return
     */
    public static Date addMinutes(Date recordTime, int recordPeriodMinute) {
        Calendar c = Calendar.getInstance();
        c.setTime(recordTime);
        c.add(Calendar.MINUTE, recordPeriodMinute);
        return c.getTime();
    }

    /**
     * <li>按输入日期加毫秒</li>
     *
     * @param recordPeriodSecond 毫秒数
     * @return
     */
    public static Date addSeconds(Date recordTime, int recordPeriodSecond) {
        Calendar c = Calendar.getInstance();
        c.setTime(recordTime);
        c.add(Calendar.SECOND, recordPeriodSecond);
        return c.getTime();
    }

    /**
     * <li>校验日期是否合法</li>
     *
     * @param date 日期
     * @return
     */
    public static boolean isValidDate(String date) {
        DateFormat fmt = new SimpleDateFormat(parsePatterns[1]);
        try {
            fmt.parse(date);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    /**
     * <li>时间相减得到相差年数</li>
     *
     * @param startTime 开始日期
     * @param endTime   结束日期
     * @return
     */
    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat(parsePatterns[1]);
        try {
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数</li>
     *
     * @param startTime 开始日期
     * @param endTime   结束日期
     * @return
     */
    public static long getDaySub(String startTime, String endTime) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat(parsePatterns[1]);
        Date beginDate = null;
        Date endDate = null;
        try {
            beginDate = format.parse(startTime);
            endDate = format.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * <li>得到n天之后的日期</li>
     *
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance();
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdfd = new SimpleDateFormat((parsePatterns[3]));
        String dateStr = sdfd.format(date);
        return dateStr;
    }

    /**
     * <li>得到n天之后是周几</li>
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance();
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(parsePatterns[11]);
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 把时间根据时、分、秒转换为时间段
     *
     * @param str
     * @param str 可选其1值{ "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd
     *               HH:mm", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
     * @return
     * @author String ？日前或？小时前或？分钟前？秒前
     */
    public static String getTimes(Object str) {
        Date date = parseDate(str);
        long times = Calendar.getInstance().getTimeInMillis() - date.getTime();
        long day = times / (24 * 60 * 60 * 1000);
        long hour = (times / (60 * 60 * 1000) - day * 24);
        long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long sec = (times / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day).append("日前");
        } else if (hour > 0) {
            sb.append(hour).append("小时前");
        } else if (min > 0) {
            sb.append(min).append("分钟前");
        } else if (sec > 0) {
            sb.append(sec).append("秒前");
        } else if (times >= 0) {
            sb.append(times).append("毫秒前");
        } else {
            sb.append("超前毫秒数:").append(times);
        }
        return sb.toString();
    }

    /**
     * 将微信消息中的CreateTime转换成标准格式的时间Date
     *
     * @param createTime 消息创建时间
     * @return
     */
    public static Date formatTime(long createTime) {
        long msgCreateTime = createTime * 1000L;
        return new Date(msgCreateTime);
    }

    /**
     * 将上下午转换为时间
     *
     * @param time
     * @param ap
     * @param flag
     * @return
     */
    public static Date updateDateByAmPm(Date time, int ap, Character flag) {
        if (time != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(time);
            switch (flag) {
                case BEGIN:
                    cal.add(HOUR_OF_DAY, ap < 1 ? 1 : MIDNOON + 1);
                    break;
                case END:
                    cal.add(HOUR_OF_DAY, ap < 1 ? 11 : MIDNOON + 11);
                    break;
                default:
                    cal.add(HOUR_OF_DAY, ap < 1 ? 1 : MIDNOON + 1);
                    break;

            }
            return cal.getTime();
        }
        return time;
    }

    /**
     * 解析时间（上午/下午）
     *
     * @param time
     * @param flag
     * @return
     */
    public static String parseDateByAmPm(Date time, String flag) {
        StringBuilder sb = new StringBuilder();
        if (time != null) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            sb.append(df.format(time));
            df.format(time);
            Calendar cd = Calendar.getInstance();
            cd.setTime(time);
            if (cd.get(Calendar.HOUR_OF_DAY) < MIDNOON) {
                sb.append("上午");
            } else {
                sb.append("下午");
            }
        }
        return StringUtils.trim(sb.toString());
    }

    public static Integer getDaysbetween(Date before, Date after) {
        if (before == null || after == null || before.after(after)) {
            return 0;
        }
        return Integer.valueOf(after.getTime() / (24 * 60 * 60 * 1000) - before.getTime() / (24 * 60 * 60 * 1000) + 1 + "");
    }


}
