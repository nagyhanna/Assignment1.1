package model;

import java.util.Date;

/**
 * Created by hanna on 26.03.2017.
 */
public class Account {

    private Long id;

    private Long client_id;
    private String type;
    private Double amountOfMoney;
    private Date dateOfCreation;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public Long getClient_id() {
        return client_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

public String toString(){
        return " id: "+id +" client_id: "+ client_id +
                " type: " + type + " balance: " + amountOfMoney
                +" date: " + dateOfCreation;
}

}
