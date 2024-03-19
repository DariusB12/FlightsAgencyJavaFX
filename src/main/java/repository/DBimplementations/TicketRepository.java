package repository.DBimplementations;

import exception.ValidationException;
import model.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.JdbcUtils;
import repository.interfaces.ITicketRepository;
import validator.TicketValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

public class TicketRepository implements ITicketRepository {
    private JdbcUtils jdbcUtils;
    private final TicketValidator ticketValidator = new TicketValidator();
    private static final Logger logger = LogManager.getLogger();

    public TicketRepository(Properties properties) {
        logger.info("Initializing TicketRepository with properties: {} ",properties);
        this.jdbcUtils = new JdbcUtils(properties);
    }

    /***
     * pentru save inseram in tickets si in tourists(contine lista cu numele turistilor)
     * si facem update la zboruri daca s-a cumparat biletul
     */
    @Override
    public Optional<Ticket> save(Ticket entity) throws ValidationException {
        logger.trace("saving the ticket {}",entity);
        Connection con = jdbcUtils.getConnection();
        ticketValidator.validate(entity);
        try(
                PreparedStatement preparedStatement1 = con.prepareStatement("INSERT INTO tickets(name_client,address_client,no_seats,id_flight)" +
                        " VALUES (?,?,?,?)");
                PreparedStatement preparedStatement2 = con.prepareStatement("INSERT INTO tourists(tourists_name,id_ticket)" +
                        " VALUES (?,?)");
                PreparedStatement preparedStatement3 = con.prepareStatement("SELECT no_seats FROM flights" +
                        " WHERE id = ?");
                PreparedStatement preparedStatement4 = con.prepareStatement("UPDATE flights" +
                        " SET no_seats = ?" +
                        " WHERE id = ?")
                ) {
            //first validate if the number of seats bought ar lower or equal to the no of seats available
            preparedStatement3.setInt(1,entity.getFlight().getId());
            ResultSet resultSet = preparedStatement3.executeQuery();
            resultSet.next();
            int noSeatsFlight = resultSet.getInt("no_seats");
            if(noSeatsFlight >= entity.getNoSeats()) {
//                System.out.println(noSeatsFlight);
//                System.out.println(entity.getNoSeats());
                preparedStatement1.setString(1, entity.getNameClient());
                preparedStatement1.setString(2, entity.getAddressClient());
                preparedStatement1.setInt(3, entity.getNoSeats());
                preparedStatement1.setInt(4, entity.getFlight().getId());
                preparedStatement1.executeUpdate();
                ResultSet generatedKeys = preparedStatement1.getGeneratedKeys();
                if (generatedKeys.next()) {
                    preparedStatement2.setInt(2, generatedKeys.getInt(1));
                    //insert the tourists names
                    for (String name : entity.getNameTourists()) {
                        preparedStatement2.setString(1, name);
                        preparedStatement2.executeUpdate();
                    }
                    //decrease the no of seats available on the flight
                    preparedStatement4.setInt(1,noSeatsFlight - entity.getNoSeats());
                    preparedStatement4.setInt(2,entity.getFlight().getId());
                    preparedStatement4.executeUpdate();
                }
                logger.traceExit("Tickets bought successfully");
                return Optional.empty();
            }
            logger.traceExit("Too much tickets bought");
            return Optional.of(entity);
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Ticket> findAll() {
        return null;
    }

    @Override
    public Optional<Ticket> find(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Optional<Ticket> delete(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Optional<Ticket> update(Ticket entity) {
        return Optional.empty();
    }
}
