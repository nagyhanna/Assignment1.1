package repository.client;

import database.DBConnectionFactory;
import model.Client;
import model.builder.ClientBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * Created by hanna on 28.03.2017.
 */
public class ClientRespositoryMYSQLImplTest {

    private static ClientRepository clientRepository;


    @BeforeClass
    public static void setupClass() {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(false).getConnection();
        clientRepository = new ClientRespositoryMYSQLImpl(connection);

    }


    @Test
    public void findAll() throws Exception {

        assertNotEquals(clientRepository.findAll().size(),0);
    }

    @Test
    public void findById() throws Exception {

        assertNotNull(clientRepository.findById(1L));
    }

    @Test
    public void save() throws Exception {
        clientRepository.removeAll();
        assertTrue(clientRepository.save(new ClientBuilder().setLastName("Test").setFirstName("Test")
        .setAddress("cluj").setIdentityCardNr("23234456").setPersonalCardNr("54657").build()));
    }

    @Test
    public void delete() throws Exception {

        assertTrue(clientRepository.delete(2L));
    }

    @Test
    public void update() throws Exception {

        Client client = clientRepository.findById(1L);
        client.setAddress("New Address");
        assertTrue(clientRepository.update(client));


    }

    @Test
    public void removeAll() throws Exception {

        clientRepository.removeAll();
        assertEquals(clientRepository.findAll().size(),0);
    }

}