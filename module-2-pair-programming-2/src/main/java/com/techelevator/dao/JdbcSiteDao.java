package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Site;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcSiteDao implements SiteDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcSiteDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Site> getSitesWithRVAccessByParkId(int parkId) {
        List<Site> rvAccessible = new ArrayList<>();
        String sql = "SELECT site_id, campground_id, site_number, " +
                "max_occupancy, accessible, max_rv_length, utilities " +
                "FROM site " +
                "JOIN campground USING(campground_id) " +
                "JOIN park USING(park_id) " +
                "WHERE park_id = ? AND max_rv_length > 0 ";
        try {
            SqlRowSet retrieved = jdbcTemplate.queryForRowSet(sql, parkId);
            while (retrieved.next()) {
                rvAccessible.add(mapRowToSite(retrieved));
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing site data", e);
        }
        return rvAccessible;
    }

    @Override
    public List<Site> getSitesAvailableTodayByParkId(int parkId) {
        List<Site> sitesAvailableToday = new ArrayList<>();
        String sql = "SELECT s.site_id, s.campground_id, s.site_number, s.max_occupancy, s.accessible, s.max_rv_length, s.utilities " +
                "FROM site s " +
                "JOIN campground c ON s.campground_id = c.campground_id " +
                "JOIN park p ON c.park_id = p.park_id " +
                "LEFT JOIN reservation r ON s.site_id = r.site_id " +
                "AND r.from_date <= CURRENT_DATE " +
                "AND r.to_date >= CURRENT_DATE " +
                "WHERE p.park_id = ? " +
                "AND r.site_id IS NULL";
        try {
            SqlRowSet retrieved = jdbcTemplate.queryForRowSet(sql, parkId);
            while (retrieved.next()) {
                sitesAvailableToday.add(mapRowToSite(retrieved));
            }
        } catch (DataAccessException e) {
            throw new DaoException("Trouble accessing data", e);
        }
        return sitesAvailableToday;
    }

    private Site mapRowToSite(SqlRowSet results) {
        Site site = new Site();
        site.setSiteId(results.getInt("site_id"));
        site.setCampgroundId(results.getInt("campground_id"));
        site.setSiteNumber(results.getInt("site_number"));
        site.setMaxOccupancy(results.getInt("max_occupancy"));
        site.setAccessible(results.getBoolean("accessible"));
        site.setMaxRvLength(results.getInt("max_rv_length"));
        site.setUtilities(results.getBoolean("utilities"));
        return site;
    }
}
