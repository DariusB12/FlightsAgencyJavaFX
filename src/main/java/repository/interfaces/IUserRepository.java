package repository.interfaces;

import model.User;

public interface IUserRepository extends ICrudRepository<Integer, User> {
    /***
     * Finds a User by its username
     * @param username String
     * @return {
     *     User if found
     *     optional empty if not found
     * }
     */
    public User findByUsername(String username);
}
