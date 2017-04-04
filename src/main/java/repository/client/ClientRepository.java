package repository.client;

import model.Account;
import model.Client;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by hanna on 26.03.2017.
 */
public interface ClientRepository {

    List<Client> findAll();

    Client findById(Long id) throws EntityNotFoundException;

    boolean save(Client client);

    boolean delete(Long id);

    boolean update(Client client);

    void removeAll();




}
