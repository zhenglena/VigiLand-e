package com.lena.vigilande.dao;

import com.lena.vigilande.dtos.CommentsRequest;
import com.lena.vigilande.pojos.Scofflaw;
import com.lena.vigilande.pojos.Violation;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

//TODO: handle exceptions or null results
public class PropertyDao {

    private final JdbcTemplate template;

    public PropertyDao(JdbcTemplate template) {
        this.template = template;
    }

    public List<Violation> findViolationsByAddress(String address) {
        String sql = "SELECT violation_date, violation_code, violation_description, violation_inspector_comments," +
                "violation_status FROM Violations WHERE address = ? ORDER BY violation_date DESC";
        return template.query(sql, new Object[]{address},
                (rs, rowNum) -> new Violation(
                                        rs.getDate("violation_date").toLocalDate(),
                                        rs.getString("violation_code"),
                                        rs.getString("violation_description"),
                                        rs.getString("violation_inspector_comments"),
                                        rs.getString("violation_status")
                ));
    }

    public Scofflaw findScofflawByAddress(String address) {
       String sql = "SELECT s.building_list_date FROM Scofflaws s WHERE address = ? LIMIT 1";
       return template.queryForObject(sql, new Object[]{address},
               (rs, rowNum) -> new Scofflaw(
                       address,
                       rs.getDate("building_list_date").toLocalDate()
               ));
    }

    public List<Scofflaw> findScofflawsByDate(LocalDate fromDate) {
        String sql = "SELECT s.address, s.building_list_date FROM Scofflaws s WHERE s.building_list_date >= ?";

        return template.query(sql, new Object[]{fromDate},
                (rs, rowNum) -> new Scofflaw(
                        rs.getString("address"),
                        rs.getDate("building_list_date").toLocalDate()
                ));
    }

    public int createCommentWithAddress(String address, CommentsRequest request) {
        String sql = "INSERT INTO Comments(author, address, comment) VALUES (?, ?, ?)";

        return template.update(sql, request.getAuthor(), address, request.getComment());
    }
}
