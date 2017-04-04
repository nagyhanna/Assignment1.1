package repository.report;

import database.DBConnectionFactory;
import model.Report;
import model.builder.ReportBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by hanna on 28.03.2017.
 */
public class ReportRepositoryMYSQLImplTest {

    private static ReportRepository reportRepository;

    @BeforeClass
    public static void setupClass() {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(false).getConnection();
        reportRepository = new ReportRepositoryMYSQLImpl(connection);

    }

    @Test
    public void addReport() throws Exception {

        Report report=new ReportBuilder().setUser_id(2L).setReport("Test").setDate(new Date()).build();
        assertTrue(reportRepository.addReport(report));

    }



}