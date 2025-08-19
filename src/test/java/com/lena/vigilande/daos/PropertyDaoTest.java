package com.lena.vigilande.daos;

import com.lena.vigilande.dao.PropertyDao;
import com.lena.vigilande.dtos.CommentsRequest;
import com.lena.vigilande.helpers.TestHelpers;
import com.lena.vigilande.pojos.Scofflaw;
import com.lena.vigilande.pojos.Violation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PropertyDaoTest {
    @Autowired
    private PropertyDao dao;


    @Test
    public void findViolationsByAddress_successful_returnsList() {
        List<Violation> actualViolationList = dao.findViolationsByAddress(TestHelpers.VIOLATION_ADDRESS);

        assertEquals(TestHelpers.EXPECTED_VIOLATION_LIST.size(), actualViolationList.size());
        for (int i = 0; i < TestHelpers.EXPECTED_VIOLATION_LIST.size(); i++) {
            assertEquals(TestHelpers.EXPECTED_VIOLATION_LIST.get(i), actualViolationList.get(i));
        }
    }

    @Test
    public void findViolationsByAddress_noAddress_returnsEmptyList() {
        String address = "123 W GRACE ST";

        List<Violation> actualViolationList = dao.findViolationsByAddress(address);
        assertEquals(0, actualViolationList.size());
    }

    @Test
    public void findScofflawByAddress_successful_returnsOneEntry() {
        Scofflaw actualScofflaw = dao.findScofflawByAddress(TestHelpers.SCOFFLAW_ADDRESS);

        assertEquals(TestHelpers.SCOFFLAW1, actualScofflaw);
    }

    @Test
    public void findScofflawByAddress_noAddress_returnsNull() {
        String address = "123 W GRACE ST";
        Scofflaw actualScofflaw = dao.findScofflawByAddress(address);
        assertNull(actualScofflaw);
    }

    @Test
    public void findScofflawsByDate_successful_returnsList() {
        List<Scofflaw> actualScofflawList = dao.findScofflawsByDate(TestHelpers.SCOFFLAW_DATE);

        assertEquals(TestHelpers.EXPECTED_SCOFFLAW_LIST.size(), actualScofflawList.size());

        for (int i = 0; i < TestHelpers.EXPECTED_SCOFFLAW_LIST.size(); i++) {
            assertEquals(TestHelpers.EXPECTED_SCOFFLAW_LIST.get(i), actualScofflawList.get(i));
        }
    }

    @Test
    public void createCommentWithAddress_created() {
        CommentsRequest request = new CommentsRequest(
                "I have cockroaches in my apartment!",
                "author1"
        );

        int actual = dao.createCommentWithAddress(TestHelpers.SCOFFLAW_ADDRESS, request);
        assertEquals(1, actual);
    }
}
