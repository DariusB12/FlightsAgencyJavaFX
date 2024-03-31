package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Flight extends Entity<Integer>{
    private String destination;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private String airport;
    private Integer seatsNo;

    public Flight(String destination, LocalDate departureDate, LocalTime departureTime, String airport, Integer seatsNo) {
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.airport = airport;
        this.seatsNo = seatsNo;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
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
        return Objects.equals(destination, flight.destination) && Objects.equals(departureDate, flight.departureDate) && Objects.equals(departureTime, flight.departureTime) && Objects.equals(airport, flight.airport) && Objects.equals(seatsNo, flight.seatsNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), destination, departureDate, departureTime, airport, seatsNo);
    }

    @Override
    public String toString() {
        return "destination: " + destination +
                ", departureDate: " + departureDate +
                ", departureTime: " + departureTime +
                ", airport: "  + airport +
                ", seatsNo: " + seatsNo;
    }
}
