package com.techelevator.crm;

<<<<<<< HEAD
=======
import com.techelevator.Billable;
>>>>>>> 3a2d71573372440658b995bf71de90faaa05fa38
import com.techelevator.Person;

import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD

public class Customer extends Person {
    private String phoneNumber;
    private List<Pet> pets =new ArrayList<>();
=======
import java.util.Map;

public class Customer extends Person implements Billable {
    private String phoneNumber;
    private List<Pet> pets = new ArrayList<>();

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public Customer(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName);
        this.phoneNumber = phoneNumber;
    }

    public Customer(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public double getBalanceDue(Map<String, Double> servicesRendered) {
        double subtotal = 0;
        for(Map.Entry<String,Double> e : servicesRendered.entrySet()){
            subtotal += e.getValue();
        }
        return subtotal;
    }
>>>>>>> 3a2d71573372440658b995bf71de90faaa05fa38


}
