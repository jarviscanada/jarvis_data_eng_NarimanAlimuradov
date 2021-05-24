package ca.jrvs.apps.trading.model.domain;

import java.time.LocalDate;
import java.util.Date;

public class Trader implements Entity<Integer> {

    private int id;
    private String first_name;
    private String last_name;
    private String country;
    private String email;
    private Date dob;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }

    public String getFirstName(){
        return this.first_name;
    }

    public void setFirstName(String name){
        this.first_name = name;
    }

    public String getLastName(){
        return this.last_name;
    }

    public void setLastName(String name){
        this.last_name = name;
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

    public Date getDob(){
        return this.dob;
    }

    public void setDob(Date dob){
        this.dob = dob;
    }
}
