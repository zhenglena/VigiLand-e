package com.lena.vigilande.ingest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class IngestServiceTest {

    @Mock
    JdbcTemplate jdbcTemplate;
    @InjectMocks
    private IngestService ingestService;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }


    @Test
    public void writeToViolationsTable_withTestCsv_successful() throws Exception {
        String csvPath = Paths.get(
                getClass().getClassLoader().getResource("Building_Violations_TEST.csv").toURI()
        ).toString();

        ingestService.ingestToViolations(csvPath);

        ArgumentCaptor<Object[]> captor = ArgumentCaptor.forClass(Object[].class);
        verify(jdbcTemplate, atLeastOnce()).update(anyString(), captor.capture());


        Object[] args = captor.getValue(); // last row captured
        assertEquals("4362232", args[0]);
        assertEquals(LocalDate.of(2025, Month.AUGUST, 15), args[1]);
        assertEquals("EV1110", args[2]);
        assertEquals("OPEN", args[3]);
        assertEquals("MAINTAIN OR REPAIR ELECT ELEVA", args[4]);
        assertEquals("PROVIDE DOOR RESTRICTORS - PASSENGER ELEV", args[5]);
        assertEquals("4145 N RAVENSWOOD AVE", args[6]);
    }

    @Test
    public void writeToScofflawsTable_withTestCsv_successful() throws Exception {
        String csvPath = Paths.get(
                getClass().getClassLoader().getResource("Scofflaw_list_TEST.csv").toURI()
        ).toString();

        ingestService.ingestToScofflaws(csvPath);

        ArgumentCaptor<Object[]> captor = ArgumentCaptor.forClass(Object[].class);
        verify(jdbcTemplate, atLeastOnce()).update(anyString(), captor.capture());


        Object[] args = captor.getValue();
        assertEquals("23-M1-401336-3/1/2025", args[0]);
        assertEquals(LocalDate.of(2025, Month.MARCH, 1), args[1]);
        assertEquals("1554 E 65TH ST", args[2]);
    }
}
