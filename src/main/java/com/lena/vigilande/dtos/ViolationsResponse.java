package com.lena.vigilande.dtos;

import com.lena.vigilande.pojos.Violation;

import java.time.LocalDate;
import java.util.List;

public class ViolationsResponse {
    private LocalDate lastViolationDate;
    private int totalViolationCount;
    private List<Violation> violationList;
    private boolean scofflawStatus;

    public ViolationsResponse(LocalDate lastViolationDate, int totalViolationCount, List<Violation> violationList, boolean scofflawStatus) {
        this.lastViolationDate = lastViolationDate;
        this.totalViolationCount = totalViolationCount;
        this.violationList = violationList;
        this.scofflawStatus = scofflawStatus;
    }

    public LocalDate getLastViolationDate() {
        return lastViolationDate;
    }

    public void setLastViolationDate(LocalDate lastViolationDate) {
        this.lastViolationDate = lastViolationDate;
    }

    public int getTotalViolationCount() {
        return totalViolationCount;
    }

    public void setTotalViolationCount(int totalViolationCount) {
        this.totalViolationCount = totalViolationCount;
    }

    public List<Violation> getViolationList() {
        return violationList;
    }

    public void setViolationList(List<Violation> violationList) {
        this.violationList = violationList;
    }

    public boolean isScofflawStatus() {
        return scofflawStatus;
    }

    public void setScofflawStatus(boolean scofflawStatus) {
        this.scofflawStatus = scofflawStatus;
    }
}
