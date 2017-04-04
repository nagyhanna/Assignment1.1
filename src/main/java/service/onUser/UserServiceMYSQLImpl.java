package service.onUser;

import model.Report;
import model.User;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.report.ReportRepository;
import repository.user.UserRepository;

import java.util.List;

/**
 * Created by hanna on 26.03.2017.
 */
public class UserServiceMYSQLImpl implements UserService {

    private UserRepository userRepository;
    private ReportRepository reportRepository;

   public  UserServiceMYSQLImpl(UserRepository userRepository,ReportRepository reportRepository ){
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) throws EntityNotFoundException {
        return userRepository.findById(id);
    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public boolean save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean deleteByUsername(String username) {
        return userRepository.deleteByUsername(username);
    }

    @Override
    public boolean update(User user) {
        return userRepository.update(user);
    }

    @Override
    public void removeAll() {
       userRepository.removeAll();
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public boolean addReportToUser(Report report) {
        return reportRepository.addReport(report);

    }


    @Override
    public List<Report> generateReportOfEmployee(Long id) {
       return reportRepository.findAllReportOfAUser(id);
    }
}
