package com.techelevator.crm;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CustomerTests {


    private Map<String, Double> services = new HashMap<>();

    @Test
    public void testGetBalanceDue(){
        Customer customer = new Customer("Billy", "Bob", "911");
        services.put("Walking", 10.0);
        System.out.println(customer.getBalanceDue(services));
    }
}
