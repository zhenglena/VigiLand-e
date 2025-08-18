package com.lena.vigilande.parser;

import com.lena.vigilande.util.DateParser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateParserTest {

    @Test
    public void dateParser_successful() {
        String input = "08/08/2000";
        LocalDate actualDate = DateParser.parseDate(input);

        assertEquals(LocalDate.of(2000,Month.AUGUST,8), actualDate);
    }
}
