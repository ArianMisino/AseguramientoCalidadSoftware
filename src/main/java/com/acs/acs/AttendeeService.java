package com.acs.acs;

import com.acs.acs.exception.EntityNotFoundException;
import com.acs.acs.exception.EntityNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AttendeeService {

    AttendeeMockedData db = AttendeeMockedData.getInstance(); //My fake database

    public List<Attendee> fetchAll(){
        return db.fetchAll();
    }

    public Attendee getByDni(String dni){
        if (!dni.matches("[0-9]")){
            throw new EntityNotValidException("The DNI format is invalid");
        }
        Attendee attendee = db.getByDni(dni);
        if (attendee == null){
            throw  new EntityNotFoundException("The specified person does not exist");
        }
        return attendee;
    }

    //create new attendee
    public Attendee create(Attendee attendee){
        attendee.setApproved(false);
        if (!attendee.getDni().matches("[0-9]")){
            throw new EntityNotValidException("The DNI format is invalid");
        }
        if (db.getByDni(attendee.getDni()) != null){
            throw new EntityNotValidException("Attendee already inscribed");
        }
        return db.create(attendee);
    }

    //delete attendee
    public boolean delete(String dni){
        Attendee attendee = getByDni(dni);
        return db.delete(attendee.getId());
    }

}
