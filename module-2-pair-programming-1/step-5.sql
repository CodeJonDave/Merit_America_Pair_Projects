BEGIN;
/*
 STEP ONE: Add a new park with the following data:
  ------------------------------
  name: Ohiopyle State Park
  location: Pennsylvania
  establish date: 1965-01-01
  area: 19052
  visitors: 1000000
  description: Ohiopyle State Park is a Pennsylvania state park on 19,052 acres in Dunbar, Henry Clay and Stewart Townships, Fayette County, Pennsylvania in the United States. The focal point of the park is the more than 14 miles of the Youghiogheny River Gorge that passes through the park.
  ------------------------------
*/
INSERT INTO park(name, location, establish_date, area, visitors, description)
VALUES ('Ohiopyle State Park','Pennsylvania', '1965-01-01', 19052, 1000000,
'Ohiopyle State Park is a Pennsylvania state park on 19,052 acres in Dunbar, Henry Clay and Stewart Townships, Fayette County, Pennsylvania in the United States. The focal point of the park is the more than 14 miles of the Youghiogheny River Gorge that passes through the park.');

/*
  STEP TWO: You just found out that there was an error with the park data. Please update the park visitors to 1.5 million anually.

*/
UPDATE park
SET visitors = 1500000
WHERE name = 'Ohiopyle State Park';

/*
 STEP THREE: Insert new campground with the following data:
  ------------------------------------------------------------
  park_id: (use the id of the new park you just added)
  name: Youghiogheny
  open_from_mm: 01
  open_to_mm: 12
  daily_fee: 95.00
  ------------------------------------------------------------
*/
INSERT INTO campground (park_id, name, open_from_mm, open_to_mm, daily_fee)
VALUES((SELECT park_id
        FROM park
        WHERE name = 'Ohiopyle State Park'), Youghiogheny, 01, 12, 95.00);

/*
 STEP FOUR: Insert 3 new sites with the following data:
 ------------------------------------------------------------
  site_number: 623, campground_id: (use the id of the new campground you just added)
  site_number: 624, campground_id: (use the id of the new campground you just added)
  site_number: 625, campground_id: (use the id of the new campground you just added)
 ------------------------------------------------------------
*/
INSERT INTO site(campground_id, site_number)
VALUES ((SELECT campground_id
         FROM campground
         WHERE name = 'Youghiogheny'), 623),
       ((SELECT campground_id
         FROM campground
         WHERE name = 'Youghiogheny'), 624),
       ((SELECT campground_id
         FROM campground
         WHERE name = 'Youghiogheny'), 625);

/*
 STEP FIVE: Insert 3 reservations, 1 for each site with the following data:
------------------------------------------------------------------------------------
  site_id: (the site_id for site number 623 that you just created), name: Wayne Family, from_date: today + 10 days, to_date: today + 20 days
  site_id: (the site_id for site number 624 that you just created), name: Parker Family, from_date: today + 11 days, to_date: today + 20 days
  site_id: (the site_id for site number 625 that you just created), name: Kent Family, from_date: today + 12 days, to_date: today + 20 days
------------------------------------------------------------------------------------
*/
INSERT INTO reservation (site_id, name, from_date, to_date, create_date)
VALUES
    (
        (SELECT s.site_id
         FROM site s
         JOIN campground c ON s.campground_id = c.campground_id
         JOIN park p ON c.park_id = p.park_id
         WHERE p.name = 'Youghiogheny' AND s.site_number = 623),
        'Wayne Family',
        CURRENT_DATE + INTERVAL '10 days',
        CURRENT_DATE + INTERVAL '20 days',
        CURRENT_DATE
    ),
    (
        (SELECT s.site_id
         FROM site s
         JOIN campground c ON s.campground_id = c.campground_id
         JOIN park p ON c.park_id = p.park_id
         WHERE p.name = 'Youghiogheny' AND s.site_number = 624),
        'Parker Family',
        CURRENT_DATE + INTERVAL '11 days',
        CURRENT_DATE + INTERVAL '20 days',
        CURRENT_DATE
    ),
    (
        (SELECT s.site_id
         FROM site s
         JOIN campground c ON s.campground_id = c.campground_id
         JOIN park p ON c.park_id = p.park_id
         WHERE p.name = 'Youghiogheny' AND s.site_number = 625),
        'Kent Family',
        CURRENT_DATE + INTERVAL '12 days',
        CURRENT_DATE + INTERVAL '20 days',
        CURRENT_DATE
    );
/*
 STEP SIX: The Wayne Family called and asked if they could change their reservation to today. Update the from_date to today and the to_date to a week from today.

 */
 UPDATE reservation
 SET from_date = CURRENT_DATE, to_date = CURRENT_DATE + INTERVAL '7 days'
 WHERE name = 'Wayne Family';


/*
 STEP SEVEN: The Kent family called and they would like to cancel their reservation. Delete the reservation for Kent Family.

*/
DELETE FROM reservation
WHERE name = 'Kent Family';

COMMIT;