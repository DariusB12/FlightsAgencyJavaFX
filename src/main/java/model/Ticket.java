package model;

import java.util.List;

public class Ticket extends Entity<Integer>{
    private String nameClient;
    private List<String> nameTourists;
    private String addressClient;
    private Integer noSeats;
    private Flight flight;

    public Ticket(String nameClient, List<String> nameTourists, String addressClient, Integer noSeats, Flight flight) {
        this.nameClient = nameClient;
        this.nameTourists = nameTourists;
        this.addressClient = addressClient;
        this.noSeats = noSeats;
        this.flight = flight;
    }

    public String getNameClient() {
        return nameClient;
    }

    public List<String> getNameTourists() {
        return nameTourists;
    }

    public String getAddressClient() {
        return addressClient;
    }

    public Integer getNoSeats() {
        return noSeats;
    }

    public Flight getFlight() {
        return flight;
    }

    @Override
    public String toString() {
        return "model.Ticket{" +
                "nameClient='" + nameClient + '\'' +
                ", nameTourists=" + nameTourists +
                ", addressClient='" + addressClient + '\'' +
                ", noSeats=" + noSeats +
                ", flight=" + flight +
                '}';
    }
}
