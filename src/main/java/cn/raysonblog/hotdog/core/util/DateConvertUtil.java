package cn.raysonblog.hotdog.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 日期转换类
 * @author rayson
 *
 */
public class DateConvertUtil {


    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String TIME_FORMAT = "HH:mm:ss";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String TIMESTATMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";

    public static SimpleDateFormat year_format = new SimpleDateFormat("yyyy");

    public static SimpleDateFormat default_format = new SimpleDateFormat(
            "yyyy-MM-dd");

    public static SimpleDateFormat common_format = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    public static SimpleDateFormat time_format = new SimpleDateFormat(
            "yyyyMMddHHmmss");

    /**
     * 得到当前时间,返回long型
     *
     * @return String
     */
    public static long generateLongTime() {
        Date dt = new Date();
        return dt.getTime();
    }

    /**
     * 得到当前时间,格式为yyyyMMddhhmmss
     *
     * @return String
     */
    public static String generateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }

    /**
     * 根据格式生成当前日期时间
     *
     * @param formatStr
     * @return
     */
    public static String generateDateTime(String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(new Date());
    }

    public static String generateDateTimeAddHour(int addHour, String formatstr) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, addHour);
        SimpleDateFormat format = new SimpleDateFormat(formatstr);
        return format.format(cal.getTime());
    }

    /**
     * 根据格式生成日期时间
     *
     * @param date
     * @param formatStr
     * @return
     */
    public static String generateDateTime(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }

    /**
     * 根据yyyy-MM-dd得到月份
     *
     * @param dateString String
     * @return int
     */
    public static int getMonthFromDate(String dateString) {
        Date date = string2Date(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //System.out.println("calendar.setTime::::" + (calendar.get(Calendar.WEEK_OF_YEAR)));
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 根据yyyy-MM-dd得到月份
     *
     * @param dateString String
     * @return int
     */
    public static int getYearFromDate(String dateString) {
        Date date = string2Date(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //  System.out.println("calendar.setTime::::" + (calendar.get(Calendar.WEEK_OF_YEAR)));
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 得到指定年的所有天数
     *
     * @param year String
     * @return day int
     */
    public static int getDayFromYear(String year) {
        Date date;
        int day = 0;
        try {
            date = year_format.parse(year);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            day = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        } catch (ParseException e) {

        }
        return day;
    }

    /**
     * 返回下一年_格式yyyy
     *
     * @return int
     */
    public static int getNextYear() {
        return new GregorianCalendar().get(Calendar.YEAR) + 1;
    }

    /**
     * 获取某年第一天日期_格式yyyy-MM-dd
     *
     * @param year 年份
     * @return String
     */
    public static String getCurrYearFirstDay(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return default_format.format(currYearFirst);
    }

    /**
     * 根据一个日期，返回是星期几的数字_星期一:1....星期六:6,星期天:7， 注意：日期输错，不会报错，例如：2月30或31号
     *
     * @param date 日期值
     * @return int
     */
    public static int getWeek(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        System.out.println("c.get(Calendar.DAY_OF_WEEK)::::"
                + c.get(Calendar.DAY_OF_WEEK));
        int iDay = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            iDay = 7;
        } else {
            iDay = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return iDay;
    }

    /**
     * 根据一个日期，返回是星期几的数字_星期一:1....星期六:6,星期天:7， 注意：日期输错，不会报错，例如：2月30或31号
     *
     * @param dateString String
     * @return int
     */
    public static int getWeek(String dateString) {
        Date date = string2Date(dateString);
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int iDay = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            iDay = 7;
        } else {
            iDay = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return iDay;
    }

    /**
     * 根据一个日期,传入指定天数,想要返回长度,得到String
     *
     * @param inDate 日期
     * @param days   天数
     * @param _iType inDate长度
     * @return String
     */
    public static String getDateByAddDays(String inDate, int days, int _iType) {
        Date dateStr = string2Date(inDate);
        Date tempDate = getDateByAddDays(dateStr, days);
        return date2String(tempDate, _iType);
    }

    /**
     * 根据Date日期,传入返回长度,得到String
     *
     * @param date   Date
     * @param _iType 返回String长度
     * @return String
     */
    public static String date2String(Date date, int _iType) {
        String strTemp = time_format.format(date);
        SimpleDateFormat formatter = null;
        switch (_iType) {
            case 0: // yyyymmdd
                strTemp = strTemp.substring(0, 8);
                break;
            case 4:// yyyy
                strTemp = strTemp.substring(0, 4);
                break;
            case 6: // yymmdd
                strTemp = strTemp.substring(2, 8);
                break;
            case 8: // yyyymmdd
                strTemp = strTemp.substring(0, 8);
                break;
            case 14:
                // yyyyMMddHHmmss 格式
                break;
            case 10: // yyyy-mm-dd
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                strTemp = formatter.format(date);
                break;
            case 17:
                formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                strTemp = formatter.format(date);
                break;
            case 19: // yyyy-mm-dd HH:mm:ss
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                strTemp = formatter.format(date);
                break;
            case -6: // HHmmss
                strTemp = strTemp.substring(8);
                break;
            case -8: // HH:mm:ss
                formatter = new SimpleDateFormat("HH:mm:ss");
                strTemp = formatter.format(date);
                break;
            default:
                break;
        }
        return strTemp;
    }

    /**
     * 获得指定日期前后的日期，返回日期型值
     *
     * @param inDate 指定的日期
     * @param days   加减天数
     * @return Date
     */
    public static Date getDateByAddDays(Date inDate, int days) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(inDate);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static String getDateByAddDays(String formatStr, int days) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, days);
        return format.format(calendar.getTime());
    }

    //日期加减    isMonthStart=true:月初返回第一天   isMonthEnd=true:月末返回月最后一天
    public static String getDateSringByAddDays(String date, int days, boolean isMonthStart, boolean isMonthEnd) {
        if (DateConvertUtil.isMonthStart(date) && isMonthStart) {
            return date;
        }

        if (DateConvertUtil.isMonthEnd(date) && isMonthEnd) {
            return date;
        }
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(StringToDate(date, DATE_FORMAT));
        calendar.add(Calendar.DATE, days);
        return format.format(calendar.getTime());
    }

    public static Date getDateByAddMinutes(Date inDate, int minutes) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(inDate);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    /**
     * 获得指定日期前后的日期，返回日期格式
     *
     * @param inDate 指定日期
     * @param month  加减月数
     * @return
     */
    public static Date getDateByAddMonth(Date inDate, int month) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(inDate);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 当前日期的前后日期，返回字符串格式 yyyyMMddHHmmss
     *
     * @param month 加减月数
     * @return
     */
    public static String getCurrentByAddMonth(int month) {
        Date d = getDateByAddMonth(new Date(), month);

        return date2String(d, 14);
    }

    /**
     * 将日期字符串转换成日期型，日期格式为"yyyy-MM-dd"
     *
     * @param dateString 指定的日期字符串，格式为yyyyMMdd 或者yyyy-MM-dd
     * @return Date
     * @author lijunchen
     */
    public final static Date string2Date(String dateString) {
        if (dateString == null || dateString.trim().length() == 0) {
            return new Date(0);
        }

        // 处理不规范格式，例如：2013-1-2
        if (dateString.indexOf("-") > 0) {
            String month = dateString.substring(dateString.indexOf("-") + 1,
                    dateString.lastIndexOf("-"));
            if (month.length() == 1) {
                dateString = dateString.replace("-" + month + "-", "-0" + month
                        + "-");
            }
            String day = dateString.substring(dateString.lastIndexOf("-") + 1,
                    dateString.length());
            if (day.length() == 1) {
                dateString = dateString.substring(0,
                        dateString.lastIndexOf("-") + 1)
                        + "0" + day;
            }
            // System.out.println(dateString);
        }

        try {
            String strFormat = "";
            switch (dateString.length()) {
                case 4:
                    strFormat = "yyyy";
                    break;
                case 6: // yymmdd
                    strFormat = "yyMMdd";
                    break;
                case 8: // yyyymmdd
                    strFormat = "yyyyMMdd";
                    break;
                case 10: // yyyy-mm-dd
                    strFormat = "yyyy-MM-dd";
                    break;
                case 14:
                    strFormat = "yyyyMMddHHmmss";
                    break;
                default:
                    strFormat = "yyyy-MM-dd HH:mm:ss";
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormat);
            // 这里没有做非法日期的判断，比如：＂2007-05-33＂
            Date timeDate = simpleDateFormat.parse(dateString);
            return timeDate;
        } catch (Exception e) {
            return new Date(0);
        }
    }

    /**
     * 得到两个时间的差值，单位是小时
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static double getHourBetweenDates(Date beginDate, Date endDate) {

        long l1 = endDate.getTime();
        long l2 = beginDate.getTime();

        double cc = l1 - l2;
        return cc / (60 * 60 * 1000);
    }

    /**
     * 得到两个时间的差值，单位是分钟
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static double getMinuteBetweenDates(Date beginDate, Date endDate) {

        double cc = endDate.getTime() - beginDate.getTime();
        return cc / (60 * 1000);
    }

    /**
     * 根据Date日期,传入返回长度,得到String
     *
     * @param date   Date
     * @param _iType 返回String长度
     * @return String
     */
    public static String longDate2String(long lDate, int _iType) {

        Date date = new Date(lDate);
        String strTemp = time_format.format(date);
        SimpleDateFormat formatter = null;
        switch (_iType) {
            case 0: // yyyymmdd
                strTemp = strTemp.substring(0, 8);
                break;
            case 4:// yyyy
                strTemp = strTemp.substring(0, 4);
                break;
            case 6: // yymmdd
                strTemp = strTemp.substring(2, 8);
                break;
            case 8: // yyyymmdd
                strTemp = strTemp.substring(0, 8);
                break;
            case 10: // yyyy-mm-dd
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                strTemp = formatter.format(date);
                break;
            case -6: // HHmmss
                strTemp = strTemp.substring(8);
                break;
            case -8: // HH:mm:ss
                formatter = new SimpleDateFormat("HH:mm:ss");
                strTemp = formatter.format(date);
                break;
            default:
                break;
        }
        return strTemp;
    }

    public static int getDaysBetween(Date bDate, Date eDate) {
        Calendar d1 = new GregorianCalendar();
        d1.setTime(bDate);
        Calendar d2 = new GregorianCalendar();
        d2.setTime(eDate);
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    /**
     * 返回本年份_格式yyyy
     *
     * @return int
     */
    public static int getCurrYear() {
        return new GregorianCalendar().get(Calendar.YEAR);
    }

    /**
     * 获得月份英文缩写
     *
     * @param sMonth String
     * @return String
     */
    public static String getMonthEnglishName(String sMonth) {

        String[] arrMonth = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL",
                "AUG", "SEP", "OCT", "NOV", "DEC"};

        int iMonth = 0;
        String sRet = "";
        try {
            iMonth = Integer.parseInt(sMonth);
            if (iMonth >= 1 && iMonth <= 12) {
                sRet = arrMonth[iMonth - 1];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sRet;
    }

    public static Date StringToDate(String dateStr, String formatStr) {
        DateFormat dd = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = dd.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static int getBetweenDayNumber(Date dateA, Date dateB) {
        long dayNumber = 0;
        // 1小时=60分钟=3600秒=3600000
        long mins = 60L * 1000L;
        // long day= 24L * 60L * 60L * 1000L;计算天数之差
        dayNumber = (dateB.getTime() - dateA.getTime()) / mins;
        return (int) dayNumber;
    }

    /**
     * 得到当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        return simple.format(date);
    }

    public static String getCurrentTime() {
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        return common_format.format(date);
    }

    public static String getCron(Date date) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return generateDateTime(date, dateFormat);
    }


    /**
     * 按照用户提供的日期格式化输出日期数据
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public static String parseDateToString(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    public static boolean isMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE) == calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static boolean isMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE) == calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
    }

    public static boolean isMonthEnd(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(StringToDate(date, "yyyy-MM-dd"));
        return calendar.get(Calendar.DATE) == calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static boolean isMonthStart(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(StringToDate(date, "yyyy-MM-dd"));
        return calendar.get(Calendar.DATE) == calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
    }


    /**
     * 获得去年最后一天
     *
     * @param String date
     */
    public static String get1YearBeforeLastDay(String date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(StringToDate(date, "yyyy-MM-dd"));
        ca.set(Calendar.YEAR, ca.get(Calendar.YEAR) - 1);
        ca.set(Calendar.MONTH, 11);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return parseDateToString(ca.getTime(), "yyyy-MM-dd");
    }

    /**
     * 获得前年最后一天
     *
     * @param String date
     */
    public static String get2YearBeforeLastDay(String date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(StringToDate(date, "yyyy-MM-dd"));
        ca.set(Calendar.YEAR, ca.get(Calendar.YEAR) - 2);
        ca.set(Calendar.MONTH, 11);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return parseDateToString(ca.getTime(), "yyyy-MM-dd");
    }

    /**
     * 获得上前年最后一天
     *
     * @param String date
     */
    public static String get3YearBeforeLastDay(String date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(StringToDate(date, "yyyy-MM-dd"));
        ca.set(Calendar.YEAR, ca.get(Calendar.YEAR) - 3);
        ca.set(Calendar.MONTH, 11);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return parseDateToString(ca.getTime(), "yyyy-MM-dd");
    }

    /**
     * 获得去年同期
     *
     * @param String date
     */
    public static String getLastYearPeriod(String date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(StringToDate(date, "yyyy-MM-dd"));
        ca.add(Calendar.MONTH, -12);
        if (isMonthEnd(date)) {
            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        return parseDateToString(ca.getTime(), "yyyy-MM-dd");
    }

    /**
     * 获得前年同期
     *
     * @param String date
     */
    public static String getLast2YearPeriod(String date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(StringToDate(date, "yyyy-MM-dd"));
        ca.add(Calendar.MONTH, -24);
        if (isMonthEnd(date)) {
            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        return parseDateToString(ca.getTime(), "yyyy-MM-dd");
    }

    /**
     * 获得上月同期
     *
     * @param String date
     */
    public static String getLastMonthPeriod(String date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(StringToDate(date, "yyyy-MM-dd"));
        ca.add(Calendar.MONTH, -1);
        if (isMonthEnd(date)) {
            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        return parseDateToString(ca.getTime(), "yyyy-MM-dd");
    }


    /**
     * 获得上上月同期(保险单车环比)
     *
     * @param String date
     */
    public static String getBeforeMonthPeriod(String date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(StringToDate(date, "yyyy-MM-dd"));
        ca.add(Calendar.MONTH, -2);
        if (isMonthEnd(date)) {
            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        return parseDateToString(ca.getTime(), "yyyy-MM-dd");
    }

    /**
     * 获得上年月度同期
     *
     * @param String date
     */
    public static String getLastYearMonthPeriod(String date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(StringToDate(date, "yyyy-MM-dd"));
        ca.add(Calendar.YEAR, -1);
        if (isMonthEnd(date)) {
            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        return parseDateToString(ca.getTime(), "yyyy-MM-dd");
    }

    /**
     * 获得某年某月最后一天
     *
     * @param String date
     */
    public static String getXYearXMonthLastDay(Integer year, Integer month) {
        Calendar ca = Calendar.getInstance();
        // ca.setTime(StringToDate(date, "yyyy-MM-dd"));
        ca.set(Calendar.YEAR, year);
        ca.set(Calendar.MONTH, month - 1);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return parseDateToString(ca.getTime(), "yyyy-MM-dd");
    }


    /**
     * 获得当年12个月的信息
     *
     * @param String date
     * @param flag   是否截止到当月
     */
    public static List<String> getCurrYearMonths(String date, boolean flag) {
        List<String> monthList = new ArrayList<String>();
        Calendar ca = Calendar.getInstance();
        ca.setTime(StringToDate(date, "yyyy-MM-dd"));
        int month = flag ? ca.get(Calendar.MONTH) + 1 : 12;
        int year = ca.get(Calendar.YEAR);
        for (int i = 0; i < month; i++) {
            ca = Calendar.getInstance();
            ca.set(Calendar.YEAR, year);
            ca.set(Calendar.MONTH, i);
            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
            monthList.add(parseDateToString(ca.getTime(), "yyyy-MM-dd"));
        }
        return monthList;
    }

    /**
     * 获得当年12个月的信息
     *
     * @param String date
     * @param flag   是否截止到当月
     */
    public static List<String> get2YearMonths(String date, boolean flag) {
        List<String> monthList = new ArrayList<String>();
        Calendar ca = Calendar.getInstance();
        ca.setTime(StringToDate(date, "yyyy-MM-dd"));
        ca.add(Calendar.YEAR, -1);
        monthList.addAll(getCurrYearMonths(parseDateToString(ca.getTime(), "yyyy-MM-dd"), false));

        ca.add(Calendar.YEAR, 1);
        monthList.addAll(getCurrYearMonths(parseDateToString(ca.getTime(), "yyyy-MM-dd"), flag));
        return monthList;
    }

    /**
     * 获得当年有折扣的月分的信息
     * 折扣用
     *
     * @param String date
     */
    public static List<String> getCurrYearMonthsDis(String date) {
        List<String> monthList = new ArrayList<String>();
        Calendar ca = Calendar.getInstance();
        ca.setTime(StringToDate(date, "yyyy-MM"));
        int month = 0;
        if (TestLastDay(date)) {
            month = ca.get(Calendar.MONTH) + 1;
        } else {
            month = ca.get(Calendar.MONTH);
        }
        int year = ca.get(Calendar.YEAR);
        for (int i = 0; i < month; i++) {
            ca = Calendar.getInstance();
            ca.set(Calendar.YEAR, year);
            ca.set(Calendar.MONTH, i);
            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
            monthList.add(parseDateToString(ca.getTime(), "yyyy-MM"));
        }
        return monthList;
    }

    /*
    * 将时间戳转换为时间
    */
    public static String stampToDate(Long s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 计算得到MongoDB存储的日期，（默认情况下mongo中存储的是标准的时间，中国时间是东八区，存在mongo中少8小时，所以增加8小时）
     *
     * @author: huyj
     * @date: 2017年3月8日 上午9:26:23
     * @param: @param
     * date
     * @return: Date
     */
    public static Date getMongoDate(Date date) {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.HOUR_OF_DAY, 8);//StringToDate(,format)
        return ca.getTime();
        //return String2Date(sdf.format(ca.getTime()),"");
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //return day + "天" + hour + "小时" + min + "分" + sec + "秒";
        return (day > 0 ? day + "天" : "")
                + (hour > 0 ? hour + "小时" : "")
                + (min > 0 ? min + "分" : "")
                + (sec > 0 ? sec + "秒" : "0秒");
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(Long time1, Long time2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return day + "天" + hour + "小时" + min + "分" + sec + "秒";
        return (day > 0 ? day + "天" : "")
                + (hour > 0 ? hour + "小时" : "")
                + (min > 0 ? min + "分" : "")
                + (sec > 0 ? sec + "秒" : "0秒");
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        Long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        Long time2 = cal.getTimeInMillis();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return day + "天" + hour + "小时" + min + "分" + sec + "秒";
        return (day > 0 ? day + "天" : "")
                + (hour > 0 ? hour + "小时" : "")
                + (min > 0 ? min + "分" : "")
                + (sec > 0 ? sec + "秒" : "0秒");
    }


    /**
     * 判断是否当月最后一天
     *
     * @param date
     * @return
     */
    public static boolean TestLastDay(String date) {
        int lastDay = 0;
        int now = 0;
        try {

            Date a = string2Date(date);
            Calendar b = Calendar.getInstance();
            b.setTime(a);
            lastDay = b.getActualMaximum(Calendar.DAY_OF_MONTH);
            now = b.get(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (now == lastDay);
    }


    public static boolean isToday(String date, String format) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        if (date.equals(fmt.format(new Date()).toString())) {//格式化为相同格式
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否是当年当月
     *
     * @param date
     * @param format
     * @return <0： 小于某年某月
     * =0：等于某年某月
     * >0：大于某年某月
     */
    public static Integer isCurrYearAndMonth(String date, int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(string2Date(date));
        String year1 = String.valueOf(cal.get(Calendar.YEAR));
        String month1 = String.valueOf(cal.get(Calendar.MONTH) + 1);

        String _date1 = year1 + month1;
        String _date2 = String.valueOf(year) + String.valueOf(month);
        return Integer.parseInt(_date1) - Integer.parseInt(_date2);
    }


    public static void main(String rgs[]) {
    /*    System.out.println("去年年底:" + get1YearBeforeLastDay("2015-01-01"));
        System.out.println("前年年底:" + get2YearBeforeLastDay("2015-01-01"));
        System.out.println("去年同期:" + getLastYearPeriod("2017-12-31"));
        System.out.println("上月同期:" + getLastMonthPeriod("2017-03-29"));
        System.out.println("某年分月(截止到当月)" + getCurrYearMonths("2017-08-30", true));
        System.out.println("某年分月(12个月)" + getCurrYearMonths("2017-08-30", false));
        System.out.println("某年上年12个月+当年截止到当月" + get2YearMonths("2017-08-30", true));
        System.out.println("某年上年12个月+当年12个月月" + get2YearMonths("2017-08-30", false));
        System.out.println(getCurrYearMonthsDis("2016-01-31"));
        System.out.println(getLastYearMonthPeriod("2016-03-02"));

        System.out.println(isToday("2017-04-23",DATE_FORMAT));

        System.out.println(getDateByAddDays("2017-03-01",-1,10));

        System.out.println(isCurrYearAndMonth("2017-03-01",2017,03));
        System.out.println(getYearFromDate("2017-02-15") );
        System.out.println("sales_library,ybxs".split("[;]").length);*/

        System.out.println(getDateSringByAddDays("2017-02-01", -1,false,false));
    }


    /**
     * 判断当天是否为本月一号
     *
     * @return
     */
    public static boolean earlyMonth(String date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date time = simpleDateFormat.parse(date);
        calendar.setTime(time);
        int today = calendar.get(calendar.DAY_OF_MONTH);
        if (today == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取昨天的日期
     *
     * @return
     */
    public static String getYesterdayDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date time = new Date(simpleDateFormat.parse(date).getTime() - 24 * 60 * 60 * 1000);
        return simpleDateFormat.format(time);
    }

    /**
     * 判断是否为年的第一天
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static boolean earlyYear(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date time = simpleDateFormat.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE)));
        if (calendar.get(Calendar.DAY_OF_YEAR) == 1) {
            return true;
        }
        return false;
    }

    /**
     * @param
     * @param 根据长度返回年份或月份 4年7月
     * @return
     */
    public static String getPlanYearOrMonth(String date, int length) {
        return date.substring(0, length);
    }
}
