package service;

import exception.ServiceException;
import exception.ValidationException;
import model.Ticket;
import repository.interfaces.ITicketRepository;

import java.util.Optional;

public class TicketService {
    private final ITicketRepository ticketRepository;

    public TicketService(ITicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void buyTicket(Ticket ticket) throws ValidationException, ServiceException {
        Optional<Ticket> obj = ticketRepository.save(ticket);
        if(obj.isPresent())
        {
            throw new ServiceException("Too much tickets bought\n");
        }
    }
}
