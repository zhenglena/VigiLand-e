package com.lena.vigilande.ingest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class IngestServiceTest {
    @Autowired
    private IngestService ingestService;

    @Test
    public void writeToViolationsTable_successful() {
        try {
            ingestService.ingestToViolations("datasets/Building_Violations_TEST.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeToScofflawsTable_successful() {
        try {
            ingestService.ingestToScofflaws("datasets/Scofflaw_List_TEST.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
