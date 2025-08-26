package com.lena.vigilande.ingest;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class IngestServiceTest {
    @Mock
    private JdbcTemplate template;
    private IngestService ingestService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ingestService = new IngestService(template);

    }

    @Test
    public void writeToViolationsTable_withTestCsv_successful() throws Exception {
        Path tempFile = Files.createTempFile("violations", ".csv");
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(tempFile.toFile()), CSVFormat.DEFAULT.builder()
                .setHeader("ID", "VIOLATION DATE", "VIOLATION CODE", "VIOLATION STATUS", "VIOLATION DESCRIPTION",
                        "VIOLATION INSPECTOR COMMENTS", "ADDRESS")
                .build())
        ) {
            printer.printRecord("123", "08/15/2025", "EV1110", "OPEN", "DESC", "COMMENTS", "123 W GRACE ST");
        }

        ingestService.ingestToViolations(tempFile.toString());

        verify(template, times(1)).batchUpdate(anyString(), anyCollection(), anyInt(), any());
    }

    @Test
    public void writeToScofflawsTable_withTestCsv_successful() throws Exception {
        Path tempFile = Files.createTempFile("scofflaws", ".csv");
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(tempFile.toFile()), CSVFormat.DEFAULT.builder()
                .setHeader("RECORD ID", "BUILDING LIST DATE", "ADDRESS")
                .build())
        ) {
            printer.printRecord("456", "03/01/2025", "123 W GRACE ST");
        }

        ingestService.ingestToScofflaws(tempFile.toString());

        verify(template, times(1)).batchUpdate(anyString(), anyCollection(), anyInt(), any());
    }
}
