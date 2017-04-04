package model.builder;

import model.Client;

/**
 * Created by hanna on 26.03.2017.
 */
public class ClientBuilder {


    private Client client;

    public ClientBuilder() {
        client = new Client();
    }

    public ClientBuilder setFirstName(String firstName) {
        client.setFirstName(firstName);
        return this;
    }

    public ClientBuilder setLastName(String lastName) {
        client.setLastName(lastName);
        return this;
    }

    public ClientBuilder setIdentityCardNr(String identityCardNr) {
        client.setIdentityCardNr(identityCardNr);
        return this;
    }

    public ClientBuilder setPersonalCardNr(String personalCardNr) {
        client.setPersonalCardNr(personalCardNr);
        return this;
    }

    public ClientBuilder setAddress(String address) {
        client.setAddress(address);
        return this;
    }

    public ClientBuilder setId(Long id) {
        client.setId(id);
        return this;
    }

    public Client build() {
        return client;
    }

}
