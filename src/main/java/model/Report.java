package model;

import java.util.Date;

/**
 * Created by hanna on 27.03.2017.
 */
public class Report {

    private Long id;
    private Long user_id;
    private String report;
    private Date date;


    public Report(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString(){
        return " id:"+id+" user_id:"+user_id+" "+report+" date:"+date;
    }
}
