package com.techelevator;

import java.util.HashMap;
import java.util.Map;

public interface Billable {
<<<<<<< HEAD
    Map<String, Double> services = new HashMap<>();
=======
   // Map<String, Double> services = new HashMap<>();
>>>>>>> 3a2d71573372440658b995bf71de90faaa05fa38
    double getBalanceDue(Map<String, Double> servicesRendered);

}
