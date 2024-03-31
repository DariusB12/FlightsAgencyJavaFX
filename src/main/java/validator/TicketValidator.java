package validator;

import exception.ValidationException;
import model.Ticket;

public class TicketValidator implements IValidator<Ticket>{

    @Override
    public void validate(Ticket entity) throws ValidationException {
        String errors ="";
        if(entity.getNameClient() == ""){
            errors+="invalid client name\n";
        }
        if(entity.getNoSeats() == 0){
            errors += "can't buy 0 tickets\n";
        }
        if(entity.getAddressClient() == ""){
            errors+="invalid address\n";
        }
        if(entity.getFlight().getSeatsNo() < entity.getNoSeats()){
            errors += "can't buy more tickets than available in the flight\n";
        }
        if(entity.getNoSeats() != entity.getNameTourists().size()+1){
            errors += "noOfSeats doesn't correspond with the number of people\n";
        }
        if(errors != ""){
            throw new ValidationException(errors);
        }
    }
}
