package service;

import model.Flight;
import repository.interfaces.IFlightRepository;

import java.time.LocalDate;
import java.util.List;

public class FlightService {
    private final IFlightRepository flightRepository;

    public FlightService(IFlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> findAll(){
        return (List<Flight>) flightRepository.findAll();
    }

    public List<Flight> findByDestinationAndDate(String destination, LocalDate departureDate){
        return (List<Flight>) flightRepository.findByDestinationAndDate(destination,departureDate);
    }
}
