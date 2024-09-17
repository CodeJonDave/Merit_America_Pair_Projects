package com.techelevator.dao;

import com.techelevator.model.Park;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;

public class JdbcParkDaoTests extends BaseDaoTests {

    private ParkDao dao;

    @Before
    public void setup() {
        dao = new JdbcParkDao(dataSource);
    }

    @Test
    public void getParks_Should_Return_All_Parks() {
        List<Park> parkList = dao.getParks();

        /**
         * test parks
         * 
         * INSERT INTO park (name, location, establish_date, area, visitors,
         * description)
         * VALUES ('Park 1', 'Pennsylvania', '1/1/1970', 1024, 512, 'Test description
         * 1'); -- park_id will be 1 due to serial
         * 
         * INSERT INTO park (name, location, establish_date, area, visitors,
         * description)
         * VALUES ('Park 2', 'Ohio', '1/1/1970', 2048, 1024, 'Test description 2'); --
         * park_id will be 2 due to serial
         **/

        assertEquals("Incorrect list size", 2, parkList.size());
        assertEquals("Incorrect name for park at index 0","Park 1", parkList.get(0).getName());
        assertEquals("Incorrect name for park at index 1", "Park 2", parkList.get(1).getName());
    }

}
