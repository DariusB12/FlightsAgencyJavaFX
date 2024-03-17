package repository.interfaces;

import model.Flight;

import java.time.LocalDate;

public interface IFlightRepository extends ICrudRepository<Integer,Flight>{
    /***
     * Return the flights found that have the destination and departureDate given as parameters
     * @param destination String
     * @param departureDate LocalDate
     * @return Iterable with Flights
     */
    public Iterable<Flight> findByDestinationAndDate(String destination, LocalDate departureDate);
}
