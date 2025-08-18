package com.lena.vigilande.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

//todo: make a builder?
public class Violation {
    private String id;
    private String address;

    @JsonProperty("violation_date")
    private LocalDate violationDate;
    @JsonProperty("violation_code")
    private String violationCode;
    @JsonProperty("violation_description")
    private String violationDescription;
    @JsonProperty("violation_inspector_comments")
    private String violationInspectorComments;
    @JsonProperty("violation_status")
    private String violationStatus;


    public Violation(String id, LocalDate violationDate, String violationCode, String violationDescription, String violationInspectorComments, String violationStatus, String address) {
        this.id = id;
        this.violationDate = violationDate;
        this.violationCode = violationCode;
        this.violationDescription = violationDescription;
        this.violationInspectorComments = violationInspectorComments;
        this.violationStatus = violationStatus;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getViolationDate() {
        return violationDate;
    }

    public void setViolationDate(LocalDate violationDate) {
        this.violationDate = violationDate;
    }

    public String getViolationCode() {
        return violationCode;
    }

    public void setViolationCode(String violationCode) {
        this.violationCode = violationCode;
    }

    public String getViolationDescription() {
        return violationDescription;
    }

    public void setViolationDescription(String violationDescription) {
        this.violationDescription = violationDescription;
    }

    public String getViolationInspectorComments() {
        return violationInspectorComments;
    }

    public void setViolationInspectorComments(String violationInspectorComments) {
        this.violationInspectorComments = violationInspectorComments;
    }

    public String getViolationStatus() {
        return violationStatus;
    }

    public void setViolationStatus(String violationStatus) {
        this.violationStatus = violationStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
