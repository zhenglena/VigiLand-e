package com.lena.vigilande.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {

    public static LocalDate parseDate(String date) {

        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/uuuu"));

        return localDate;
    }
}
