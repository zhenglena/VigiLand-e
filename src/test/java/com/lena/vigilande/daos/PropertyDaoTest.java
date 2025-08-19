package com.lena.vigilande.daos;

import com.lena.vigilande.dao.PropertyDao;
import com.lena.vigilande.dtos.CommentsRequest;
import com.lena.vigilande.pojos.Scofflaw;
import com.lena.vigilande.pojos.Violation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PropertyDaoTest {
    @Autowired
    private PropertyDao dao;

    private final Violation violation1 = new Violation(
            LocalDate.of(2025, Month.AUGUST, 15),
            "EV1110",
            "MAINTAIN OR REPAIR ELECT ELEVA",
            "PERFORM CAT1 TESTFRONT CAR",
            "OPEN"
            );
    private final Violation violation2 = new Violation(
            LocalDate.of(2025, Month.AUGUST, 15),
            "EV1110",
            "MAINTAIN OR REPAIR ELECT ELEVA",
            "REPAIR PHONES BOTH CARS",
            "OPEN"
    );
    private final Violation violation3 = new Violation(
            LocalDate.of(2025, Month.AUGUST, 15),
            "EV1110",
            "MAINTAIN OR REPAIR ELECT ELEVA",
            "REPAIR FRONT ELEVATOR OUT OF SERVICE FOR EXTENDED PERIOD OF TIME. DUE TO ELECTRICAL ISSUES.AFTER FIRE DEPT RESPONCE.",
            "OPEN"
    );
    private final Violation violation4 = new Violation(
            LocalDate.of(2025, Month.AUGUST, 15),
            "EV1110",
            "MAINTAIN OR REPAIR ELECT ELEVA",
            "REPAIR RETIRING CAM DEVICE ON REAR ELEVATOR",
            "OPEN"
    );
    private final List<Violation> expectedViolationList = List.of(
            violation1,
            violation2,
            violation3,
            violation4
    );

    private final Scofflaw scofflaw1 = new Scofflaw(
        "4230 S MICHIGAN AVE",
        LocalDate.of(2025, Month.MARCH, 1)
    );
    private final Scofflaw scofflaw2 = new Scofflaw(
            "7501 S JEFFERY BLVD",
            LocalDate.of(2025, Month.MARCH, 1)
    );
    private final Scofflaw scofflaw3 = new Scofflaw(
            "7548 S BLACKSTONE AVE",
            LocalDate.of(2025, Month.MARCH, 1)
    );
    private final Scofflaw scofflaw4 = new Scofflaw(
            "1554 E 65TH ST",
            LocalDate.of(2025, Month.MARCH, 1)
    );
    private final List<Scofflaw> expectedScofflawList = List.of(
            scofflaw1,
            scofflaw2,
            scofflaw3,
            scofflaw4
    );

    @Test
    public void findViolationsByAddress_successful_returnsList() {
        String address = "5036 N SHERIDAN RD";
        List<Violation> actualViolationList = dao.findViolationsByAddress(address);

        assertEquals(expectedViolationList.size(), actualViolationList.size());
        for (int i = 0; i < expectedViolationList.size(); i++) {
            assertEquals(expectedViolationList.get(i), actualViolationList.get(i));
        }
    }

    @Test
    public void findViolationsByAddress_noAddress_returnsEmptyList() {
        String address = "123 W GRACE ST";

        List<Violation> actualViolationList = dao.findViolationsByAddress(address);
        assertEquals(0, actualViolationList.size());
    }

    @Test
    public void findScofflawByAddress_successful_returnsOneEntry() {
        String address = "4230 S MICHIGAN AVE";
        Scofflaw actualScofflaw = dao.findScofflawByAddress(address);

        assertEquals(scofflaw1, actualScofflaw);
    }

    @Test
    public void findScofflawByAddress_noAddress_returnsNull() {
        String address = "123 W GRACE ST";
        Scofflaw actualScofflaw = dao.findScofflawByAddress(address);
        assertNull(actualScofflaw);
    }

    @Test
    public void findScofflawsByDate_successful_returnsList() {
        LocalDate fromDate = LocalDate.of(2025, Month.MARCH, 1);
        List<Scofflaw> actualScofflawList = dao.findScofflawsByDate(fromDate);

        assertEquals(expectedScofflawList.size(), actualScofflawList.size());

        for (int i = 0; i < expectedScofflawList.size(); i++) {
            assertEquals(expectedScofflawList.get(i), actualScofflawList.get(i));
        }
    }

    @Test
    public void createCommentWithAddress_created() {
        String address = "4230 S MICHIGAN AVE";
        CommentsRequest request = new CommentsRequest(
                "I have cockroaches in my apartment!",
                "author1"
        );

        int actual = dao.createCommentWithAddress(address, request);
        assertEquals(1, actual);
    }
}
