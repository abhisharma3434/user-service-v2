package com.stanzaliving.userv2.utils;

import static com.google.common.base.Preconditions.checkArgument;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import com.stanzaliving.userv2.constants.StanzaConstants;
import com.stanzaliving.userv2.enums.DatePart;
import org.apache.commons.text.CaseUtils;


import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
@UtilityClass
public class DateUtil {
	
    public final String DATETIME_WITH_AM_PM = "dd MMM, yyyy hh:mm:ss a";
    public final String DD_MMM_YYYY_FORMAT = "dd-MMM-yyyy";
    public final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public final String yyyy_MM_dd_FORMAT = "yyyy-MM-dd";

    public static Date getNormalizedTodayDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date normalizeDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getNormalizedPrevDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public String formatIst(Date date, String format) {
        return Instant.ofEpochMilli(date.getTime()).atZone(StanzaConstants.IST_TIMEZONEID).format(DateTimeFormatter.ofPattern(format));
    }

    public long getDaysBetweenDates(Date fromDate, Date toDate) {
        return ChronoUnit.DAYS.between(fromDate.toInstant(), toDate.toInstant());
    }

    public boolean isMidMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH) <= 15;
    }

    public static Date max(Date d1, Date d2) {
        if (d1 == null && d2 == null) return null;
        if (d1 == null) return d2;
        if (d2 == null) return d1;
        return (d1.after(d2)) ? d1 : d2;
    }

    public String customDateFormatter(Date dateInput, DateFormat dateFormat) {

        if (dateInput != null) {
            SimpleDateFormat formatterOutput = new SimpleDateFormat(dateFormat.getValue());
            return formatterOutput.format(dateInput);
        }

        return null;
    }

    public String customDateFormatter(LocalDate dateInput, DateFormat dateFormat) {

        if (dateInput != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat.getValue());
            return formatter.format(dateInput);
        }
        return null;
    }

    public String customDateFormatter(LocalDateTime localDateTime, DateFormat dateFormat) {

        if (Objects.nonNull(localDateTime)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat.getValue());

            return formatter.format(localDateTime);
        }

        return null;
    }

    public static String convertDateToString(Date date, SimpleDateFormat sdf) {
        return sdf.format(date);
    }

    public String customTimeFormatter(LocalTime timeInput, DateFormat dateFormat) {

        if (timeInput != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat.getValue());
            return formatter.format(timeInput);
        }
        return null;
    }

    public String convertLocalDateTimeToDateFormatString(LocalDateTime localDateTime, DateFormat dateFormat) {

        if (localDateTime != null) {
            Date date = convertToDate(localDateTime);
            return customDateFormatter(date, dateFormat);
        }

        return null;

    }

    public Date customDateParser(String dateInput, DateFormat dateFormat) {

        if (dateInput != null) {
            SimpleDateFormat formatterOutput = new SimpleDateFormat(dateFormat.getValue());
            try {
                return formatterOutput.parse(dateInput);
            } catch (ParseException e) {
                // Ignore
            }
        }

        return null;
    }

    public String changeDateFormat(String dateInput, DateFormat inputDateFormat, DateFormat outputDateFormat) {

        if (dateInput != null) {
            SimpleDateFormat formatterOutput = new SimpleDateFormat(inputDateFormat.getValue());
            try {
                return customDateFormatter(formatterOutput.parse(dateInput), outputDateFormat);
            } catch (ParseException e) {
                // Ignore
            }
        }

        return null;
    }

    public LocalDateTime customDateTimeParser(String dateInput) {
        return customDateTimeParser(dateInput, DateFormat.ELASTIC_SEARCH);
    }

    public LocalDateTime customDateTimeParser(String dateInput, DateFormat dateFormat) {

        if (dateInput != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat.getValue());
            try {
                return LocalDateTime.parse(dateInput, formatter);
            } catch (DateTimeParseException e) {
                // Ignore
            }
        }

        return null;
    }

    public Date convertToDate(LocalDateTime localdateTime) {
        Date date = null;
        if (Objects.nonNull(localdateTime)) {
            date = Date.from(localdateTime.atZone(ZoneId.of(StanzaConstants.IST_TIMEZONE)).toInstant());
        }
        return date;
    }

    public LocalDateTime convertToLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }

        ZoneId zoneId = ZoneId.of(StanzaConstants.IST_TIMEZONE);
        Instant instant = date.toInstant();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * Method to generate a list of all LocalDate between given date range. It includes the startDate and endDate both in list
     *
     * @param startDate
     * @param endDate
     * @return list of LocalDate including start and end date
     */
    public List<LocalDate> getAllLocalDatesForRange(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();

        while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
            dates.add(startDate);
            startDate = startDate.plusDays(1);
        }

        return dates;
    }

    public List<LocalDate> getAllLocalDatesBetweenRange(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();

        while (!startDate.isEqual(endDate)) {
            dates.add(startDate);
            startDate = startDate.plusDays(1);
        }

        return dates;
    }

    /**
     * Method to generate a list of all Date between given date range. It includes the startDate and endDate both in list
     *
     * @param startDate
     * @param endDate
     * @return list of Date including start and end date
     */
    public List<Date> getAllDatesForRange(LocalDate startDate, LocalDate endDate) {
        List<Date> dates = new ArrayList<>();

        while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
            dates.add(DateUtil.convertToDate(startDate));
            startDate = startDate.plusDays(1);
        }

        return dates;
    }

    public List<Date> getAllDatesBetweenRange(LocalDate startDate, LocalDate endDate) {
        List<Date> dates = new ArrayList<>();

        while (startDate.isBefore(endDate)) {
            dates.add(DateUtil.convertToDate(startDate));
            startDate = startDate.plusDays(1);
        }

        return dates;
    }

    public Date getYesterDate(Date date) {
        Date yesterday = null;
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, -1);
            yesterday = cal.getTime();
        }
        return yesterday;
    }

    public Date convertToDate(LocalDate localdate) {

        if (localdate == null) {
            return null;
        }

        return Date.from(localdate.atStartOfDay(ZoneId.of(StanzaConstants.IST_TIMEZONE)).toInstant());
    }

    public LocalDate convertToLocalDate(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.of(StanzaConstants.IST_TIMEZONE)).toLocalDate();
    }

    public LocalDateTime convertToLocalDateTime(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.of(StanzaConstants.IST_TIMEZONE)).toLocalDateTime();
    }

    public Date convertToDate(LocalTime localTime) {

        if (localTime == null) {
            return null;
        }

        return Date.from(localTime.atDate(LocalDate.now()).atZone(ZoneId.of(StanzaConstants.IST_TIMEZONE)).toInstant());
    }

    public Integer yearsBetween(LocalDate one, LocalDate two) {

        if (Objects.isNull(one) || Objects.isNull(two)) {
            return null;
        }

        return Period.between(one, two).getYears();
    }

    public LocalDate convertToLocalDate(Date date, String timeZone) {
        if (date == null) {
            return null;
        }

        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        }

        ZoneId zoneId = ZoneId.of(timeZone);
        Instant instant = date.toInstant();
        return instant.atZone(zoneId).toLocalDate();
    }

    public LocalDate convertToLocalDate(Date date) {
        return convertToLocalDate(date, StanzaConstants.IST_TIMEZONE);
    }

    public LocalDate getLocalDate(Date date) {
        return convertToLocalDate(date);
    }

    public LocalDate convertToLocalDateFromUTC(Date date) {
        return convertToLocalDate(date, StanzaConstants.UTC_TIMEZONE);
    }

    public boolean isLocalDateExpired(LocalDate localDate) {
        ZoneId zoneId = ZoneId.of(StanzaConstants.IST_TIMEZONE);
        return localDate.isBefore(LocalDate.now(zoneId));
    }

    public LocalDate getLocalDate() {
        ZoneId zoneId = ZoneId.of(StanzaConstants.IST_TIMEZONE);
        return LocalDate.now(zoneId);
    }

    public LocalTime convertToLocalTime(Date date) {
        if (date == null) {
            return null;
        }

        if (date instanceof java.sql.Date) {
            return new Time(((java.sql.Date) date).getTime()).toLocalTime();
        }

        ZoneId zoneId = ZoneId.of(StanzaConstants.IST_TIMEZONE);
        Instant instant = date.toInstant();
        return instant.atZone(zoneId).toLocalTime();
    }

    public long daysBetween(Date one, Date two) {
        return Math.abs(daysBetweenWithSign(one, two));
    }

    public long daysBetween(LocalDate one, LocalDate two) {
        return Math.abs(daysBetweenWithSign(one, two));
    }

    public long daysBetweenWithSign(Date one, Date two) {

        LocalDate start = convertToLocalDate(one);
        LocalDate end = convertToLocalDate(two);

        return daysBetweenWithSign(start, end);
    }

    public long daysBetweenWithSign(LocalDate one, LocalDate two) {
        return ChronoUnit.DAYS.between(one, two);
    }

    public int getMaxDaysInMonth(LocalDate date) {
        return date.lengthOfMonth();
    }

    public int getDaysInCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public int getMTDDaysCount() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public Date getLaterDate(Date d1, Date d2) {

        if (d1 == null && d2 == null) {
            return null;
        }

        if (d1 == null) {
            return d2;
        }

        if (d2 == null) {
            return d1;
        }

        return d1.after(d2) ? d1 : d2;
    }

    public Date getEarlierDate(Date d1, Date d2) {

        if (d1 == null && d2 == null) {
            return null;
        }

        if (d1 == null) {
            return d2;
        }

        if (d2 == null) {
            return d1;
        }

        return d1.before(d2) ? d1 : d2;
    }

    public LocalDate getLaterLocalDate(LocalDate d1, LocalDate d2) {

        if (d1 == null && d2 == null) {
            return null;
        }

        if (d1 == null) {
            return d2;
        }

        if (d2 == null) {
            return d1;
        }

        return d1.isAfter(d2) ? d1 : d2;
    }

    public LocalDate getEarlierLocalDate(LocalDate d1, LocalDate d2) {

        if (d1 == null && d2 == null) {
            return null;
        }

        if (d1 == null) {
            return d2;
        }

        if (d2 == null) {
            return d1;
        }

        return d1.isAfter(d2) ? d2 : d1;
    }

    public List<String> getListOfMonths(LocalDate startDate, LocalDate endDate) {
        LinkedHashSet<String> monthsList = new LinkedHashSet<>();
        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            monthsList.add(customDateFormatter(date, DateFormat.MONTH_FULL_NAME));
        }
        return new ArrayList<>(monthsList);
    }

    public static List<Month> getListOfMonthEnum(LocalDate startDate, LocalDate endDate) {
        LinkedHashSet<Month> monthsList = new LinkedHashSet<>();
        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            monthsList.add(date.getMonth());
        }
        return new ArrayList<>(monthsList);
    }

    public static LinkedHashMap<Month, Integer> getOrderedMapOfMonthYearEnum(LocalDate startDate, LocalDate endDate) {
        LinkedHashMap<Month, Integer> monthsYearMap = new LinkedHashMap<>();
        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            monthsYearMap.put(date.getMonth(), date.getYear());
        }
        return monthsYearMap;
    }

    public static List<String> getYearWeekSqlListOfWeeks(LocalDate startDate, LocalDate endDate) {
        LinkedHashSet<String> weeksList = new LinkedHashSet<>();
        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            Integer weekNumber = Integer.parseInt(customDateFormatter(date, DateFormat.WEEK_OF_YEAR));
            String week;
            if (weekNumber < 10) {
                week = "0" + weekNumber.toString();
            } else {
                week = weekNumber.toString();
            }
            weeksList.add(customDateFormatter(date, DateFormat.YEAR_IN_WEEK_OF_YEAR) + week);
        }

        return new ArrayList<>(weeksList);
    }

    public List<String> getFormattedListOfWeeksFromWeekOne(LocalDate startDate, LocalDate endDate) {
        List<String> weeksList = getYearWeekSqlListOfWeeks(startDate, endDate);

        List<String> weekListInFromOne = new ArrayList<>();
        for (Integer i = 1; i <= weeksList.size(); i++) {
            weekListInFromOne.add("Week " + i.toString());
        }

        return weekListInFromOne;
    }

    public List<String> getListOfDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return new ArrayList<>();
        }
        LinkedHashSet<String> dateList = new LinkedHashSet<>();
        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            dateList.add(customDateFormatter(date, DateFormat.YYYY_HIFEN_MM_HIFEN_DD));
        }
        return new ArrayList<>(dateList);
    }

    /**
     * when start date equals toDate, this method returns -1, can't be changed now as already used elsewhere
     * returns negative value if startDate is greater than endDate
     * use getAbsoluteCountOfDates instead
     */
    public Integer getCountOfDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return 0;
        }
        if (startDate.isBefore(endDate)) {
            return getListOfDates(startDate, endDate).size();
        } else {
            return -1 * getListOfDates(endDate, startDate).size();
        }
    }

    public Integer getAbsoluteCountOfDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return 0;
        }
        if (startDate.isBefore(endDate)) {
            return getListOfDates(startDate, endDate).size();
        } else {
            return getListOfDates(endDate, startDate).size();
        }
    }

    public LocalDate getCurrentMonthStartDate() {
        return getMonthStartDate(LocalDate.now());
    }

    public LocalDate getMonthStartDate(LocalDate localDate) {
        return localDate.withDayOfMonth(1);
    }

    public LocalDate getCurrentMonthEndDate() {
        return YearMonth.now().atEndOfMonth();
    }

    public LocalDate getMonthEndDate(LocalDate localDate) {
        return YearMonth.from(localDate).atEndOfMonth();
    }

    public Date getNMinutesBackTime(Integer minutes) {
        return getNMinutesBackTime(new Date(), minutes);
    }

    public Date getNMinutesBackTime(Date date, Integer minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, -(minutes));
        return cal.getTime();
    }

    public static Date addToDate(Date dateToBeAdjusted, Integer days, Integer months, Integer years, Boolean normalizeDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateToBeAdjusted);
        if (normalizeDate) {
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        }
        if (days != null) {
            calendar.add(Calendar.DAY_OF_MONTH, days);
        }
        if (months != null) {
            calendar.add(Calendar.MONTH, months);
        }
        if (years != null) {
            calendar.add(Calendar.YEAR, years);
        }
        return calendar.getTime();
    }

    public static String getDayOfMonthSuffix(LocalDate date) {
        int n = date.getDayOfMonth();
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    public Date getFormatedCleanDate(Date date, String format) {

        if (format == null || format.equals(""))
            format = "yyyy/MM/dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String dateString = dateFormat.format(date);
        Date formatedDate = date;
        try {
            formatedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            log.error("Exception while parsing date", e);
        }
        return formatedDate;
    }

    public Date customiseDateTime(Date date, int hour, int min, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.SECOND, seconds);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public Date putTimeIntoDate(Date date, Date timeDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, getPartsFromDate(timeDate, DatePart.HOUR));
        calendar.set(Calendar.MINUTE, getPartsFromDate(timeDate, DatePart.MINUTE));
        calendar.set(Calendar.SECOND, getPartsFromDate(timeDate, DatePart.SECOND));
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public int getPartsFromDate(Date date, DatePart datePart) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int requiredValue;
        switch (datePart) {
            case YEAR:
                requiredValue = cal.get(Calendar.YEAR);
                break;
            case MONTH:
                requiredValue = cal.get(Calendar.MONTH);
                break;
            case DATE:
                requiredValue = cal.get(Calendar.DATE);
                break;
            case HOUR:
                requiredValue = cal.get(Calendar.HOUR_OF_DAY);
                break;
            case MINUTE:
                requiredValue = cal.get(Calendar.MINUTE);
                break;
            case SECOND:
                requiredValue = cal.get(Calendar.SECOND);
            default:
                requiredValue = 0;
        }
        return requiredValue;
    }

    public LocalDate getMonthStartBeginningDate(Integer month, Integer year) {

        return LocalDate.of(year, month, 1);
    }

    public LocalDate getMonthEndBeginningDate(Integer month, Integer year) {
        LocalDate date = LocalDate.of(year, month, 1);
        return date.withDayOfMonth(date.lengthOfMonth());
    }

    public Date getTodayEndDate() {

        LocalDateTime today = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);

        return convertToDate(today);
    }

    public Date addDaysToDate(Date d1, long days) {
        long ltime = d1.getTime() + days * 24 * 60 * 60 * 1000;
        Date newdate = new Date(ltime);
        return newdate;
    }

    public Date addDayAndSetHour(Integer days, Integer hour) {

        LocalDateTime tomorrow = LocalDateTime.now().plusDays(days);
        tomorrow.withHour(hour);
        tomorrow.withMinute(0);
        tomorrow.withSecond(0);

        return convertToDate(tomorrow);
    }

    public int getLeaderboardWeekNumberForDate(LocalDate localDate) {

        int weekNumber = 1;

        if (localDate.getDayOfMonth() >= 22) {
            weekNumber = 4;
        } else if (localDate.getDayOfMonth() >= 15) {
            weekNumber = 3;
        } else if (localDate.getDayOfMonth() >= 8) {
            weekNumber = 2;
        }

        return weekNumber;
    }

    public static Map<String, LocalDate> getStartAndEndDateForFoodOrder(Integer week, LocalDate date) {

        Map<String, LocalDate> dateMap = new HashMap<>();
        if (week == 1) {
            dateMap.put("startDate", date.withDayOfMonth(1));
            dateMap.put("endDate", date);
            return dateMap;
        } else if (week == 2) {
            dateMap.put("startDate", date.withDayOfMonth(8));
            dateMap.put("endDate", date);
            return dateMap;
        } else if (week == 3) {
            dateMap.put("startDate", date.withDayOfMonth(15));
            dateMap.put("endDate", date);
            return dateMap;
        } else {
            dateMap.put("startDate", date.withDayOfMonth(22));
            dateMap.put("endDate", date);
            return dateMap;
        }
    }

    public static List<LocalDate> getWeekFirstAndLastDaysForLeaderboard(LocalDate date) {

        List<LocalDate> weekFirstAndLastDays = new ArrayList<>();
        Integer dayOfMonth = date.getDayOfMonth();

        if (dayOfMonth >= 1 && dayOfMonth <= 7) {
            weekFirstAndLastDays.add(date.withDayOfMonth(1));
            weekFirstAndLastDays.add(date.withDayOfMonth(7));
            return weekFirstAndLastDays;
        } else if (dayOfMonth >= 8 && dayOfMonth <= 14) {
            weekFirstAndLastDays.add(date.withDayOfMonth(8));
            weekFirstAndLastDays.add(date.withDayOfMonth(14));
            return weekFirstAndLastDays;
        } else if (dayOfMonth >= 15 && dayOfMonth <= 21) {
            weekFirstAndLastDays.add(date.withDayOfMonth(15));
            weekFirstAndLastDays.add(date.withDayOfMonth(21));
            return weekFirstAndLastDays;
        } else {
            weekFirstAndLastDays.add(date.withDayOfMonth(22));
            weekFirstAndLastDays.add(date.withDayOfMonth(date.lengthOfMonth()));
            return weekFirstAndLastDays;
        }
    }

    public static List<LocalDate> getFirstAndTillDayOfCurrentWeekForLeaderboard(LocalDate date) {

        List<LocalDate> firstAndTillDayOfCurrentWeek = new ArrayList<>();
        Integer dayOfMonth = date.getDayOfMonth();

        if (dayOfMonth >= 1 && dayOfMonth <= 7) {
            firstAndTillDayOfCurrentWeek.add(date.withDayOfMonth(1));
            firstAndTillDayOfCurrentWeek.add(date.withDayOfMonth(7));
            return firstAndTillDayOfCurrentWeek;
        } else if (dayOfMonth >= 8 && dayOfMonth <= 14) {
            firstAndTillDayOfCurrentWeek.add(date.withDayOfMonth(8));
            firstAndTillDayOfCurrentWeek.add(date.withDayOfMonth(14));
            return firstAndTillDayOfCurrentWeek;
        } else if (dayOfMonth >= 15 && dayOfMonth <= 21) {
            firstAndTillDayOfCurrentWeek.add(date.withDayOfMonth(15));
            firstAndTillDayOfCurrentWeek.add(date.withDayOfMonth(21));
            return firstAndTillDayOfCurrentWeek;
        } else {
            firstAndTillDayOfCurrentWeek.add(date.withDayOfMonth(22));
            firstAndTillDayOfCurrentWeek.add(date);
            return firstAndTillDayOfCurrentWeek;
        }

    }

    public String getDatePhrase(LocalDate localDate) {

        if (localDate.getDayOfMonth() == 1 || localDate.getDayOfMonth() == 21 || localDate.getDayOfMonth() == 31) {

            return "st";
        }

        if (localDate.getDayOfMonth() == 2 || localDate.getDayOfMonth() == 22) {

            return "nd";
        }

        if (localDate.getDayOfMonth() == 3 || localDate.getDayOfMonth() == 23) {

            return "rd";
        }

        return "th";

    }

    public String convertToAMPM(LocalTime localTime) {

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(Locale.US);

        return localTime.format(timeFormatter);

    }

    public String convertToStringDate(LocalDate localDate) {

        return String.valueOf(localDate.getDayOfMonth()) + " " + CaseUtils.toCamelCase(localDate.getMonth().toString(), true) + " " + String.valueOf(localDate.getYear());
    }

    public String getDayOfMonthSuffix(final int n) {
        checkArgument(n >= 1 && n <= 31, "illegal day of month: " + n);
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    /**
     * Method to generate a standard date String for display purpose
     *
     * @return Date string eg. 1st May, 2020
     */
    public String getStandardDateString(Date dateInput) {
        Integer datePart = getPartsFromDate(dateInput, DatePart.DATE);
        return datePart
                + getDayOfMonthSuffix(datePart) + " "
                + customDateFormatter(dateInput, DateFormat.MMMM_YYYY);
    }

    public static LocalDate getNextDayOfWeek(LocalDate date, DayOfWeek day) {

        return date.with(TemporalAdjusters.next(day));
    }

    public static int getMonthsBetweenDates(Date fromDate, Date toDate) {
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(fromDate);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(toDate);
        int diffYear = calEnd.get(Calendar.YEAR) - calStart.get(Calendar.YEAR);
        return diffYear * 12 + calEnd.get(Calendar.MONTH) - calStart.get(Calendar.MONTH);
    }

    public static long getDifferenceBetweenDates(Date d1, Date d2, String differenceIn) {
        long diff = d2.getTime() - d1.getTime();
        long requiredValue;
        switch (differenceIn) {

            case "DAYS":
                requiredValue = diff / (24 * 60 * 60 * 1000);
                break;
            case "HOURS":
                requiredValue = diff / (60 * 60 * 1000) % 24;
                break;
            case "MINUTES":
                requiredValue = diff / (60 * 1000) % 60;
                break;
            case "SECONDS":
                requiredValue = diff / 1000 % 60;
            default:
                requiredValue = 0;
        }
        return requiredValue;
    }

    public String getCurrentDateInSpecificFormat(LocalDate localDate) {
        int dayOfMonth = localDate.getDayOfMonth();
        String dayNumberSuffix = getDayNumberSuffix(dayOfMonth);
        String dayWithNumberSuffix = dayOfMonth + dayNumberSuffix;
        return dayWithNumberSuffix;
    }

    private String getDayNumberSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";
        }
        switch (day % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    /**
     * @param time
     * @param startTime
     * @param endTime
     * @return boolean
     * @apiNote written by piyush.srivastava "piyush.srivastava@stanzaliving.com"
     */
    public boolean isInBetween(LocalTime time, LocalTime startTime, LocalTime endTime) {

        boolean inBetween = false;

        if (time.isAfter(startTime) && time.isBefore(endTime)) {

            inBetween = true;

        }

        return inBetween;
    }

    public LocalDate min(LocalDate dateOne, LocalDate dateTwo) {

        if (dateOne.isBefore(dateTwo)) {
            return dateOne;
        }

        return dateTwo;
    }

    public static List<String> getListOfMonthYear(LocalDate fromDate, LocalDate toDate, DateFormat dateFormat) {
        LinkedHashSet<String> monthYear = new LinkedHashSet<>();
        if (!toDate.isAfter(fromDate)) {// TODO add additional validation
            return new ArrayList<>(monthYear);
        }
        for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
            monthYear.add(customDateFormatter(date, dateFormat));
        }
        return new ArrayList<>(monthYear);
    }

    public static int getDaysCountInMonthYear(LocalDate fromDate, LocalDate toDate, DateFormat dateFormat, String monthYear) {
        int count = 0;
        if (!toDate.isAfter(fromDate)) {// TODO add additional validation
            return count;
        }
        for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
            if (monthYear.equals(customDateFormatter(date, dateFormat))) {
                count += 1;
            }
        }
        return count;
    }

    public static List<LocalDate> getFirstDateOfMonthListBetweenDates(LocalDate fromDate, LocalDate toDate) {
        List<LocalDate> firstDateMonthList = new ArrayList<>();
        for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
            if (1 == date.getDayOfMonth()) {
                firstDateMonthList.add(date);
            }
        }
        return firstDateMonthList;
    }

    public static int getWeekendDaysCount(LocalDate fromDate, LocalDate toDate) {
        return getWeekendDays(fromDate, toDate).size();
    }

    public static List<LocalDate> getWeekendDays(LocalDate fromDate, LocalDate toDate) {
        List<LocalDate> weekendDays = new ArrayList<>();
        if (!toDate.isAfter(fromDate)) {
            return weekendDays;
        }
        for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
            if (DayOfWeek.SATURDAY.equals(date.getDayOfWeek()) || DayOfWeek.SUNDAY.equals(date.getDayOfWeek())) {
                weekendDays.add(date);
            }
        }
        return weekendDays;
    }

    public static double getMonthsBetweenDatesInDouble(LocalDate fromDate, LocalDate toDate) {

        List<String> monthYearList = DateUtil.getListOfMonthYear(fromDate, toDate, DateFormat.MMM_YY2);
        double monthCount = 0;
        if (fromDate.getDayOfMonth() != 1 && toDate.isAfter(fromDate) && (fromDate.getDayOfMonth() - 1) == toDate.getDayOfMonth()) {
            monthCount = 1;
            monthYearList.remove(0);
            monthYearList.remove(monthYearList.size() - 1);
        }
        for (String monthYear : monthYearList) {
            int daysToConsider = DateUtil.getDaysCountInMonthYear(fromDate, toDate, DateFormat.MMM_YY2, monthYear);
            int daysInMonth = YearMonth.parse(monthYear, DateFormat.MMM_YY2.getDateTimeFormatter()).lengthOfMonth();
            monthCount += (double) daysToConsider / (double) daysInMonth;
        }
        return Math.round(monthCount * 100.0) / 100.0;
    }

    public long getHoursBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (Objects.isNull(startDateTime) || Objects.isNull(endDateTime)) {
            return 0;
        }

        return getDurationBetween(startDateTime, endDateTime).toHours();
    }

    public Duration getDurationBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return Duration.between(startDateTime, endDateTime);
    }

    public String getNextDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();

        try {
            c.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        c.add(Calendar.DATE, 1);  // number of days to add

        date = sdf.format(c.getTime());

        return date;
    }

    public static Date getMaximumTimeOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static Date getCurrentDate() {
        return getCurrentDate(new Date());
    }

    public static Date getCurrentDate(Date date) {
        Calendar calendarInstance = Calendar.getInstance();
        calendarInstance.setTime(date);
        calendarInstance.set(Calendar.MINUTE, 00);
        calendarInstance.set(Calendar.HOUR_OF_DAY, 00);
        calendarInstance.set(Calendar.SECOND, 00);
        calendarInstance.set(Calendar.MILLISECOND,0);
        Date currentDate = calendarInstance.getTime();
        return currentDate;
    }

    public static String ConvertMilliSecondsToFormattedDate(String milliSeconds) {
        String dateFormat = "dd-MM-yyyy hh:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Date result = new Date(milliSeconds);
        return simpleDateFormat.format(result);
    }

    public static Period findDifference(Date start_date,
                                        Date end_date) {
        Period dateDifference
                = Period
                .between(start_date.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate(),
                        end_date.toInstant().atZone(ZoneId.systemDefault())
                                .toLocalDate());
        return dateDifference;
    }


    public static String dateDifferenceInString(Period date) {
        Integer year = date.getYears();
        Integer month = date.getMonths();
        Integer days = date.getDays();
        if (year != 0) month = year * 12 + month;
        String diff = " ";
        if (month != 0) diff += month == 1 ? month + " month " : month + " months ";
        if (days != 0) diff += days == 1 ? days + " day" : days + " days";
        return diff;
    }

    public String calculateDifferenceInMonthAndDate(Date startDate, Date endDate) {
        Period age = Period.between(DateUtil.convertToLocalDate(startDate),
                DateUtil.convertToLocalDate(endDate));
        int months = age.getMonths();
        int days = age.getDays();
        String datePeriod = "";
        if (months != 0)
            datePeriod += months == 1 ? months + " month " : months + " months ";
        if (days != 0)
            datePeriod += days == 1 ? days + " day" : days + " days";
        return datePeriod;
    }

    public static Integer calculatePeriod(Period date) {
        Integer year = date.getYears();
        Integer month = date.getMonths();
        Integer days = date.getDays();
        if (year != 0) month = year * 12 + month;
        if (days > 15) {
            month += 1;
        }
        return month;
    }

    public boolean isBetween(LocalDate checkDate, LocalDate startDate, LocalDate endDate) {
        return !checkDate.isBefore(startDate) && !checkDate.isAfter(endDate);
    }

    public static String dateDifferenceInString(Period date, Date endDate) {
        Integer year = date.getYears();
        Integer month = date.getMonths();
        Integer days = date.getDays();
        if (year != 0) month = year * 12 + month;
        String diff = " ";
        if (enableRoundOff(endDate.getMonth() + 1, days)) {
            month = month + 1;
            days = 0;
        }
        if (month != 0) diff += month == 1 ? month + " month " : month + " months ";
        if (days != 0) diff += days == 1 ? days + " day" : days + " days";
        return diff;
    }

    public static boolean enableRoundOff(int month, int day) {
        List<Integer> oddMonth = Arrays.asList(1, 3, 5, 7, 8, 10, 12);
        List<Integer> evenMonth = Arrays.asList(4, 6, 9, 11);
        if ((month == 2 && (day == 28 || day == 29)) ||
                (day == 31 && oddMonth.contains(month)) ||
                (day == 30 && evenMonth.contains(month))) return true;
        return false;
    }

    public static Boolean isDateInDateRange(Date date, LocalDate fromDate, LocalDate toDate) {
        LocalDate localDate = getLocalDate(date);
        return (fromDate.isBefore(localDate) || fromDate.equals(localDate)) && (toDate.isAfter(localDate) || toDate.equals(localDate));
    }

    public static List<LocalDate> getCalendarMonthOfYear(Integer month,Integer year) {

        LocalDate startDate = getMonthStartBeginningDate(month, year);
        LocalDate endDate = getMonthEndBeginningDate(month, year);

        DayOfWeek startDayOfMonth = startDate.getDayOfWeek();
        startDate = startDayOfMonth==DayOfWeek.MONDAY?startDate:startDate.minusDays(startDayOfMonth.getValue()-1);

        DayOfWeek endDayOfMonth = endDate.getDayOfWeek();
        endDate = endDate.plusDays(7-endDayOfMonth.getValue());

        return getAllLocalDatesForRange(startDate,endDate);

    }

    public static boolean isWeekDay(LocalDate date) {
        return date.getDayOfWeek()!=DayOfWeek.SATURDAY&&date.getDayOfWeek()!=DayOfWeek.SUNDAY;
    }

    public static Long convertToEpochInMiliSeconds(LocalDate localDate) {
        return Objects.isNull(localDate) ? null : localDate.atStartOfDay().toInstant(ZoneOffset.of(StanzaConstants.ZONE_OFFSET)).toEpochMilli();
    }
}