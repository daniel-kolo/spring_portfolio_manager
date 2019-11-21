package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.util.HashMap;
import java.util.Map;

public class Report {

    private Boolean dailyReport;
    private Boolean weeklyReport;
    private Map<String, Float> greaterThan = new HashMap<>();
    private Map<String, Float> lessThan = new HashMap<>();

    @JsonIgnore
    @OneToOne
    @MapsId
    private User user ;

    public Report(){}

    public Boolean getDailyReport() {
        return dailyReport;
    }

    public Boolean getWeeklyReport() {
        return weeklyReport;
    }

    public void setDailyReport(Boolean dailyReport) {
        this.dailyReport = dailyReport;
    }

    public void setWeeklyReport(Boolean weeklyReport) {
        this.weeklyReport = weeklyReport;
    }

    public void addGreaterThanNotification(String stockName, Float price){
        greaterThan.put(stockName,price);
    }

    public void addLessThanNotification(String stockName, Float price){
        lessThan.put(stockName,price);
    }

    public Map<String, Float> getGreaterThan() {
        return greaterThan;
    }

    public Map<String, Float> getLessThan() {
        return lessThan;
    }
}
