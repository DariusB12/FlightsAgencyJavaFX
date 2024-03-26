package uitls.factory;

import service.FlightService;
import service.TicketService;
import service.UserService;

public class Container {
    private final UserService userService;
    private final TicketService ticketService;
    private final FlightService flightService;

    public Container(UserService userService, TicketService ticketService, FlightService flightService) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.flightService = flightService;
    }

    public UserService getUserService() {
        return userService;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public FlightService getFlightService() {
        return flightService;
    }
}
