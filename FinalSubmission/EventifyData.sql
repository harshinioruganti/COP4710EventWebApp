-- **UNIVERSITIES** -- 
INSERT INTO universities (university_id, university_name, location, email_domain, num_students) 
VALUES (1, 'University of Example', 'Example City', 'example.edu', 5000);

-- (2) UCF
INSERT INTO universities (university_id, university_name, location, email_domain, num_students) 
VALUES (2, 'University of Central Florida', 'Orlando', 'knights.ucf.edu', 70406);


-- **USERS** --
INSERT INTO users (user_id, username, password, email, user_type, university_id) 
VALUES (1, 'johndoe', 'password123', 'johndoe@example.com', 'superadmin', 1);

-- admin, harshini oruganti
INSERT INTO users (user_id, username, password, email, user_type, university_id) 
VALUES (2, 'harshinioruganti', 'harshi2023', 'harshinioruganti@knights.ucf.edu', 'admin', 2);

-- super admin, mohammad alli
INSERT INTO users (user_id, username, password, email, user_type, university_id) 
VALUES (3, 'mohammadalli', 'alli04', 'mo_alli@knights.ucf.edu', 'superadmin', 2);

-- admin, arnav sing
INSERT INTO users (user_id, username, password, email, user_type, university_id) 
VALUES (4, 'arnav_sing123', 'as098', 'asing@example.edu', 'admin', 1);

-- student, charu sankarganesh
INSERT INTO users (user_id, username, password, email, user_type, university_id) 
VALUES (5, 'charu_s', 'charus345', 'charusankar@example.edu', 'student', 1);

-- student, umran jameel
INSERT INTO users (user_id, username, password, email, user_type, university_id) 
VALUES (6, 'umjam', 'uj482!', 'umran@knights.ucf.edu', 'student', 2);

-- adding super_admin_id to univeristy 2 (UCF)
UPDATE universities
SET super_admin_id = 3
WHERE university_id = 2;


-- **RSOS** --
-- Example Key Club
INSERT INTO rsos (rso_id, rso_name, university_id, admin_id)
VALUES (2, 'Example Key Club', 1, 1);

-- KnightHacks
INSERT INTO rsos (rso_id, rso_name, university_id, admin_id)
VALUES (1, 'KnightHacks', 2, 2);

-- Chess Club
INSERT INTO rsos (rso_id, rso_name, university_id, admin_id)
VALUES (3, 'Chess Club', 2, 4);


-- **EVENTS** --
INSERT INTO events (event_id, event_name, category, description, event_date_and_time, location_name, latitude, longitude, contact_phone, contact_email, event_type, rso_id)
VALUES (1, 'Garden Volunteering', 'rso', 'Come volunteer with us at the campus garden!', TO_DATE('2023-04-18 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Example Garden', 100, 100, 1234567890, 'example@example.edu', 'volunteering', 2);

INSERT INTO events (event_id, event_name, category, description, event_date_and_time, location_name, latitude, longitude, contact_phone, contact_email, event_type)
VALUES (2, 'Ice Cream Social', 'public', 'Free Ice Cream and Mingling!', TO_DATE('2023-04-25 3:15:00', 'YYYY-MM-DD HH24:MI:SS'), 'Toppers Ice Cream', 312.46, 413.84, 1112223333, 'toppers@gmail.com', 'social');


-- **COMMENTS** --
INSERT INTO comments (comment_id, user_id, event_id, comment_text, rating)
VALUES (1, 5, 1, 'I had so much fun planting a new tree today!', 8);

INSERT INTO comments (comment_id, user_id, event_id, comment_text, rating)
VALUES (2, 6, 2, 'It was fun to meet new people and the ice cream was very good!', 9);

commit;
