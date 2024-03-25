package validator;

import exception.ValidationException;
import model.User;

public class UserValidator implements IValidator<User> {
    @Override
    public void validate(User entity) throws ValidationException {
        String errors = "";
        if (entity.getUsername() == "") {
            errors += "invalid username\n";
        }
        if (entity.getPassword() == "") {
            errors += "invalid password\n";
        }
        if (errors != "") {
            throw new ValidationException(errors);
        }
    }
}
