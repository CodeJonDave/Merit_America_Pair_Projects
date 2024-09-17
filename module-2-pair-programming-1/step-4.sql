-- select the park name, campground name, open_from_mm, open_to_mm & daily_fee ordered by park name and then campground name
-- (expected: 7 rows, starting with "Blackwoods")
SELECT p.name, c.name, open_from_mm, open_to_mm, daily_fee
FROM campground c
JOIN park p using(park_id);


-- select the park name and the total number of campgrounds for each park ordered by park name
-- (expected: 3 rows, starting with "Acadia")
SELECT p.name, COUNT(campground_id) AS num_of_campgrounds
FROM park p
JOIN campground USING(park_id)
GROUP BY p.name
ORDER BY p.name;


-- select the park name, campground name, site number, max occupancy, accessible, max rv length, utilities where the campground name is 'Blackwoods'
-- (expected: 12 rows)
SELECT p.name, c.name, site_number, max_occupancy, accessible, max_rv_length, utilities
FROM site
JOIN campground c USING(campground_id)
JOIN park p USING(park_id)
WHERE c.name = 'Blackwoods';

-- select site number, reservation name, reservation from and to date ordered by reservation from date
-- (expected: 44 rows, starting with the earliest date)
SELECT site_number, r.name, from_date, to_date
FROM reservation r
JOIN site USING(site_id)
ORDER BY from_date;
