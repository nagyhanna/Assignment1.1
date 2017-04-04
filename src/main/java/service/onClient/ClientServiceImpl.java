package service.onClient;

import model.Client;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;
import repository.report.ReportRepository;

import java.util.List;

/**
 * Created by hanna on 26.03.2017.
 */
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ReportRepository reportRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return  clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) throws EntityNotFoundException {
        return clientRepository.findById(id);
    }

    @Override
    public boolean save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public boolean delete(Long id) {
        return clientRepository.delete(id);
    }

    @Override
    public boolean update(Client client) {
        return clientRepository.update(client);
    }

    @Override
    public void removeAll() {
        clientRepository.removeAll();
    }
}
