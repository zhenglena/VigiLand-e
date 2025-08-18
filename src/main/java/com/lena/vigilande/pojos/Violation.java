package com.lena.vigilande.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

//todo: make a builder?
public class Violation {
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


    public Violation(LocalDate violationDate, String violationCode, String violationDescription, String violationInspectorComments, String violationStatus) {
        this.violationDate = violationDate;
        this.violationCode = violationCode;
        this.violationDescription = violationDescription;
        this.violationInspectorComments = violationInspectorComments;
        this.violationStatus = violationStatus;
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

}
