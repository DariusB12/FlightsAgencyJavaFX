package repository.interfaces;

import exception.ValidationException;
import model.User;

import java.util.Optional;

public interface IUserRepository extends ICrudRepository<Integer, User> {
    /***
     * Finds a User by its username and password
     * @param username String
     * @param password String
     * @return {
     *     User if found (without the password for security purposes)
     *     optional empty if not found
     * }
     */
    public Optional<User> findByUsernameAndPassword(String username, String password);
    /***
     * Finds a User by its username
     * @param username String
     * @return {
     *     User if found (without the password for security purposes)
     *     optional empty if not found
     * }
     */
    public Optional<User> findByUsername(String username);
}
