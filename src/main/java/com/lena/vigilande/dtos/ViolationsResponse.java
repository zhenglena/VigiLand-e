package com.lena.vigilande.dtos;

import com.lena.vigilande.pojos.Violation;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViolationsResponse that = (ViolationsResponse) o;
        return getTotalViolationCount() == that.getTotalViolationCount() && isScofflawStatus() == that.isScofflawStatus() && Objects.equals(getLastViolationDate(), that.getLastViolationDate()) && Objects.equals(getViolationList(), that.getViolationList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLastViolationDate(), getTotalViolationCount(), getViolationList(), isScofflawStatus());
    }
}
