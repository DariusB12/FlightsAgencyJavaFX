package repository.DBimplementations;

import model.User;
import repository.interfaces.IUserRepository;

import java.util.Optional;

public class UserRepository implements IUserRepository {
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

    @Override
    public User findByUsername(String username) {
        return null;
    }
}
