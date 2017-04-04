package model;

/**
 * Created by hanna on 26.03.2017.
 */
public class Client {

    private Long id;
    private String firstName;
    private String lastName;
    private String identityCardNr;
    private String personalCardNr;
    private String address;



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getIdentityCardNr() {
        return identityCardNr;
    }

    public void setIdentityCardNr(String identityCardNr) {
        this.identityCardNr = identityCardNr;
    }

    public String getPersonalCardNr() {
        return personalCardNr;
    }

    public void setPersonalCardNr(String personalCardNr) {
        this.personalCardNr = personalCardNr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String toString(){
        return id+"  "+firstName+"  "+lastName+"  "+identityCardNr+"  "+personalCardNr+address+"  ";
    }
}
