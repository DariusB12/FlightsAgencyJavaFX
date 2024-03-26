package uitls.factory;

import repository.DBimplementations.FlightRepository;
import repository.DBimplementations.TicketRepository;
import repository.DBimplementations.UserRepository;
import repository.interfaces.IFlightRepository;
import repository.interfaces.ITicketRepository;
import repository.interfaces.IUserRepository;
import service.FlightService;
import service.TicketService;
import service.UserService;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Factory {
    private static Container container = null;
    private Factory(){}

    public static Container getContainer(){
        if(container == null){
            Properties properties = new Properties();
            try {
                properties.load(new FileReader("bd.config"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            IUserRepository userRepository = new UserRepository(properties);
            IFlightRepository flightRepository = new FlightRepository(properties);
            ITicketRepository ticketRepository = new TicketRepository(properties);

            UserService userService = new UserService(userRepository);
            FlightService flightService = new FlightService(flightRepository);
            TicketService ticketService = new TicketService(ticketRepository);

            container = new Container(userService,ticketService,flightService);
            return container;
        }
        return container;
    }
}
