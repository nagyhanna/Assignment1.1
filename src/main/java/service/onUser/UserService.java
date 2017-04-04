package service.onUser;

import model.Report;
import repository.user.UserRepository;

import java.util.List;

/**
 * Created by hanna on 26.03.2017.
 */
public interface UserService extends UserRepository {


    boolean addReportToUser(Report report);
    List<Report> generateReportOfEmployee(Long id);

}
