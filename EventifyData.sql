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


commit;