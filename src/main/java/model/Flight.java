package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight extends Entity<Integer>{
    private String destination;
    private LocalDateTime departureDateTime;
    private String airport;
    private Integer seatsNo;

    public Flight(String destination, LocalDateTime departureDateTime, String airport, Integer seatsNo) {
        this.destination = destination;
        this.departureDateTime = departureDateTime;
        this.airport = airport;
        this.seatsNo = seatsNo;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public String getAirport() {
        return airport;
    }

    public Integer getSeatsNo() {
        return seatsNo;
    }

    public void setSeatsNo(Integer seatsNo) {
        this.seatsNo = seatsNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Flight flight = (Flight) o;
        return Objects.equals(destination, flight.destination) && Objects.equals(departureDateTime, flight.departureDateTime) && Objects.equals(airport, flight.airport) && Objects.equals(seatsNo, flight.seatsNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), destination, departureDateTime, airport, seatsNo);
    }

    @Override
    public String toString() {
        return "model.Flight{" +
                "destination='" + destination + '\'' +
                ", departureDateTime=" + departureDateTime +
                ", airport='" + airport + '\'' +
                ", seatsNo=" + seatsNo +
                '}';
    }
}
