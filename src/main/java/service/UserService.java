package service;

import exception.ServiceException;
import exception.ValidationException;
import model.User;
import repository.DBimplementations.UserRepository;
import repository.interfaces.IUserRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public class UserService {
    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signIn(String username, String password) throws ServiceException {
        Optional<User> obj = userRepository.findByUsernameAndPassword(username,password);
        if(obj.isPresent()){
            return obj.get();
        }
        else{
            throw new ServiceException("Invalid username or password\n");
        }
    }

    public void signUp(User user) throws ValidationException, ServiceException {
        Optional<User> obj = userRepository.save(user);
        if(obj.isPresent()){
            throw new ServiceException("Username not unique\n");
        }
    }

    public boolean isUsernameTaken(String username){
        return userRepository.findByUsername(username).isPresent();
    }
}
