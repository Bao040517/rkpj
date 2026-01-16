package dtos;

import java.math.BigDecimal;

public class Statistics {
    Integer month;
    Integer year;
    Integer day;
    BigDecimal revenue;

    public Statistics() {
    }

    public Statistics(Integer day, Integer month, BigDecimal revenue, Integer year) {
        this.day = day;
        this.month = month;
        this.revenue = revenue;
        this.year = year;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
