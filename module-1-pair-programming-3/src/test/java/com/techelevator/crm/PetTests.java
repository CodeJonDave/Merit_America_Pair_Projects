package com.techelevator.crm;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PetTests {
    @Test
    public void testListVaccinations(){
        Pet pet = new Pet("Dog", "Stupid");
        List<String> vax = new ArrayList<>();
        vax.add("Rabies");
        vax.add("Distemper");
        vax.add("Parvo");
        pet.setVaccinations(vax);
        Assert.assertEquals("List of vaccinations returned is not in the correct format",
                "Rabies, Distemper, Parvo", pet.listVaccinations());
    }

    @Test
    public void testEmptyList(){
        Pet pet = new Pet("Cat", "Dumbass");
        Assert.assertEquals("", pet.listVaccinations());
    }

    @Test
    public void testNullList(){
        Pet pet = new Pet("Turtle", "Slowass");
        pet.setVaccinations(null);
        Assert.assertNull(pet.listVaccinations());
    }
}
