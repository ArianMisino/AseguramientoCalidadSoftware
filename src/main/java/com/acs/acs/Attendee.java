package com.acs.acs;

import java.util.Date;
import java.util.GregorianCalendar;

/** Persona que asiste a un evento **/
public class Attendee {
    private int id;
    private String dni;
    private String name;
    private String surname;
    private String phone;
    private GregorianCalendar birthDate;
    private String dniScanUrl;

    private boolean approved;

    public Attendee(int id, String dni, String name, String surname, String phone, GregorianCalendar birthDate, String dniScanUrl, Boolean approved){
        this.setId(id);
        this.setDni(dni);
        this.setName(name);
        this.setSurname(surname);
        this.setPhone(phone);
        this.setBirthDate(birthDate);
        this.setDniScanUrl(dniScanUrl);
        this.setApproved(approved);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public GregorianCalendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(GregorianCalendar birthDate) {
        this.birthDate = birthDate;
    }

    public String getDniScanUrl() {
        return dniScanUrl;
    }

    public void setDniScanUrl(String dniScanUrl) {
        this.dniScanUrl = dniScanUrl;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
