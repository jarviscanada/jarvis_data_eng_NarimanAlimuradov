package ca.jrvs.apps.trading.model.domain;

import java.time.LocalDate;
import java.util.Date;

public class Trader implements Entity<Integer> {

    private int id;
    private String firstname;
    private String lastname;
    private String country;
    private String email;
    private Date date;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }

    public String getFirstname(){
        return this.firstname;
    }

    public void setFirstname(String name){
        this.firstname = name;
    }

    public String getLastname(){
        return this.lastname;
    }

    public void setLastname(String name){
        this.lastname = name;
    }

    public String getCountry(){
        return this.country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
