package service;

import model.Flight;
import repository.DBimplementations.FlightRepository;
import repository.interfaces.IFlightRepository;

import java.time.LocalDate;
import java.util.List;

public class FlightService {
    private final IFlightRepository flightRepository;

    public FlightService(IFlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> findAllAvailable(){
        return (List<Flight>) flightRepository.findAllAvailable();
    }

    public List<Flight> findByDestinationAndDate(String destination, LocalDate departureDate){
        return (List<Flight>) flightRepository.findByDestinationAndDate(destination,departureDate);
    }
}
