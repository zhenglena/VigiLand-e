package com.lena.vigilande.ingest;

import com.lena.vigilande.util.DateParser;
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
import java.time.format.DateTimeParseException;

@Service
public class IngestService {

    private static final Logger log = LogManager.getLogger(IngestService.class);
    private final JdbcTemplate template;

    public IngestService(JdbcTemplate template) {
        this.template = template;
    }

    /**
     * Reads from the Building Violations CSV file and
     * @param csvFilePath
     * @throws IOException
     */
    public void ViolationsReader(String csvFilePath) throws IOException {
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
                String violation_inspector_comment = record.get("VIOLATION INSPECTOR COMMENT");

                LocalDate violation_date = DateParser.parseDate(record.get("VIOLATION DATE"));

                //TODO: PARSE ADDRESS?
                String address = record.get("ADDRESS");


                String sqlCommand = "INSERT INTO Violations(id, violation_date, violation_code, violation_status, " +
                        "violation_description, violation_inspector_comment, address) VALUES (?, ?, ?, ?, ?, ?, ?)";

                template.update(sqlCommand, id, violation_date, violation_code, violation_status,
                        violation_description, violation_inspector_comment, address);
            }
        }
    }

    public void ScofflawsReader(String csvFilePath) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader());
        ) {
            for (CSVRecord record : csvParser) {
                String record_id = record.get("RECORD ID");
                //TODO: PARSE DATE
                String building_list_date = record.get("BUILDING LIST DATE"); //date comes in as MM/DD/YYYY
                //TODO: PARSE ADDRESS?
                String address = record.get("ADDRESS");

                String sqlCommand = "INSERT INTO Scofflaws(record_id, building_list_date, address) VALUES (?, ?, ?)";

                template.update(sqlCommand, record_id, building_list_date, address);
            }
        }
    }
}
