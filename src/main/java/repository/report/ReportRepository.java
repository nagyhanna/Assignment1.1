package repository.report;

import model.Report;

import java.util.List;

/**
 * Created by hanna on 27.03.2017.
 */
public interface ReportRepository {


    List<Report> findAllReportOfAUser(Long userId);
    boolean addReport(Report report);

}
