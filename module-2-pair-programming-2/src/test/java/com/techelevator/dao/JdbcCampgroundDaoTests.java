package com.techelevator.dao;

import com.techelevator.model.Campground;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JdbcCampgroundDaoTests extends BaseDaoTests {

    private CampgroundDao dao;

    @Before
    public void setup() {
        dao = new JdbcCampgroundDao(dataSource);
    }

    @Test
    public void getCampgroundById_Should_Return_Specific_Campground() {
        Campground campground = dao.getCampgroundById(1);

        assertEquals("Incorrect campground returned for ID 1", 1, campground.getCampgroundId());
    }

    @Test
    public void getCampgroundsByParkId_Should_Return_All_Campgrounds_For_Park() {
        /**
         * test campgrounds
         * 
         * INSERT INTO campground(park_id, name, open_from_mm, open_to_mm, daily_fee)
         * VALUES (1, 'Test Campground 1', 1, 12, 35); -- campground_id will be 1 due to
         * serial
         * 
         * INSERT INTO campground(park_id, name, open_from_mm, open_to_mm, daily_fee)
         * VALUES (1, 'Test Campground 2', 1, 12, 35); -- campground_id will be 2 due to
         * serial
         */

        List<Campground> campgrounds = dao.getCampgroundsByParkId(1);
        assertEquals("Incorrect list size", 2, campgrounds.size());
        assertEquals("Incorrect campground name for index 0", "Test Campground 1", campgrounds.get(0).getName());
        assertEquals("Incorrect campground name for index 1", "Test Campground 2", campgrounds.get(1).getName());


        List<Campground> camps = new ArrayList<>();
        camps.add(dao.getCampgroundById(1));
        camps.add(dao.getCampgroundById(2));

        assertEquals(campgrounds, camps);
    }
}
