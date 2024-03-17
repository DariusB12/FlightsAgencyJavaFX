import exception.ValidationException;
import model.Flight;
import model.Ticket;
import repository.DBimplementations.FlightRepository;
import repository.DBimplementations.TicketRepository;


import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class FlightMain {
    public static void main(String[] args)  {
        System.out.println("Hello and welcome!");
        Properties properties = new Properties();

        try {
            properties.load(new FileReader("bd.config"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        JdbcUtils jdbcUtils = new JdbcUtils(properties);
//        Connection con = jdbcUtils.getConnection();
//        UserRepository userRepository = new UserRepository(properties);
//        userRepository.findByUsername("mihnea","1234");
        FlightRepository flightRepository = new FlightRepository(properties);
//        System.out.println(flightRepository.findByDestinationAndDate("Bucuresti", Date.valueOf("2025-05-17").toLocalDate()));
        System.out.println(flightRepository.findAllAvailable());

        Flight flight =  new Flight("fdgfdg",null,null,null,6);
        flight.setId(1);
        TicketRepository ticketRepository = new TicketRepository(properties);
        Ticket ticket = new Ticket("darius", Arrays.asList("elena","gheorghe","badea"),"Baia Mare",4,flight);
        try {
            ticketRepository.save(ticket);
        } catch (ValidationException e) {
            System.out.println(e);
        }


    }
}