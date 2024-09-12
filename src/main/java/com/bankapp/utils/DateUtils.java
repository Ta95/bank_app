package com.bankapp.utils;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class DateUtils {


   public static final DateTimeFormatter YYYYMMDDHHMMSS_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");



   public  static String formatDate(Timestamp date){
       return date.toLocalDateTime().format(YYYYMMDDHHMMSS_FORMATTER);
   }
}
