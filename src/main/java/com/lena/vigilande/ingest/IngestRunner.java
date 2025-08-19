package com.lena.vigilande.ingest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("ingest")
public class IngestRunner implements CommandLineRunner {
    private static final Logger log = LogManager.getLogger(IngestRunner.class);
    private IngestService script;

    public IngestRunner(IngestService script) {
        this.script = script;
    }
    @Override
    public void run(String... args) throws Exception {
        log.info("Writing to database from csv files...");

        script.ingestToScofflaws("datasets/Building_Code_Scofflaw_List_20250807.csv");
        script.ingestToViolations("datasets/Building_Violations_20250815.csv");

        log.info("Finished writing!");
        System.exit(0);
    }
}
