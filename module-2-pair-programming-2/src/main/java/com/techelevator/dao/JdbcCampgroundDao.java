package com.techelevator.dao;

import com.techelevator.model.Campground;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCampgroundDao implements CampgroundDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCampgroundDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Campground getCampgroundById(int id) {
        Campground campground = null;
        String sql = "select * from campground where campground_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);
        if (rowSet.next()){
            campground = mapRowToCampground(rowSet);
        }
        return campground;
    }

    @Override
    public List<Campground> getCampgroundsByParkId(int parkId) {
        List<Campground> campgrounds = new ArrayList<>();
        String sql = "select * from campground where park_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, parkId);
        while (rowSet.next()){
            System.out.println(mapRowToCampground(rowSet).getName());
            campgrounds.add(mapRowToCampground(rowSet));
        }
        return campgrounds;
    }

    private Campground mapRowToCampground(SqlRowSet results) {
        Campground campground = new Campground();
        campground.setCampgroundId(results.getInt("campground_id"));
        campground.setParkId(results.getInt("park_id"));
        campground.setName(results.getString("name"));
        campground.setOpenFromMonth(results.getInt("open_from_mm"));
        campground.setOpenToMonth(results.getInt("open_to_mm"));
        campground.setDailyFee(results.getDouble("daily_fee"));
        return campground;
    }
}
