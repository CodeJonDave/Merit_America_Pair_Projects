package com.techelevator.dao;

import com.techelevator.model.Reservation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class JdbcReservationDaoTests extends BaseDaoTests {

    private ReservationDao dao;

    @Before
    public void setup() {
        dao = new JdbcReservationDao(dataSource);
    }

    @Test
    public void getReservationById_Should_Return_Specific_Reservation() {
        Reservation testReservation = dao.getReservationById(10);
        Assert.assertNotNull("Failed to retrieve a reservation", testReservation);
        Assert.assertEquals("Retrieved incorrect reservation", "Leonard Leonardson", testReservation.getName());
    }

    @Test
    public void createReservation_Should_Return_Reservation_With_New_Id() {
        Reservation reservation = new Reservation(
                0,
                1,
                "TEST NAME",
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(3),
                LocalDate.now());

        Reservation reservationCreated = dao.createReservation(reservation);

        assertEquals("Incorrect ID of new reservation", 13, reservationCreated.getReservationId());

    }

}
