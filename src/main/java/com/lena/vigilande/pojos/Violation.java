package com.lena.vigilande.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;

public class Violation {
    @JsonIgnore
    private String id;
    @JsonIgnore
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

    public Violation(String id, String address, LocalDate violationDate, String violationCode, String violationDescription, String violationInspectorComments, String violationStatus) {
        this.id = id;
        this.address = address;
        this.violationDate = violationDate;
        this.violationCode = violationCode;
        this.violationDescription = violationDescription;
        this.violationInspectorComments = violationInspectorComments;
        this.violationStatus = violationStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Violation violation = (Violation) o;
        return Objects.equals(getViolationDate(), violation.getViolationDate()) && Objects.equals(getViolationCode(), violation.getViolationCode()) && Objects.equals(getViolationDescription(), violation.getViolationDescription()) && Objects.equals(getViolationInspectorComments(), violation.getViolationInspectorComments()) && Objects.equals(getViolationStatus(), violation.getViolationStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getViolationDate(), getViolationCode(), getViolationDescription(), getViolationInspectorComments(), getViolationStatus());
    }
}
