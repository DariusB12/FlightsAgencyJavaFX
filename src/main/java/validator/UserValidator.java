package validator;

import exception.ValidationException;
import model.User;

import java.util.Objects;

public class UserValidator implements IValidator<User> {
    @Override
    public void validate(User entity) throws ValidationException {
        String errors = "";
        if (Objects.equals(entity.getUsername(), "")) {
            errors += "invalid username\n";
        }
        if (Objects.equals(entity.getPassword(), "")) {
            errors += "invalid password\n";
        }
        if (errors != "") {
            throw new ValidationException(errors);
        }
    }
}
