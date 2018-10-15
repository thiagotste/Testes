
package com.br.library.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class DateUtils {

    public static Timestamp convertToTimestamp(String dt) throws ParseException {
        Timestamp ts = null;
        DateFormat dateFormat = new SimpleDateFormat(
                "E MMM dd yyyy HH:mm:ss 'GMT'z", Locale.US);

        ts = new Timestamp(dateFormat.parse(dt).getTime());
        return ts;
    }

    public static String formatDate(Date date, String type) {
        SimpleDateFormat sdf = null;
        if (type.equals("date")) {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
        } else if (type.equals("time")) {
            sdf = new SimpleDateFormat("HH:mm:ss");
        } else {
            sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        }
        return sdf.format(date);
    }

    public static String endHour(String hour, String duration) {
        String endHour, aux = "", aux2 = "";
        if (hour == null || hour.equals("")) {
            hour = "12:00";
        }
        if (duration == null || duration.equals("")) {
            hour = "60";
        }
        int minutes = Integer.parseInt(duration);
        int minutes2, hour2;
        int inteira = minutes / 60;
        int resto = minutes % 60;
        String[] t = hour.split(Pattern.quote(":"));
        hour2 = Integer.parseInt(t[0]);
        minutes2 = Integer.parseInt(t[1]);
        if ("0".equals(Integer.toString(resto + minutes2))) {
            aux = "00";
        } else if ((resto + minutes2) < 10) {
            aux = "0" + (resto + minutes2);
        } else {
            aux = Integer.toString(resto + minutes2);
        }
        if ((hour2 + inteira) < 12) {
            aux2 = Integer.toString(hour2 + inteira);
            aux2 = "0" + aux2;
        } else if ((hour2 + inteira) == 24) {
            aux2 = "00";
        } else {
            aux2 = Integer.toString(hour2 + inteira);
        }
        endHour = aux2 + ":" + aux;
        return endHour;
    }

    public static String monthTransform(String returnMonth) {
        int month = 0;
        if (returnMonth != null && returnMonth != "") {
            month = Integer.parseInt(returnMonth);
        }
        switch (month) {
            case 1:
                return "JAN";
            case 2:
                return "FEV";
            case 3:
                return "MAR";
            case 4:
                return "ABR";
            case 5:
                return "MAI";
            case 6:
                return "JUN";
            case 7:
                return "JUL";
            case 8:
                return "AGO";
            case 9:
                return "SET";
            case 10:
                return "OUT";
            case 11:
                return "NOV";
            case 12:
                return "DEZ";
            default:
                return "Incorreto";
        }
    }

}
