package com.example.tms_sona2;

public class Report_Details {
    private String report;
    private String reported_by;

    public Report_Details() {
    }

    public Report_Details(String report, String reported_by) {
        this.report = report;
        this.reported_by = reported_by;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getReported_by() {
        return reported_by;
    }

    public void setReported_by(String reported_by) {
        this.reported_by = reported_by;
    }
}
