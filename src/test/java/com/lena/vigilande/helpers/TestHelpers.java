package com.lena.vigilande.helpers;

import com.lena.vigilande.dtos.ViolationsResponse;
import com.lena.vigilande.pojos.Scofflaw;
import com.lena.vigilande.pojos.Violation;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class TestHelpers {
    public static final String VIOLATION_ADDRESS = "5036 N SHERIDAN RD";
    public static final Violation VIOLATION1 = new Violation(
            "7331768",
            VIOLATION_ADDRESS,
            LocalDate.of(2025, Month.AUGUST, 15),
            "EV1110",
            "MAINTAIN OR REPAIR ELECT ELEVA",
            "PERFORM CAT1 TESTFRONT CAR",
            "OPEN"
    );

    public static final Violation VIOLATION2 = new Violation(
            "7331769",
            VIOLATION_ADDRESS,
            LocalDate.of(2025, Month.AUGUST, 15),
            "EV1110",
            "MAINTAIN OR REPAIR ELECT ELEVA",
            "REPAIR PHONES BOTH CARS",
            "OPEN"
    );

    public static final Violation VIOLATION3 = new Violation(
            "7331770",
            VIOLATION_ADDRESS,
            LocalDate.of(2025, Month.AUGUST, 15),
            "EV1110",
            "MAINTAIN OR REPAIR ELECT ELEVA",
            "REPAIR FRONT ELEVATOR OUT OF SERVICE FOR EXTENDED PERIOD OF TIME. DUE TO ELECTRICAL ISSUES.AFTER FIRE DEPT RESPONCE.",
            "OPEN"
    );

    public static final Violation VIOLATION4 = new Violation(
            "7331771",
            VIOLATION_ADDRESS,
            LocalDate.of(2025, Month.AUGUST, 15),
            "EV1110",
            "MAINTAIN OR REPAIR ELECT ELEVA",
            "REPAIR RETIRING CAM DEVICE ON REAR ELEVATOR",
            "OPEN"
    );

    public static final List<Violation> EXPECTED_VIOLATION_LIST = List.of(
            VIOLATION1,
            VIOLATION2,
            VIOLATION3,
            VIOLATION4
    );

    public static final ViolationsResponse EXPECTED_VIOLATIONS_RESPONSE = new ViolationsResponse(
            LocalDate.of(2025, Month.AUGUST, 15),
            4,
            TestHelpers.EXPECTED_VIOLATION_LIST,
            false
    );

    public static final String SCOFFLAW_ADDRESS = "4230 S MICHIGAN AVE";
    public static final LocalDate SCOFFLAW_DATE = LocalDate.of(2025, Month.MARCH, 1);
    public static final Scofflaw SCOFFLAW1 = new Scofflaw(
            "23-M1-400069-3/1/2025",
            "4230 S MICHIGAN AVE",
            LocalDate.of(2025, Month.MARCH, 1)
    );
    public static final Scofflaw SCOFFLAW2 = new Scofflaw(
            "16-M1-403519-3/1/2025",
            "7501 S JEFFERY BLVD",
            LocalDate.of(2025, Month.MARCH, 1)
    );
    public static final Scofflaw SCOFFLAW3 = new Scofflaw(
            "22-M1-400122-3/1/2025",
            "7548 S BLACKSTONE AVE",
            LocalDate.of(2025, Month.MARCH, 1)
    );
    public static final Scofflaw SCOFFLAW4 = new Scofflaw(
            "23-M1-401336-3/1/2025",
            "1554 E 65TH ST",
            LocalDate.of(2025, Month.MARCH, 1)
    );
    public static final List<Scofflaw> EXPECTED_SCOFFLAW_LIST = List.of(
            SCOFFLAW1,
            SCOFFLAW2,
            SCOFFLAW3,
            SCOFFLAW4
    );

}
