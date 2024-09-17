package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Reservation;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

public class JdbcReservationDao implements ReservationDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcReservationDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Reservation getReservationById(int id) {
        Reservation retrieved = null;
        String sql = "SELECT reservation_id, site_id, name, from_date, to_date, create_date " +
                "FROM reservation " +
                "WHERE reservation_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                retrieved = mapRowToReservation(results);
            }
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("No reservation was found for #" + id, e);
        } catch (DataAccessException e) {
            throw new DaoException("Access error for reservation #" + id, e);
        } catch (Exception e) {
            throw new DaoException("Unexpected error.", e);
        }
        return retrieved;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        String sql = "INSERT INTO reservation (site_id, name, from_date, to_date, create_date) " +
                "VALUES(?,?,?,?,?) RETURNING reservation_id";
        try {
            int reservationId = jdbcTemplate.queryForObject(sql, int.class, reservation.getSiteId(), reservation.getName(),
                    reservation.getFromDate(), reservation.getToDate(), reservation.getCreateDate());
            return getReservationById(reservationId);
        } catch (NullPointerException e) {
            throw new DaoException("Must have values in correct format for all dates.", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Error creating new reservation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing reservations", e);
        }
    }

    private Reservation mapRowToReservation(SqlRowSet results) {
        Reservation reservation = new Reservation();
        reservation.setReservationId(results.getInt("reservation_id"));
        reservation.setSiteId(results.getInt("site_id"));
        reservation.setName(results.getString("name"));
        reservation.setFromDate(results.getDate("from_date").toLocalDate());
        reservation.setToDate(results.getDate("to_date").toLocalDate());
        reservation.setCreateDate(results.getDate("create_date").toLocalDate());
        return reservation;
    }
}
