package repository.DBimplementations;

import exception.ValidationException;
import model.User;
import repository.JdbcUtils;
import repository.interfaces.IUserRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uitls.PasswordEncoder;
import validator.IValidator;
import validator.UserValidator;

import java.lang.management.OperatingSystemMXBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;


public class UserRepository implements IUserRepository {
    private JdbcUtils jdbcUtils;
    private static final Logger logger = LogManager.getLogger();
    private IValidator<User> userIValidator;

    public UserRepository(Properties properties) {
        logger.info("Initializing UserRepository with properties: {} ", properties);
        this.jdbcUtils = new JdbcUtils(properties);
        this.userIValidator = new UserValidator();
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        logger.traceEntry("finding user by username and password");
        Connection con = jdbcUtils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * from users" +
                " WHERE username=? AND password = ?")
        ) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, PasswordEncoder.encodePassword(password));

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username1 = resultSet.getString("username");
                String password1 = null;
                User user = new User(username1);
                user.setPassword(password1);
                user.setId(id);

                logger.traceExit(user);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        logger.traceExit("No user found with username and password: {}", username + " " + password);
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        logger.traceEntry("finding user with username {} ", username);
        Connection con = jdbcUtils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * from users" +
                " WHERE username=?")
        ) {
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String password = null;
                User user = new User(username);
                user.setPassword(password);
                user.setId(id);

                logger.traceExit("User found: {}", user);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        logger.traceExit("No user found with username: {}", username);
        return Optional.empty();
    }

    @Override
    public Optional<User> save(User entity) throws ValidationException {
        logger.traceEntry("saving the user {}", entity);
        Connection con = jdbcUtils.getConnection();
        userIValidator.validate(entity);
        Optional<User> obj = this.findByUsername(entity.getUsername());
        if (obj.isPresent()) {
            logger.traceExit("Not unique username: {}", entity);
            return Optional.of(entity);
        }

        try (
                PreparedStatement preparedStatement1 = con.prepareStatement("INSERT INTO users(username,password)" +
                        " VALUES (?,?)");
        ) {
            //save the user with the password encoded
            preparedStatement1.setString(1, entity.getUsername());
            preparedStatement1.setString(2, PasswordEncoder.encodePassword(entity.getPassword()));

            preparedStatement1.executeUpdate();
            logger.traceExit("User saved with succes: {}", entity);
            return Optional.empty();

        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> find(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Optional<User> delete(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(User entity) {
        return Optional.empty();
    }
}
