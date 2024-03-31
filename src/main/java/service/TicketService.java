package service;

import exception.ServiceException;
import exception.ValidationException;
import model.Ticket;
import repository.interfaces.ITicketRepository;
import uitls.observer.Observable;
import uitls.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketService implements Observable {
    private final ITicketRepository ticketRepository;
    private List<Observer> allObservers;

    public TicketService(ITicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
        this.allObservers = new ArrayList<>();
    }

    public void buyTicket(Ticket ticket) throws ValidationException, ServiceException {
        Optional<Ticket> obj = ticketRepository.save(ticket);
        if(obj.isPresent())
        {
            throw new ServiceException("Too much tickets bought\n");
        }
        notifyAllObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        allObservers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        allObservers.remove(o);
    }

    @Override
    public void notifyAllObservers() {
        allObservers.forEach(Observer::update);
    }
}
