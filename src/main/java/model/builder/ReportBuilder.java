package model.builder;

import model.Report;

import java.util.Date;

/**
 * Created by hanna on 27.03.2017.
 */
public class ReportBuilder {

    private Report report;

    public ReportBuilder(){
        this.report = new Report();
    }

    public ReportBuilder setId(Long id){
        report.setId(id);
        return this;
    }
    public ReportBuilder setUser_id(Long id){
        report.setUser_id(id);
        return this;
    }
    public ReportBuilder setReport(String reportOfUser){
        report.setReport(reportOfUser);
        return this;
    }
    public ReportBuilder setDate(Date date){
        report.setDate(date);
        return this;
    }
    public Report build(){
        return this.report;
    }
}
