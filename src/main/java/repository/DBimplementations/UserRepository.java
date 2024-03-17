package repository.DBimplementations;

import model.User;
import repository.JdbcUtils;
import repository.interfaces.IUserRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;


public class UserRepository implements IUserRepository {
    private JdbcUtils jdbcUtils;
    private static final Logger logger = LogManager.getLogger();

    public UserRepository(Properties properties) {
        logger.info("Initializing UserRepository with properties: {} ",properties);
        this.jdbcUtils = new JdbcUtils(properties);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        logger.traceEntry("finding user with username {} ",username);
        Connection con = jdbcUtils.getConnection();

        try(PreparedStatement preparedStatement = con.prepareStatement("SELECT * from users" +
                " WHERE username=? AND password = ?")
        ){
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
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
        logger.traceExit("No user found with username and password: {}", username + " " +password);
        return Optional.empty();
    }

    @Override
    public Optional<User> save(User entity) {
        return Optional.empty();
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
