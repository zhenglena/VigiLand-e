package com.lena.vigilande.parser;

import com.lena.vigilande.util.DateParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DateParserTest {

    @Test
    public void dateParser_successful() {
        String input = "08/08/2000";
        LocalDate actualDate = DateParser.parseDateFromCsv(input);

        assertEquals(LocalDate.of(2000,Month.AUGUST,8), actualDate);
    }
}
