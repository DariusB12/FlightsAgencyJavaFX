package repository.DBimplementations;

import model.Flight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.JdbcUtils;
import repository.interfaces.IFlightRepository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/***
 * Singurele metode de care avem nevoie sunt
 * toate biletele pentru cautarea dupa destinatie si data
 * toate iletele available care mai au locuri
 */
public class FlightRepository implements IFlightRepository{
    private JdbcUtils jdbcUtils;
    private static final Logger logger = LogManager.getLogger();

    public FlightRepository(Properties properties) {
        logger.info("Initializing FlightRepository with properties: {} ",properties);
        this.jdbcUtils = new JdbcUtils(properties);
    }

    @Override
    public Iterable<Flight> findByDestinationAndDate(String destination, LocalDate departureDate) {

        logger.info("Getting the connection for filterByDestinationAndDate {} {}",destination,departureDate);
        Connection con = jdbcUtils.getConnection();
        try(
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM flights" +
                        " WHERE destination = ? AND date = ?")
                ) {
            List<Flight> allFiltered = new ArrayList<>();

            preparedStatement.setString(1,destination);
            preparedStatement.setString(2, departureDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                LocalDate date = Date.valueOf(resultSet.getString("date")).toLocalDate();
                LocalTime time = Time.valueOf(resultSet.getString("time")).toLocalTime();
                String airport = resultSet.getString("airport");
                int noOfSeats = resultSet.getInt("no_seats");
                Flight flight = new Flight(destination,date,time,airport,noOfSeats);
                flight.setId(id);
                allFiltered.add(flight);
            }

            logger.traceExit(allFiltered);
            return allFiltered;

        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Flight> findAllAvailable() {
        logger.info("Getting the connection for findAllAvailable");
        Connection con = jdbcUtils.getConnection();
        try(
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM flights" +
                        " WHERE no_seats > 0")
        ) {
            List<Flight> allAvailable = new ArrayList<>();

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String destination = resultSet.getString("destination");
                LocalDate date = Date.valueOf(resultSet.getString("date")).toLocalDate();
                LocalTime time = Time.valueOf(resultSet.getString("time")).toLocalTime();
                String airport = resultSet.getString("airport");
                int noOfSeats = resultSet.getInt("no_seats");
                Flight flight = new Flight(destination,date,time,airport,noOfSeats);
                flight.setId(id);
                allAvailable.add(flight);
            }

            logger.traceExit(allAvailable);
            return allAvailable;

        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Flight> save(Flight entity) {
        return Optional.empty();
    }

    @Override
    public Iterable<Flight> findAll() {
        return null;
    }

    @Override
    public Optional<Flight> find(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Optional<Flight> delete(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Optional<Flight> update(Flight entity) {
        return Optional.empty();
    }
}
