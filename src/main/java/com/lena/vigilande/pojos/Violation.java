package com.lena.vigilande.pojos;

import java.time.LocalDate;

//todo: make a builder?
public class Violation {
    private String id;
    private LocalDate date;
    private String violationCode;
    private String violationDescription;
    private String violationInspectorComments;
    private String violationStatus;
    private String address;

    public Violation(String id, LocalDate date, String violationCode, String violationDescription, String violationInspectorComments, String violationStatus, String address) {
        this.id = id;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
