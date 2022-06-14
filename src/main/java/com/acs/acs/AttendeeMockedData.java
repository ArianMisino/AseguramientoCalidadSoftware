package com.acs.acs;

import java.util.*;

public class AttendeeMockedData {

    private List<Attendee> attendees; //esta sería nuestra "tabla"

    private static AttendeeMockedData instance = null;
    public static AttendeeMockedData getInstance(){
        if (instance == null){
            instance = new AttendeeMockedData();
        }
        return  instance;
    }

    public AttendeeMockedData(){
        attendees = new ArrayList<Attendee>();
        attendees.add(new Attendee(1, "31738285","Mario","Cuevas","2604343704",new GregorianCalendar(1985,10,18),"https://www.clarin.com/img/2020/05/28/W8j5oIwMb_720x0__1.jpg", true));
        attendees.add(new Attendee(2, "35555555", "Jorge", "Perez","2604555555",new GregorianCalendar(1987,9,22),"https://fotos.com/juan", false));
    }

    //return all attendees
    public List<Attendee> fetchAll(){
        return attendees;
    }

    //return attendee by dni
    public Attendee getByDni(String dni){
        for (Attendee a: attendees){
            if(a.getDni().equals(dni)){
                return a;
            }
        }
        return null;
    }

    //create new attendee
    public Attendee create(Attendee attendee){
        attendees.add(attendee);
        return attendee;
    }
    //public Attendee create(int id, String dni, String name, String surname, String phone, Date birthDate, String dniScanUrl){
    //    Attendee attendee = new Attendee(id, dni, name, surname, phone, birthDate, dniScanUrl, false);
    //    attendees.add(attendee);
    //    return attendee;
    //}

    //delete attendee
    public boolean delete(int id){
        int index = -1;
        for (Attendee a: attendees){
            if (a.getId() == id){
                index = attendees.indexOf(a);
                continue;
            }
        }
        if (index > -1){
            attendees.remove(index);
            return true;
        }else{
            return false;
        }

    }

}
