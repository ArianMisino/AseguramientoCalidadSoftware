package com.acs.acs;

import com.acs.acs.exception.EntityNotValidException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import javax.swing.text.html.parser.Entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.GregorianCalendar;

public class AttendeeServiceTests {
    private AttendeeMockedData db = AttendeeMockedData.getInstance();
    private AttendeeService srv = new AttendeeService();
    private Attendee attendee;

    @BeforeEach
    public void setup(){
        attendee = new Attendee(
                0,
                "40183347",
                "pablo",
                "fracaro",
                "2604061571",
                new GregorianCalendar(1997,07,03),
                "https://photos.url",
                true);
    }
    // givenXXXX_whenAAA_thenRRRRR nomenclatura
    @DisplayName("Test de creacion exitosa de Attendee")
    @Test
    public void givenAttendeeObject_whenCreateAttendee_thenReturnAttendeeObject(){

        //when
        Attendee createAttendee = srv.create(attendee);

        //then
        assertThat(createAttendee).isNotNull();
        assertThat(createAttendee.isApproved()).isFalse();
        assertThat(db.fetchAll().size() == 3);

    }
    @Test
    public void givenAttendeeObjectWithCorruptedDNI_whenCreateAttendee_thenThrowException(){

        //given
        attendee.setDni("aa");

        //when / then
        assertThrows(EntityNotValidException.class, () -> srv.create(attendee));
        assertThat(db.fetchAll().size() == 2);



    }


}
