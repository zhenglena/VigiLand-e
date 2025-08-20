package com.lena.vigilande.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PathInputParser {

    /**
     * Parses the date from the CSV format (MM/DD/YYYY) into the db format (YYYY-MM-DD)
     * @param date String date from CSV
     * @return a LocalDate
     */
    public static LocalDate parseDateFromCsv(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/uuuu"));
    }

    /**
     * Converts the String containing the date into a LocalDate
     * @param date a String containing the date
     * @return a LocalDate
     */
    public static LocalDate parseDateFromUrl(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu-MM-dd"));
    }

    /**
     * Takes the String containing the address in the URL and replaces the '+' with a regular whitespace for querying
     * @param address A String containing an address with '+' between each component
     * @return a String address with whitespaces between each component
     */
    public static String parseStringFromUrl(String address) {
        return address.replaceAll("\\+", " ").trim().toUpperCase();
    }
}
