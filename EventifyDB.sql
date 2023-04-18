-- Create the table for users
CREATE TABLE users (
    user_id NUMBER PRIMARY KEY,
    username VARCHAR2(50) NOT NULL UNIQUE,
    password VARCHAR2(50) NOT NULL,
    email VARCHAR2(50) NOT NULL,
    user_type VARCHAR2(20) NOT NULL, --super admin, admin, student
    -- university_id NUMBER
);

-- Create the table for universities
CREATE TABLE universities (
    university_id NUMBER PRIMARY KEY,
    university_name VARCHAR2(100) NOT NULL,
    location VARCHAR2(100) NOT NULL,
    email_domain VARCHAR2(50) NOT NULL,
    num_students NUMBER NOT NULL,
    super_admin_id NUMBER REFERENCES users(user_id) -- which super_admin created the rso
);

ALTER TABLE users
ADD CONSTRAINT check_user_level CHECK (user_type IN ('admin', 'superadmin', 'student')) --user_type has to be a student, admin, or super_admin

ALTER TABLE users
ADD CONSTRAINT fk_university_id FOREIGN KEY (university_id) REFERENCES universities(university_id);

-- Create the table for RSOs
CREATE TABLE rsos (
    rso_id NUMBER PRIMARY KEY UNIQUE,
    rso_name VARCHAR2(100) NOT NULL,
    university_id NUMBER REFERENCES universities(university_id),
    admin_id NUMBER REFERENCES users(user_id) -- which admin created the rso
);

-- Create the table for events
CREATE TABLE events (
    event_id NUMBER PRIMARY KEY,
    event_name VARCHAR2(100) NOT NULL,
    category VARCHAR2(50) NOT NULL, -- public,  private, rso
    description VARCHAR2(500) NOT NULL,
    event_date_and_time TIMESTAMP NOT NULL,
    location_name VARCHAR2(100) NOT NULL,
    latitude NUMBER(10,6) NOT NULL,
    longitude NUMBER(10,6) NOT NULL,
    contact_phone VARCHAR2(20) NOT NULL,
    contact_email VARCHAR2(100) NOT NULL,
    event_type VARCHAR2(20) NOT NULL, -- social, fundraising, tech talks, etc.
    approval_status CHAR(1) DEFAULT 'Y' CHECK (approval_status IN ('Y', 'N')),
    rso_id NUMBER
);

ALTER TABLE events
ADD CONSTRAINT event_category_level CHECK (category IN ('public', 'private', 'rso')) --category has to be public, private, rso

ALTER TABLE events
ADD CONSTRAINT fk_rso_id FOREIGN KEY (rso_id) REFERENCES rsos(rso_id);

-- Create the table for comments
CREATE TABLE comments (
    comment_id NUMBER PRIMARY KEY,
    user_id NUMBER REFERENCES users(user_id),
    event_id NUMBER REFERENCES events(event_id),
    comment_text VARCHAR2(500) NOT NULL,
    rating NUMBER(1) DEFAULT 0
);

DROP TABLE comments;

-- Create the table for event attendees
CREATE TABLE event_attendees (
user_id NUMBER REFERENCES users(user_id),
event_id NUMBER REFERENCES events(event_id),
PRIMARY KEY(user_id, event_id)
);

DROP TABLE event_attendees;

-- Create the table for university images
CREATE TABLE university_images (
  university_id NUMBER PRIMARY KEY,
  picture BLOB NOT NULL,
  CONSTRAINT fk_university_images FOREIGN KEY (university_id) REFERENCES universities(university_id)
);

INSERT INTO users (user_id, username, password, email, user_type, university_id) 
VALUES (1, 'johndoe', 'password123', 'johndoe@example.com', 'superadmin', 1);

INSERT INTO universities (university_id, university_name, location, email_domain, num_students) 
VALUES (1, 'University of Example', 'Example City', 'example.edu', 5000);

