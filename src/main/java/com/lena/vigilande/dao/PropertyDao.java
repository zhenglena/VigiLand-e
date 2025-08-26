package com.lena.vigilande.dao;

import com.lena.vigilande.dtos.CommentsRequest;
import com.lena.vigilande.pojos.Scofflaw;
import com.lena.vigilande.pojos.Violation;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PropertyDao {

    private final JdbcTemplate template;

    public PropertyDao(JdbcTemplate template) {
        this.template = template;
    }

    /**
     * Queries the Violations table with a given address.
     * @param address the address to query
     * @return a list of violations associated with the address. Returns an empty list if no violations are found.
     */
    public List<Violation> findViolationsByAddress(String address) {
        String sql = "SELECT violation_date, violation_code, violation_description, violation_inspector_comments," +
                "violation_status FROM Violations WHERE address = ? ORDER BY violation_date DESC";

        return template.query(sql, (rs, rowNum) -> new Violation(
                rs.getDate("violation_date").toLocalDate(),
                rs.getString("violation_code"),
                rs.getString("violation_description"),
                rs.getString("violation_inspector_comments"),
                rs.getString("violation_status")
        ), new SqlParameterValue(Types.VARCHAR, address));
    }

    /**
     * Queries the Scofflaws table with a given address.
     * @param address the address to query
     * @return a Scofflaw result
     */
    public Scofflaw findScofflawByAddress(String address) {
       String sql = "SELECT s.building_list_date FROM Scofflaws s WHERE address = ? LIMIT 1";

        try {
            return template.queryForObject(sql, (rs, rowNum) -> new Scofflaw(
                           address,
                           rs.getDate("building_list_date").toLocalDate()
                   ), new SqlParameterValue(Types.VARCHAR, address));
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }

    }

    /**
     * Queries the Scofflaws table with a given date (inclusive).
     * @param fromDate the date to query
     * @return a List of Scofflaws
     */
    public List<Scofflaw> findScofflawsByDate(LocalDate fromDate) {
        String sql = "SELECT s.address, s.building_list_date FROM Scofflaws s WHERE s.building_list_date >= ?";

        return template.query(sql, (rs, rowNum) -> new Scofflaw(
                rs.getString("address"),
                rs.getDate("building_list_date").toLocalDate()
        ), new SqlParameterValue(Types.DATE, fromDate));
    }

    /**
     * Inserts a comment into the Comments table.
     * @param address the address of tied to the comment
     * @param request the request body containing the author and the comment to be inserted
     * @return an integer of rows affected by the insertion
     */
    public int createCommentWithAddress(String address, CommentsRequest request) {
        String sql = "INSERT INTO Comments(author, address, comment) VALUES (?, ?, ?)";

        return template.update(sql, request.getAuthor(), address, request.getComment());
    }
}
