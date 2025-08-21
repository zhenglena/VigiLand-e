package com.lena.vigilande.ingest;

import com.lena.vigilande.util.PathInputParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

//todo: implement batch writing

@Service
public class IngestService {

    private static final Logger log = LogManager.getLogger(IngestService.class);
    private final JdbcTemplate template;

    public IngestService(JdbcTemplate template) {
        this.template = template;
    }

    /**
     * Reads from the Building Violations CSV file and writes to Postgres db.
     * @param csvFilePath String path to Building Violations CSV
     * @throws IOException- If an I/O error occurs opening the filew
     */
    public void ingestToViolations(String csvFilePath) throws IOException {
        try (
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader());
        ) {
            for (CSVRecord record : csvParser) {
                String id = record.get("ID");
                String violation_code = record.get("VIOLATION CODE");
                String violation_status = record.get("VIOLATION STATUS");
                String violation_description = record.get("VIOLATION DESCRIPTION");
                String violation_inspector_comment = record.get("VIOLATION INSPECTOR COMMENTS");

                LocalDate violation_date = PathInputParser.parseDateFromCsv(record.get("VIOLATION DATE"));

                String address = record.get("ADDRESS").trim().toUpperCase();

                String sqlCommand = "INSERT INTO Violations(id, violation_date, violation_code, violation_status, " +
                        "violation_description, violation_inspector_comments, address) VALUES (?, ?, ?, ?, ?, ?, ?)";

                template.update(sqlCommand, id, violation_date, violation_code, violation_status,
                        violation_description, violation_inspector_comment, address);
            }
        }
    }

    /**
     * Reads from the Scofflaw List CSV file and writes to Postgres db.
     * @param csvFilePath String path to Scofflaws List CSV
     * @throws IOException- If an I/O error occurs opening the file
     */
    public void ingestToScofflaws(String csvFilePath) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader());
        ) {
            for (CSVRecord record : csvParser) {
                String record_id = record.get("RECORD ID");

                LocalDate building_list_date = PathInputParser.parseDateFromCsv(record.get("BUILDING LIST DATE"));

                String address = record.get("ADDRESS").trim().toUpperCase();

                String sqlCommand = "INSERT INTO Scofflaws(record_id, building_list_date, address) VALUES (?, ?, ?)";

                template.update(sqlCommand, record_id, building_list_date, address);
            }
        }
    }
}
