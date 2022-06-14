package com.acs.acs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class AttendeeController {

    AttendeeService srv = new AttendeeService();
    @GetMapping("/attendees")
    public List<Attendee> index() {
        return srv.fetchAll();
    }

    @GetMapping("/attendees/{dni}")
    public Attendee get(@PathVariable String dni){
        return srv.getByDni(dni);
    }

    @PostMapping("/attendees")
    public ResponseEntity<Attendee> create(@RequestBody Attendee attendee){
        var newAttendee = srv.create(attendee);
        return new ResponseEntity<Attendee>(newAttendee, HttpStatus.CREATED);
    }

    @DeleteMapping("/attendees/{dni}")
    public void delete(@PathVariable String dni){
        var result = srv.delete(dni);
    }

}
