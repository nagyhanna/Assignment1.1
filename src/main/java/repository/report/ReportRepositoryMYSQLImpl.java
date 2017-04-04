package repository.report;

import model.Report;
import model.builder.ReportBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanna on 27.03.2017.
 */
public class ReportRepositoryMYSQLImpl implements ReportRepository {

    private final Connection connection;

    public ReportRepositoryMYSQLImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Report> findAllReportOfAUser(Long userId) {
        List<Report> reports = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from report where user_id="+userId;
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
               reports.add(getReportFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }

    @Override
    public boolean addReport(Report report) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO report values (null, ?, ?, ?)");
            insertStatement.setLong(1, report.getUser_id());
            insertStatement.setString(2, report.getReport());
            insertStatement.setDate(3, new java.sql.Date(report.getDate().getTime()));
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private Report getReportFromResultSet(ResultSet rs) throws SQLException {
        return new ReportBuilder()
                .setId(rs.getLong("id"))
                .setUser_id(rs.getLong("user_id"))
                .setReport(rs.getString("report"))
                .setDate(new java.sql.Date(rs.getDate("date").getTime()))
                .build();
    }
}
