package com.lena.vigilande.parser;

import com.lena.vigilande.util.PathInputParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PathInputParserTest {

    @Test
    public void parseDateFromCsv_successful() {
        String input = "08/08/2000";
        LocalDate actualDate = PathInputParser.parseDateFromCsv(input);

        assertEquals(LocalDate.of(2000,Month.AUGUST,8), actualDate);
    }

    @Test
    public void parseDateFromUrl_successful() {
        String input = "2000-08-08";
        LocalDate actualDate = PathInputParser.parseDateFromUrl(input);

        assertEquals(LocalDate.of(2000,Month.AUGUST,8), actualDate);
    }

    @Test
    public void parseStringFromUrl_successful() {
        String input = "123+W+GRACE+ST";
        String actualString = PathInputParser.parseStringFromUrl(input);

        assertEquals("123 W GRACE ST", actualString);
    }

}
