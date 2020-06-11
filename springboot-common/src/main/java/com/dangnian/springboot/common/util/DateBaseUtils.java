package com.dangnian.springboot.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <p>日期类基础工具</p>
 *
 * @Author nian.qiu
 * @Date 2018/8/13 17:21
 * @Version 1.0.0
 */
public class DateBaseUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    public final static long    ONE_MILL_SECONDS     = 1;
    public final static long    ONE_SECONDS          = 1000 * ONE_MILL_SECONDS;
    public final static long    ONE_MINUTES          = 60 * ONE_SECONDS;
    public final static long    ONE_HOUR             = 60 * ONE_MINUTES;
    public final static long    ONE_DAY              = 24 * ONE_HOUR;
    public final static long    ONE_YEAR             = ONE_DAY * 365;
    public final static String SHORT_FORMAT      = "yyyyMMdd";
    public final static String SHORT_YEAR_FORMAT = "yyMMdd";
    public final static String LONG_FORMAT       = "yyyyMMddHHmmss";
    public final static String LONG_LONG_FORMAT  = "yyyyMMddHHmmssSSS";
    public final static String WEB_FORMAT        = "yyyy-MM-dd";
    public final static String WEB_FORMAT_NOTE   = "yyyy/MM/dd";
    public final static String WEB_TIME_FORMAT   = "HH:mm:ss";
    public final static String TIME_FORMAT       = "HHmmss";
    public final static String MONTH_FORMAT      = "yyyy-MM";
    public final static String YEAR_FORMAT       = "yyyy";
    public final static String HOUR_FORMAT       = "yyyy-MM-dd HH";
    public final static String CHINESE_DT_FORMAT = "yyyy年MM月dd日";
    public final static String NEW_FORMAT        = "yyyy-MM-dd HH:mm:ss";
    public final static String NO_SECOND_FORMAT  = "yyyy-MM-dd HH:mm";
    public final static String FULL_FORMAT       = "yyyy-MM-dd HH:mm:ss.SSS";
    public final static String SLAT_FORMAT       = "yyyy/MM/dd HH:mm:ss";
    final static String[] zhWeeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
    final static String[] usWeeks = {"Sun","Mon","Tues","Wed","Thur","Fri","Sat"};
    final static String[] zhDayPeriod = {"上午","下午"};
    final static String[] usDayPeriod = {"AM","PM"};


    /**
     * 获得默认代理对象
     *
     * @return CollectionUtils
     */
    public static DateUtils getProxy(){
        return new DateUtils();
    }

    /**
     * 判断是否为同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2){
       return DateUtils.isSameDay(date1,date2);
    }

    public static boolean isSameInstant(Date date1, Date date2){
        return DateUtils.isSameInstant(date1,date2);
    }

    public static Date parseDate(String str, String... parsePatterns){
        try {
            return DateUtils.parseDate(str,parsePatterns);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 转化当前时间到毫秒数
     *
     * @return
     */
    public static long current(){
        return current(DateBaseUtils.getCurrentDate());
    }

    /**
     * 转化毫秒数到日期
     *
     * @param current
     * @return
     */
    public static Date date(long current){
        return new Date(current);
    }

    /**
     * 转化日期到毫秒数
     *
     * @return
     */
    public static long current(Date date){
        return date.getTime();
    }

    //转化日期
    public static Date getCurrentDate() {
        return new Date();
    }

    public static Date toDateFullFormat(String dateString){
        return  toDate(dateString,FULL_FORMAT);
    }

    public static Date toDateShortFormat(String dateString){
        return  toDate(dateString,SHORT_FORMAT);
    }

    public static Date toDateLongFormat(String dateString) {
        return  toDate(dateString,LONG_FORMAT);
    }

    public static Date toDateNewFormat(String dateString) {
        return  toDate(dateString,NEW_FORMAT);
    }

    public static Date toDateYearFormat(String dateString) {
        return  toDate(dateString,YEAR_FORMAT);
    }

    public static Date toDateMonthFormat(String dateString) {
        return  toDate(dateString,MONTH_FORMAT);
    }

    public static Date toDateWebFormat(String dateString) {
        return  toDate(dateString,WEB_FORMAT);
    }

    public static Date toDateHourFormat(String dateString) {
        return  toDate(dateString,HOUR_FORMAT);
    }

    public static Date toDateMinutesFormat(String dateString) {
        return  toDate(dateString,NO_SECOND_FORMAT);
    }

    public static SimpleDateFormat getSimpleDateFormat(String strFormat){
        if(StringBaseUtils.isNotEmpty(strFormat)){
            return new SimpleDateFormat(strFormat);
        } else {
            return new SimpleDateFormat();
        }
    }

    public static Date toDateWebNoteFormat(String dateString) {

        try{
            if(StringBaseUtils.isEmpty(dateString)){
                return null;
            }
            String[] stringArr = dateString.split("/");
            if(ArrayBaseUtils.isEmpty(stringArr)){
                return null;
            }

            //增加yyyy-mm-dd的场景
            if(stringArr.length == 1 && dateString.split("-").length > 1){
                stringArr = dateString.split("-");
            }

            String mm = stringArr[1];
            if(mm.length() == 1 ){
                mm = "0"+mm;
            }
            String dd = stringArr[2];
            if(dd.length() == 1 ){
                dd = "0"+dd;
            }
            String newStr = stringArr[0] + "/" + mm + "/" + dd;
            return  toDate(newStr,WEB_FORMAT_NOTE);
        }catch (Exception ex) {
            LOGGER.error("日期转化异常,原因：",ex);
        }
        return null;
    }


    /**
     * 将字符串按照指定格式转为java.util.Date
     *
     * @param dateString   日期格式字符串
     * @param format       格式字符串
     */
    public static Date toDate(String dateString, String format) {
        if(dateString != null && dateString.length() == format.length()){
            try{
                return getSimpleDateFormat(format).parse(dateString);
            }catch (ParseException ex) {
                LOGGER.error("日期转化异常,原因：",ex);
            }
        }
        return null;
    }


    //转化日期字符
    public static String getCurrentMinutesFormat(){
        return getMinutesFormatDateString(new Date());
    }

    public static String getMinutesFormatDateString(Date date) {
        return toDateString(date, NO_SECOND_FORMAT);
    }

    public static String getCurrentHourFormat(){
        return getHourFormatDateString(new Date());
    }

    public static String getHourFormatDateString(Date date) {
        return toDateString(date, HOUR_FORMAT);
    }

    public static String getCurrentYearFormat(){
        return getYearFormatDateString(new Date());
    }

    public static String getYearFormatDateString(Date date) {
        return toDateString(date, YEAR_FORMAT);
    }

    public static String getCurrentMonthFormat(){
        return getMonthFormatDateString(new Date());
    }

    public static String getMonthFormatDateString(Date date) {
        return toDateString(date, MONTH_FORMAT);
    }

    public static String getCurrentNewFormat(){
        return getNewFormatDateString(new Date());
    }

    public static String getNewFormatDateString(Date date) {
        return toDateString(date, NEW_FORMAT);
    }
    
    public static String getCurrentWebFormat(){
        return getWebFormatDateString(new Date());
    }

    public static String getWebFormatDateString(Date date) {
        return toDateString(date, WEB_FORMAT);
    }
    
    public static String getWebFormatNoteDateString(Date date) {
        return toDateString(date, WEB_FORMAT_NOTE);
    }

    public static String getWebFormatTimeString(Date date) {
        return toDateString(date, WEB_TIME_FORMAT);
    }

    public static String getCurrentLongDate(){
        return getLongDateString(new Date());
    }

    public static String getLongDateString(Date date) {
        return toDateString(date, LONG_FORMAT);
    }
    public static String getLongLongDateString() {
        return toDateString(new Date(), LONG_LONG_FORMAT);
    }

    public static String getCurrentShortDate(){
        return getShortDateString(new Date());
    }

    public static String getShortDateString(Date date) {
        return toDateString(date, SHORT_FORMAT);
    }

    public static String getSaltFormatString(Date date) {
        return toDateString(date, SLAT_FORMAT);
    }

    /**
     * 将java.util.date型按照指定格式转为字符串
     *
     * @param date   源对象
     * @param format 格式字符串
     */
    public static String toDateString(Date date, String format) {
        return getSimpleDateFormat(format).format(date);
    }

    /**
     * 比较操作
     *
     * @param sourceDate 原始日期
     * @param targetDate 目标日期
     * @param format     日期格式
     * @return   0  ：sourceDate == targetDate
     *           1  ：sourceDate >  targetDate
     *           -1 : sourceDate <  targetDate
     *           -2 : 数据异常
     */
    public static int  compare(String sourceDate, String targetDate, String format){
        Date sDate = toDate(sourceDate,format);
        Date tDate = toDate(targetDate,format);
        return sDate.compareTo(tDate);
    }

    /**
     * 比较操作
     *
     * @param sourceDate  原始日期
     * @param targetDate  目标日期
     * @return 0: equal -1:less than 1:great than
     */
    public static int  compare(Date sourceDate, Date targetDate){
        return sourceDate.compareTo(targetDate);
    }

    /**
     * 判断相差毫秒数
     *
     * @param sourceDate  起始日期
     * @param targetDate  结束日期
     * @return
     */
    public static Long diffMillSeconds(Date sourceDate, Date targetDate){
        return targetDate.getTime() - sourceDate.getTime();
    }

    /**
     * 相差秒数
     * @param start
     * @param end
     * @return
     */
    public static long diffSeconds(Date start, Date end) {
        return (end.getTime() - start.getTime()) / 1000;
    }

    /**
     * 日期加减通过日历法
     *
     * @param date 原始日期
     *
     * @return
     */
    public static Date addDayByCalendar(Date date, int diffDay){
        Calendar c = Calendar.getInstance();
        if(date == null){
            //如果时间为空，取当前时间
            date = new Date();
        }
        c.setTime(date);
        c.add(Calendar.DATE, diffDay);
        return c.getTime();
    }

    public static String addDayByCalendar(Date sourceDate, int diffDay, String format){
        return toDateString(addDayByCalendar(sourceDate,diffDay),format);
    }

    public static String addDayByCalendar(String sourceDate, int diffDay, String format){
        Date sDate = toDate(sourceDate,format);
        return  addDayByCalendar(sDate,diffDay,format);
    }

    /**
     * 计算当前时间几天之后的时间
     *
     * @param date
     * @param days
     *
     * @return
     */
    public static Date addDay(Date date, long days) {
        return addHours(date, days * 24);
    }


    /**
     * 获得指定日期前一天(昨天)
     *
     * @param date
     * @return
     */
    public static Date getYesDay(Date date){
        return  addDay(date,-1);
    }

    /**
     * 获得指定日期的后一天（明天）
     *
     * @param date
     * @return
     */
    public static Date getTomDay(Date date){
        return  addDay(date,1);
    }


    /**
     * 计算当前时间几小时之后的时间
     *
     * @param date
     * @param hours
     *
     * @return
     */
    public static Date addHours(Date date, long hours) {
        return addMinutes(date, hours * 60);
    }

    /**
     * 计算当前时间几分钟之后的时间
     *
     * @param date
     * @param minutes
     *
     * @return
     */
    public static Date addMinutes(Date date, long minutes) {
        return addSeconds(date, minutes * 60);
    }

    /**
     * 在一个时间上加指定秒
     *
     * @param date
     * @param secs
     *
     * @return
     */
    public static Date addSeconds(Date date, long secs) {
        return new Date(date.getTime() + (secs * 1000));
    }

    /**
     * 判断两个日期相隔多少天
     *
     * @param sourceDate 起始日期
     * @param targetDate 结束日期
     * @param format     日期格式
     * @return
     */
    public static Long diffDay(String sourceDate, String targetDate, String format){
        Date sDate = toDate(sourceDate,format);
        Date tDate = toDate(targetDate,format);

        return diffDay(sDate,tDate);
    }

    /**
     * 判断两个日期相隔多少天
     *
     * @param sourceDate 起始日期
     * @param targetDate 结束日期
     *
     * @return 相隔天数
     */
    public static Long diffDay(Date sourceDate, Date targetDate){
        return (targetDate.getTime() - sourceDate.getTime()) / ONE_DAY;
    }

    /**
     * 判断两个日期相差几个月份
     *
     * @param beforeDate
     * @param afterDate
     * @return
     */
    public static int diffMonth(Date beforeDate, Date afterDate){
        Calendar beforeCalendar = Calendar.getInstance();
        Calendar afterCalendar  = Calendar.getInstance();
        beforeCalendar.setTime(beforeDate);
        afterCalendar.setTime(afterDate);
        int result = afterCalendar.get(Calendar.MONTH) - beforeCalendar.get(Calendar.MONTH);
        int month = (afterCalendar.get(Calendar.YEAR) - beforeCalendar.get(Calendar.YEAR)) * 12;
        return result+month;
    }

    /**
     * 月份相加减
     *
     * @param date  原始日期
     * @return
     */
    public static String addMonth(Date date, int diffMonth){
        Calendar c = Calendar.getInstance();
        if(date == null){
            //如果时间为空，取当前时间
            date = new Date();
        }
        c.setTime(date);
        c.add(Calendar.MONTH, diffMonth);
        return  getMonthFormatDateString(c.getTime());
    }

    /**
     * 月份相加减(得到日期)
     *
     * @param date  原始日期
     * @return
     */
    public static String addMonthToDay(Date date, int diffMonth){
        Calendar c = Calendar.getInstance();
        if(date == null){
            //如果时间为空，取当前时间
            date = new Date();
        }
        c.setTime(date);
        c.add(Calendar.MONTH, diffMonth);
        return  getWebFormatDateString(c.getTime());
    }

    /**
     * 月份相加减
     *
     * @param sourceDate  原始日期
     * @param diffMonth   加减月份
     * @param format      日期格式
     * @return
     */
    public static String addMonth(String sourceDate, int diffMonth, String format){
        Date sDate = toDate(sourceDate,format);
        return addMonth(sDate,diffMonth);
    }

    /**
     * 在原始日期基础上加几天
     *
     * @param sourceDate 原始日期
     * @param diffDay    相差天数
     * @param format     日期格式
     * @return  日期字符串形式
     */
    public static String addDay(String sourceDate, Long diffDay, String format){
        Date sDate = toDate(sourceDate,format);
        return addDay(sDate,diffDay,format);
    }

    /**
     * 在原始日期基础上加几天
     *
     * @param sourceDate
     * @param diffDay
     * @param format
     * @return
     */
    public static String addDay(Date sourceDate, Long diffDay, String format){
        return toDateString(addDay(sourceDate,diffDay),format);
    }

    /**
     * 获得某月第一天
     *
     * @param monthDate
     * @return
     */
    public static String getFirstDayOfMonth(Date monthDate){
        Calendar c = Calendar.getInstance();
        if(monthDate == null){
            //如果时间为空，取当前时间
            monthDate = new Date();
        }
        c.setTime(monthDate);
        //获取某月最小天数
        int firstDay = c.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        c.set(Calendar.DAY_OF_MONTH, firstDay);
        return getWebFormatDateString(c.getTime());
    }

    public static String getFirstDayOfMonth(String monthDateStr, String format){
        Date monthDate = toDate(monthDateStr,format);
        return getFirstDayOfMonth(monthDate);
    }

    /**
     * 获得某月最后一天
     *
     * @param monthDate
     * @return
     */
    public static String getLastDayOfMonth(Date monthDate){
        Calendar c = Calendar.getInstance();
        if(monthDate == null){
            //如果时间为空，取当前时间
            monthDate = new Date();
        }
        c.setTime(monthDate);

        //获取某月最大天数
        int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        c.set(Calendar.DAY_OF_MONTH, lastDay);
        return getWebFormatDateString(c.getTime());
    }

    public static String getLastDayOfMonth(String monthDateStr, String format){
        Date monthDate = toDate(monthDateStr,format);
        return getLastDayOfMonth(monthDate);
    }

    /**
     * 获得系统当前日期前一天
     *
     * @return
     */
    public static String getYesDayString(){
        return addDayByCalendar(new Date(),-1,DateBaseUtils.WEB_FORMAT);
    }

    /**
     * 获得昨天对应的月份
     *
     * @param   monthFormat 月期格式
     * @return
     */
    public static String getYesDayMonth(String monthFormat){
        return  toDateString(addDayByCalendar(new Date(),-1),monthFormat);
    }

    /**
     * 获得指定日期前一天
     *
     * @return
     */
    public static String getYesDayString(Date sourceDate){
        return addDayByCalendar(sourceDate,-1,DateBaseUtils.WEB_FORMAT);
    }

    /**
     * 获得指定日期前一天
     *
     * @return
     */
    public static String getYesDayString(String sourceDateStr, String format){
        return getYesDayString(toDate(sourceDateStr,format));
    }

    /**
     * 获得指定日期的后一天
     *
     * @param sourceDate
     * @return 返回日期字符串信息，日期格式yyyy-MM-dd
     */
    public static String getTomDayString(Date sourceDate){
        return  toDateString(addDayByCalendar(sourceDate,1),DateBaseUtils.WEB_FORMAT);
    }

    public static String getTomDayString(String sourceDateStr, String format){
        return getTomDayString(toDate(sourceDateStr,format));
    }

    /**
     * 取两个日期最小值
     *
     * @param source
     * @param target
     * @return
     */
    public static String minDateString(String source, String target, String format){
       if(DateBaseUtils.compare(source,target,format) >= 0){
           return target;
       } else {
           return source;
       }
    }

    /**
     * 取两个日期最大值
     *
     * @param source
     * @param target
     * @return
     */
    public static String maxDateString(String source, String target, String format){
        if(DateBaseUtils.compare(source,target,format) >= 0){
            return source;
        } else {
            return target;
        }
    }

    /**
     * 获得星期
     *
     * @param date
     * @return
     */
    public static int toWeek(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 判断当前日期是星期几
     *
     * @param   dateString 日期字符串
     * @param   format     日期格式
     * @return
     */
    public static int toWeek(String dateString, String format) {
        Date date = toDate(dateString,format);
        return toWeek(date);
    }

    /**
     * 判断当前日期是星期几
     *
     * @param   dateString 日期字符串
     * @param   format     日期格式
     * @return
     */
    public static String toWeekForZh(String dateString, String format) {
        return zhWeeks[toWeek(dateString,format)-1];
    }

    /**
     * 判断当前日期是星期几
     *
     * @param   date 日期
     * @return
     */
    public static String toWeekForZh(Date date) {
        return zhWeeks[toWeek(date)-1];
    }

    /**
     * 判断当前日期是星期几
     *
     * @param   dateString 日期字符串
     * @param   format     日期格式
     * @return
     */
    public static String toWeekForUs(String dateString, String format) {
        return usWeeks[toWeek(dateString,format)-1];
    }

    /**
     * 判断当前日期是星期几
     *
     * @param   date 日期
     * @return
     */
    public static String toWeekForUs(Date date) {
        return usWeeks[toWeek(date)-1];
    }

    /**
     * 判断当前时间是上午还是下午
     *
     * @param   dateString 日期字符串
     * @param   format     日期格式  必须包括小时
     * @return
     */
    public static int toDayPeriod(String dateString, String format) {
        Date date = toDate(dateString,format);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.AM_PM);
    }

    /**
     * 判断当前时间是上午还是下午
     *
     * @param   dateString 日期字符串
     * @param   format     日期格式  必须包括小时
     * @return
     */
    public static String toDayPeriodForZh(String dateString, String format) {
        return zhDayPeriod[toDayPeriod(dateString,format)];
    }

    /**
     * 判断当前时间是上午还是下午
     *
     * @param   dateString 日期字符串
     * @param   format     日期格式  必须包括小时
     * @return
     */
    public static String toDayPeriodForUs(String dateString, String format) {
        return usDayPeriod[toDayPeriod(dateString,format)];
    }

    /**
     * date转String
     *
     * @param format
     * @param date
     * @return
     */
    public static String date2String(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * date转String
     *
     * @param format
     * @param date
     * @return
     */
    public static String coverDateToString(String format, Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 计算两个日期之间的分钟数
     * @param start
     * @param end
     * @return
     */
    public static BigDecimal countTimeDiffMin(Date start, Date end) {
        long cost = end.getTime() - start.getTime();
        BigDecimal b = new BigDecimal((cost / 1000) / 60);
        BigDecimal f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP);
        return f1;
    }

    /**
     * 使用预设格式将字符串转为Date
     */
    public static Date parse(String strDate) throws ParseException {
        return StringUtils.isBlank(strDate) ? null : parse(strDate, "yyyy-MM-dd HH:mm:ss");
    }

    private static Date parse(String yearMonth, String dateFormat) {
        Date date;
        try {
            date = new SimpleDateFormat(dateFormat).parse(yearMonth);
        } catch (Exception pe) {
            return null;
        }
        return date;
    }

    private static Date addDate(Date date, int iArg0, int iDate) {
        Calendar canlendar = Calendar.getInstance();
        canlendar.setTime(date);
        canlendar.add(iArg0, iDate);
        return canlendar.getTime();
    }

    public static Date addDay(Date date, int iDate) {
        return addDate(date, 5, iDate);
    }

    public static String getDateFormate(Date date, String formate) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormate = new SimpleDateFormat(formate);
        return simpleDateFormate.format(date);
    }

    public static Date getDateNoTime(Date date) {
        return getDate(getYearByDate(date), getMonthByDate(date),
                getDayByDate(date));
    }

    public static Date getDate(int iYear, int iMonth, int iDate) {
        return getDate(iYear, iMonth, iDate, 0, 0, 0);
    }

    public static Date getDate(int iYear, int iMonth, int iDate, int iHour,
                               int iMinute, int iSecond) {
        --iMonth;
        Calendar canlendar = Calendar.getInstance();
        canlendar.clear();
        canlendar.set(iYear, iMonth, iDate, iHour, iMinute, iSecond);
        return canlendar.getTime();
    }

    public static int getYearByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(1);
    }

    public static int getMonthByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = 0;
        month = cal.get(2) + 1;
        if (month == 13) {
            month = 1;
        }
        return month;
    }

    public static int getDayByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(5);
    }

    /**
     * string转date
     * yangyang.peng
     * 2016年4月7日下午7:51:07
     * @param format
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date coverString2Date(String format, String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(strDate);
    }

    /**
     * string转date
     * yangyang.peng
     * 2016年4月7日下午7:51:07
     *
     * @param format
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date string2Date(String format, String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(strDate);
    }


    public static int compareDate(Date d1, Date d2) {
        if (d1 == null) {
            return -1;
        } else if (d2 == null) {
            return 1;
        } else if (d1.getTime() > d2.getTime()) {
            return 1;
        } else if (d1.getTime() < d2.getTime()) {
            return -1;
        } else {
            return 0;
        }

    }

    /**
     * 取得两个日期的间隔天数
     *
     * @param one
     * @param two
     *
     * @return 间隔天数
     */
    public static long diffDays(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();
        sysDate.setTime(one);
        Calendar failDate = new GregorianCalendar();
        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / (24 * 60 * 60 * 1000);
    }

    public static Date parseDateNoTime(String sDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(SHORT_FORMAT);

        if ((sDate == null) || (sDate.length() < SHORT_FORMAT.length())) {
            throw new ParseException("length too little", 0);
        }

        if (!StringUtils.isNumeric(sDate)) {
            throw new ParseException("not all digit", 0);
        }

        return dateFormat.parse(sDate);
    }

    public static Date parseDateNoTime(String sDate, String format) throws ParseException {
        if (StringUtils.isBlank(format)) {
            throw new ParseException("Null format. ", 0);
        }

        DateFormat dateFormat = new SimpleDateFormat(format);

        if ((sDate == null) || (sDate.length() < format.length())) {
            throw new ParseException("length too little", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 获得本天的开始时间
     *
     * @return
     */
    public static Date getDayStartTime(Date date) {
        return toDateWebFormat(getWebFormatDateString(date));
    }

    /**
     * 获得本天的结束时间
     *
     * @return
     */
    public static Date getDayEndTime(Date date) {
        return toDateFullFormat(getWebFormatDateString(date) + " 23:59:59.999");
    }

    /**
     * 获得本小时的开始时间
     *
     * @return
     */
    public static Date getHourStartTime(Date date) {
        return toDateHourFormat(getHourFormatDateString(date));
    }

    /**
     * 获得本小时的结束时间
     *
     * @return
     */
    public static Date getHourEndTime(Date date) {
        return toDateFullFormat(getHourFormatDateString(date) + ":59:59.999");
    }

    /**

     * 字符串转时间

     *

     * @param source yyyy-MM-dd HH:mm:ss.SSS 格式的字符串

     * @return

     */
    public final static String SHORT_DASH = "-";
    public final static String SLANTING_BAR = "/";
    public static Date toDate(String source) {

        if (source.contains(SLANTING_BAR)) {
            source = source.replace(SLANTING_BAR, SHORT_DASH);
        }

        String formatString = "yyyy-MM-dd hh:mm:ss";

        if (source == null || "".equals(source.trim())) {

            return null;

        }

        source = source.trim();

        if (source.matches("^\\d{4}$")) {

            formatString = "yyyy";

        } else if (source.matches("^\\d{4}-\\d{1,2}$")) {

            formatString = "yyyy-MM";

        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {

            formatString = "yyyy-MM-dd";

        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}$")) {

            formatString = "yyyy-MM-dd hh";

        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {

            formatString = "yyyy-MM-dd hh:mm";

        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {

            formatString = "yyyy-MM-dd hh:mm:ss";

        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}$")) {

            formatString = "yyyy-MM-dd HH:mm:ss.SSS";

        }

        try {

            SimpleDateFormat sdf = new SimpleDateFormat(formatString);

            Date date = sdf.parse(source);

            return date;

        } catch (ParseException e) {

            e.printStackTrace();

        }

        return null;

    }

    /**
     *
     * <pre>
     *   在查询传入的日期后面加上+00:00:00
     * </pre>
     *
     * @param sDate
     * @return
     */
    public static Date getQueryStartDate(Date sDate) throws Exception {
        String start = DateFormatUtils.format(sDate, "yyyy-MM-dd") + " 00:00:00";
        return DateUtils.parseDate(start, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     *
     * <pre>
     *   在查询传入的日期后面加上+23:59:59
     * </pre>
     *
     * @param sDate
     * @return
     */
    public static Date getQueryEndDate(Date sDate) throws Exception {
        String start = DateFormatUtils.format(sDate, "yyyy-MM-dd") + " 23:59:59";
        return DateUtils.parseDate(start, "yyyy-MM-dd HH:mm:ss");
    }
}