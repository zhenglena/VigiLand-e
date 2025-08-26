package com.lena.vigilande.ingest;

import com.lena.vigilande.pojos.Scofflaw;
import com.lena.vigilande.pojos.Violation;
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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

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
        ) {
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .build();

            Collection<Violation> vCollection = new ArrayList<>();
            String sqlCommand = "INSERT INTO Violations(id, violation_date, violation_code, violation_status, " +
                    "violation_description, violation_inspector_comments, address) VALUES (?, ?, ?, ?, ?, ?, ?)";

            for (CSVRecord record : csvFormat.parse(reader)) {
                String id = record.get("ID");
                String code = record.get("VIOLATION CODE");
                String status = record.get("VIOLATION STATUS");
                String description = record.get("VIOLATION DESCRIPTION");
                String comment = record.get("VIOLATION INSPECTOR COMMENTS");
                LocalDate date = PathInputParser.parseDateFromCsv(record.get("VIOLATION DATE"));
                String address = record.get("ADDRESS").trim().toUpperCase();

                vCollection.add(new Violation(id, address, date, code, description, comment, status));
            }

            template.batchUpdate(sqlCommand, vCollection, 1000, (PreparedStatement ps, Violation v) -> {
                ps.setString(1, v.getId());
                ps.setDate(2, Date.valueOf(v.getViolationDate()));
                ps.setString(3, v.getViolationCode());
                ps.setString(4, v.getViolationStatus());
                ps.setString(5, v.getViolationDescription());
                ps.setString(6, v.getViolationInspectorComments());
                ps.setString(7, v.getAddress());
            });
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
        ) {
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .build();

            String sqlCommand = "INSERT INTO Scofflaws(record_id, building_list_date, address) VALUES (?, ?, ?)";
            Collection<Scofflaw> sCollection = new ArrayList<>();

            for (CSVRecord record : csvFormat.parse(reader)) {
                String recordId = record.get("RECORD ID");
                LocalDate date = PathInputParser.parseDateFromCsv(record.get("BUILDING LIST DATE"));
                String address = record.get("ADDRESS").trim().toUpperCase();

                sCollection.add(new Scofflaw(recordId, address, date));
            }

            template.batchUpdate(sqlCommand, sCollection, 100, (PreparedStatement ps, Scofflaw s) -> {
                ps.setString(1, s.getRecordId());
                ps.setDate(2, Date.valueOf(s.getBuildingListDate()));
                ps.setString(3, s.getAddress());
            });
        }
    }
}
