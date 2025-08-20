package com.lena.vigilande.controllers;

import com.lena.vigilande.dtos.CommentsRequest;
import com.lena.vigilande.dtos.ScofflawsResponse;
import com.lena.vigilande.dtos.ViolationsResponse;
import com.lena.vigilande.services.PropertyService;
import com.lena.vigilande.util.PathInputParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    /**
     * Retrieve a ViolationsResponse by querying an address.
     * @param address the address to query
     * @return 200 OK if successful, else a 404 Not Found
     */
    @GetMapping("/{address}")
    public ResponseEntity<?> getViolationsResponseByAddress(@PathVariable String address) {
        Optional<ViolationsResponse> response =
                propertyService.getViolationsResponseByAddress(PathInputParser.parseStringFromUrl(address));
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response.get());
    }

    /**
     * Retrieve a list of scofflaw addresses including and after a provided date.
     * @param fromDate the earliest date to query from
     * @return 200 OK
     */
    @GetMapping("/scofflaws/violations")
    public ResponseEntity<?> getScofflawsByDate(@RequestParam("since") String fromDate) {
        Optional<ScofflawsResponse> response = propertyService.getScofflawsByDate(PathInputParser.parseDateFromUrl(fromDate));
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response.get().getScofflaws());
    }

    /**
     * Create a user submitted comment tied to an address.
     * @param address the address the comment is referring to
     * @param commentsRequest- the comment body
     * @return 201 Created
     */
    @PostMapping("/{address}/comments")
    public ResponseEntity<?> createCommentWithAddress(@PathVariable String address, @RequestBody CommentsRequest commentsRequest) {
        boolean created = propertyService.createCommentWithAddress(PathInputParser.parseStringFromUrl(address),
                commentsRequest);
        if (!created) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created comment tied to " + address + ".");
    }
}
