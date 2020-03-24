package com.zucc.user.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtils {

  private DateUtils() {}

  public static final String MONTH_FORMAT = "yyyyMM";
  public static final String DATE_FORMAT = "yyyy-MM-dd";
  public static final String DATE_FORMAT2 = "yyyyMMdd";
  public static final String DATE_FORMAT3 = "yyMMdd";
  public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  public static final String DATE_TIME_FORMAT2 = "yyyyMMddHHmmss";
  public static final String DATE_TIME_FORMAT3 = "yyyyMMddHHmmssSSS";
  public static final String DATE_TIME_FORMAT4 = "yyyyMMddHHmm";
  public static final String DATE_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
  private static final String DATE_PATTERN = "\\d{4}(-\\d\\d){2}";
  private static final String DATE_TIME_PATTERN = "\\d{4}(-\\d\\d){2} \\d\\d(:\\d\\d){2}";
  private static final String DATE_PATTERN2 = "\\d{8}";
  private static final String DATE_TIME_PATTERN2 = "\\d{14}";
  private static final String DATE_TIME_PATTERN3 = "\\d{12}";
  private static final String MONTH_PATTERN = "\\d{6}";
  private static final String DATE_MINUTE_PATTERN = "\\d{4}(-\\d\\d){2} \\d\\d:\\d\\d";

  private static final String ERROR = "日期转换失败:\n";

  /**
   * 获取当前时间之前x日的00:00:00.
   * 
   * @param day 应为负数
   */
  public static Date minus(int day) {
    if (day == 1) {
      day = 0;
    }
    day = -day;
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, day);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }

  public static Date add(int day) {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, day);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }

  /**
   * 获取昨日时间.
   * 
   * @return 昨日时间
   */
  public static Date getYesterday() {
    Date date = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, -1);
    return cal.getTime();

  }

  /**
   * 计算N分钟的时间.
   * 
   * @param date 时间
   * @param minute 分钟
   * @return
   */
  public static Date addMinute(Date date, int minute) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.MINUTE, minute);
    return cal.getTime();
  }

  public static Date addMinute(Date date, Long minute) {
    return addMinute(date, minute.intValue());
  }

  /**
   * 获取本周的周一.
   *
   * @return 本周周一的date
   */
  public static Date getMondayOfWeek() {
    Calendar calendar = Calendar.getInstance();
    int week = calendar.get(Calendar.DAY_OF_WEEK);
    if (1 == week) {
      week = -6;
    } else {
      week = 2 - week;
    }
    calendar.add(Calendar.DAY_OF_MONTH, week);
    return calendar.getTime();
  }

  /**
   * 获取本周的周一.
   *
   * @return 本周周一的date
   */
  public static Date getLastMondayOfWeek() {
    Calendar calendar = Calendar.getInstance();
    int week = calendar.get(Calendar.DAY_OF_WEEK);
    if (1 == week) {
      week = -6;
    } else {
      week = 2 - week;
    }
    calendar.add(Calendar.DAY_OF_MONTH, week);
    calendar.add(Calendar.DATE, -7);
    return calendar.getTime();
  }

  /**
   * 获取本周周末.
   *
   * @return 本周周末
   */
  public static Date getSundayOfWeek() {
    Calendar calendar = Calendar.getInstance();
    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
    if (dayOfWeek == 0) {
      dayOfWeek = 7;
    }
    calendar.add(Calendar.DATE, -dayOfWeek + 7);
    return calendar.getTime();
  }

  /**
   * 获取当月第一天.
   *
   * @param date 时间
   * @return 当月第一天
   */
  public static Date getStartOfMonth(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DATE));
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    return cal.getTime();

  }

  /**
   * 获取当月最后一天.
   *
   * @param date 时间
   * @return 当月最后一天
   */
  public static Date getEndOfMonth(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    return cal.getTime();
  }

  /**
   * 获取一天开始.
   *
   * @param date 时间
   * @return 一天开始时间（00:00:00）
   */
  public static Date getStartOfDay(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);

    return cal.getTime();
  }

  /**
   * 获取一天结束.
   *
   * @param date 时间
   * @return 一天结束时间（23:59:59）
   */
  public static Date getEndOfDay(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    return cal.getTime();
  }

  /**
   * 获取昨天一天开始.
   *
   * @param date 时间
   * @return 一天开始时间（00:00:00）
   */
  public static Date getYestardayStartOfDay(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, -1);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);

    return cal.getTime();
  }

  /**
   * 获取昨天一天结束.
   *
   * @param date 时间
   * @return 一天结束时间（23:59:59）
   */
  public static Date getYestardayEndOfDay(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, -1);
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    return cal.getTime();
  }

  /**
   * 获取明天一天开始.
   *
   * @param date 时间
   * @return 一天开始时间（00:00:00）
   */
  public static Date getTomorrowStartOfDay(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, +1);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);

    return cal.getTime();
  }

  /**
   * 获取明天一天结束.
   *
   * @param date 时间
   * @return 一天结束时间（23:59:59）
   */
  public static Date getTomorrowEndOfDay(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, +1);
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    return cal.getTime();
  }

  /**
   * 获取后天一天开始.
   *
   * @param date 时间
   * @return 一天开始时间（00:00:00）
   */
  public static Date getAfterTomorrowStartOfDay(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, +2);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);

    return cal.getTime();
  }


  /**
   * 求出两个date相隔的秒数.
   *
   * @param from 开始时间
   * @param to 结束时间
   * @return 两个date相隔的时间。单位：秒
   */
  public static int getDifference(Date from, Date to) {
    if (from == null || to == null) {
      return -1;
    }
    Double result = Math.ceil((from.getTime() - to.getTime()) / 1000.0);
    return result.intValue();
  }

  /**
   * 秒数格式化为 hh:mm:ss.
   *
   * @param totalSecond 总秒数
   * @return 格式化后的时间字符串
   */
  public static String formatSecond(int totalSecond) {
    DecimalFormat dff = new DecimalFormat("00");
    int hour = totalSecond / (60 * 60);
    int min = (totalSecond - (hour * 60 * 60)) / 60;
    int second = totalSecond % 60;
    return dff.format(hour) + ":" + dff.format(min) + ":" + dff.format(second);
  }

  /**
   * 获取上月的第一天.
   * 
   * @return
   */
  public static Date getLastMonthStartOfDay() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MONTH, -1);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    return calendar.getTime();
  }

  /**
   * 获取上月的最后一天.
   * 
   * @return
   */
  public static Date getLastMonthEndOfDay() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    calendar.add(Calendar.DATE, -1);
    return calendar.getTime();
  }

  /**
   * add day.
   * 
   * @param date origin date
   * @param day add day
   * @return date
   */
  public static Date addDay(Date date, int day) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, day);
    return cal.getTime();
  }

  /**
   * 获取月份的天数.
   * 
   * @param date 当前时间
   * @return 当前时间是本月的第几天
   */
  public static int getDaysOfMonth(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
  }

  /**
   * 获取当天时间段(上午/下午).
   * 
   * @param date 当前时间
   * @return 当前时间是本月的第几天
   */
  public static String getPeriodOfDate(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.HOUR_OF_DAY) < 12 ? "上午" : "下午";
  }

  /**
   * 把字符串转为date.
   * 
   * @param date 时间字符串
   * @return date
   */
  public static Date string2Date(String date) {
    if (Pattern.matches(DATE_PATTERN2, date)) {
      try {
        return new SimpleDateFormat(DATE_FORMAT2).parse(date);
      } catch (ParseException ex) {
        log.error(ERROR, ex);
        return null;
      }
    } else if (Pattern.matches(MONTH_PATTERN, date)) {
      try {
        return new SimpleDateFormat(MONTH_FORMAT).parse(date);
      } catch (ParseException ex) {
        log.error(ERROR, ex);
        return null;
      }
    } else if (Pattern.matches(DATE_TIME_PATTERN2, date)) {
      try {
        return new SimpleDateFormat(DATE_TIME_FORMAT2).parse(date);
      } catch (ParseException ex) {
        log.error(ERROR, ex);
        return null;
      }
    } else if (Pattern.matches(DATE_PATTERN, date)) {
      try {
        return new SimpleDateFormat(DATE_FORMAT).parse(date);
      } catch (ParseException ex) {
        log.error(ERROR, ex);
        return null;
      }
    } else if (Pattern.matches(DATE_TIME_PATTERN, date)) {
      try {
        return new SimpleDateFormat(DATE_TIME_FORMAT).parse(date);
      } catch (ParseException ex) {
        log.error(ERROR, ex);
        return null;
      }
    } else if (Pattern.matches(DATE_MINUTE_PATTERN, date)) {
      try {
        return new SimpleDateFormat(DATE_MINUTE_FORMAT).parse(date);
      } catch (ParseException ex) {
        log.error(ERROR, ex);
        return null;
      }
    } else if (Pattern.matches(DATE_TIME_PATTERN3, date)) {
      try {
        return new SimpleDateFormat(DATE_TIME_FORMAT4).parse(date);
      } catch (ParseException ex) {
        log.error(ERROR, ex);
        return null;
      }
    } else if (StringUtils.isNumeric(date)) {
      return new Date(Long.parseLong(date));
    }
    return null;

  }

  /**
   * Gets the days.
   *
   * @param startDate the start date
   * @param endDate the end date
   * @return the days
   */
  public static List<Date> getDays(Date startDate, Date endDate) {

    List<Date> result = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);// 格式化为年月

    Calendar min = Calendar.getInstance();
    Calendar max = Calendar.getInstance();

    try {
      min.setTime(sdf.parse(sdf.format(startDate)));
      max.setTime(sdf.parse(sdf.format(endDate)));
    } catch (ParseException e) {
      log.error("获取开始结束时间内日期", e.fillInStackTrace());
    }
    min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), min.get(Calendar.DAY_OF_MONTH));
    max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), max.get(Calendar.DAY_OF_MONTH));
    max.add(Calendar.DAY_OF_MONTH, 1);
    Calendar curr = min;
    while (curr.before(max)) {
      result.add(curr.getTime());
      curr.add(Calendar.DAY_OF_MONTH, 1);
    }
    return result;
  }

  public static String format(Date date, String dateFormat) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
    return simpleDateFormat.format(date);
  }
}
