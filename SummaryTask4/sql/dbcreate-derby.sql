--==============================================================
-- ST4Example DB creation script for Apache Derby
--==============================================================

CONNECT 'jdbc:derby://localhost:1527/st4db;create=true;user=test;password=test';

DROP TABLE requests;
DROP TABLE request_statuses;
DROP TABLE users;
DROP TABLE roles;
DROP TABLE crews;
DROP TABLE employees;
DROP TABLE jobs;
DROP TABLE flights;
DROP TABLE statuses;

----------------------------------------------------------------
-- ROLES
----------------------------------------------------------------
CREATE TABLE roles(
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'dispatcher');

----------------------------------------------------------------
-- USERS
----------------------------------------------------------------
CREATE TABLE users(
	id INTEGER NOT NULL generated always AS identity PRIMARY KEY,
	login VARCHAR(10) NOT NULL UNIQUE,
	password VARCHAR(15) NOT NULL,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	role_id INTEGER REFERENCES roles(id) 
		ON DELETE CASCADE 
		ON UPDATE RESTRICT
);

INSERT INTO users VALUES(DEFAULT, 'admin', 'admin', 'Беня', 'Крик', 0);
INSERT INTO users VALUES(DEFAULT, 'client1', 'client', 'Petr', 'Perviy', 1);
INSERT INTO users VALUES(DEFAULT, 'client2', 'client', 'Петр', 'Второй', 1);

----------------------------------------------------------------
-- JOBS
----------------------------------------------------------------
CREATE TABLE jobs(
	id INTEGER NOT NULL PRIMARY KEY,
	job_name VARCHAR(15) NOT NULL
);

INSERT INTO jobs VALUES(0, 'pilot');
INSERT INTO jobs VALUES(1, 'navigator');
INSERT INTO jobs VALUES(2, 'operator');
INSERT INTO jobs VALUES(3, 'stewardess');

----------------------------------------------------------------
-- EMPLOYES
----------------------------------------------------------------

CREATE TABLE employees(
	id INTEGER NOT NULL generated always AS identity PRIMARY KEY,
	first_name VARCHAR(15) NOT NULL,
	last_name VARCHAR(15) NOT NULL,
	job_id INT REFERENCES jobs(id),
	avalible BOOLEAN DEFAULT true,
	email VARCHAR(30)
);

INSERT INTO employees VALUES(DEFAULT, 'Phil', 'Anselmo', 0, DEFAULT, 'Judge73@yandex.ru');
INSERT INTO employees VALUES(DEFAULT, 'Rob', 'Halford', 0, DEFAULT, 'Judge73@yandex.ru');
INSERT INTO employees VALUES(DEFAULT, 'Ron', 'Dio', 0, DEFAULT, 'Judge73@yandex.ru');
INSERT INTO employees VALUES(DEFAULT, 'Darril', 'Dimerdag', 1, DEFAULT, 'Judge73@yandex.ru');
INSERT INTO employees VALUES(DEFAULT, 'Glenn', 'Tipton', 1, DEFAULT, 'Judge73@yandex.ru');
INSERT INTO employees VALUES(DEFAULT, 'Kenneth', 'Downing', 1, DEFAULT, 'Judge73@yandex.ru');
INSERT INTO employees VALUES(DEFAULT, 'James', 'Hatfield', 2, DEFAULT, 'Judge73@yandex.ru');
INSERT INTO employees VALUES(DEFAULT, 'David', 'Mustain', 2, DEFAULT, 'Judge73@yandex.ru');
INSERT INTO employees VALUES(DEFAULT, 'Tomas', 'Araya', 2, DEFAULT, 'Judge73@yandex.ru');
INSERT INTO employees VALUES(DEFAULT, 'Charlotte', 'Bronte', 3, DEFAULT, 'Judge73@yandex.ru');
INSERT INTO employees VALUES(DEFAULT, 'Emily', 'Bronte', 3, DEFAULT, 'Judge73@yandex.ru');
INSERT INTO employees VALUES(DEFAULT, 'Anne', 'Bronte', 3, DEFAULT, 'Judge73@yandex.ru');

----------------------------------------------------------------
-- STATUSES
----------------------------------------------------------------
CREATE TABLE statuses(
	id INTEGER NOT NULL PRIMARY KEY,
	status_name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO statuses VALUES(0, 'delayed');
INSERT INTO statuses VALUES(1, 'arrived');
INSERT INTO statuses VALUES(2, 'en route');
INSERT INTO statuses VALUES(3, 'scheduled');
INSERT INTO statuses VALUES(4, 'canceled');

----------------------------------------------------------------
-- FLIGHTS
----------------------------------------------------------------
CREATE TABLE flights(
	id INTEGER NOT NULL generated always AS identity PRIMARY KEY,
	origin VARCHAR(20) NOT NULL,
	destination VARCHAR(20) NOT NULL,
	departure_date TIMESTAMP NOT NULL,
	status_id INTEGER REFERENCES statuses(id)
);

INSERT INTO flights VALUES(DEFAULT, 'Hong-Kong', 'Moskva', '2013-2-5 15:42:00', 3);
INSERT INTO flights VALUES(DEFAULT, 'Kharkov', 'Magadan', '2011-5-5 10:32:00', 3);
INSERT INTO flights VALUES(DEFAULT, 'Купянск', 'Ковшаровка', '2011-5-7 12:09:00', 3);
INSERT INTO flights VALUES(DEFAULT, 'Загрызове', 'Кондрашовка', '2013-2-11 13:05:00', 2);
INSERT INTO flights VALUES(DEFAULT, 'Земля', 'Марс', '2020-5-7 3:09:00', 1);
INSERT INTO flights VALUES(DEFAULT, 'Брешия', 'Брешия', '2014-3-7 22:12:00', 2);

----------------------------------------------------------------
-- FLIGHT CREW
----------------------------------------------------------------

CREATE TABLE crews(
	flight_id INT REFERENCES flights(id) ON DELETE CASCADE,
	pilot_id INT REFERENCES employees(id),
	navigator_id INT REFERENCES employees(id),
	operator_id INT REFERENCES employees(id),
	stewardess_id INT REFERENCES employees(id)
);

----------------------------------------------------------------
-- TRIGGERS
----------------------------------------------------------------

CREATE TRIGGER free_employees_del AFTER	DELETE ON crews
REFERENCING OLD AS EXISTING FOR EACH ROW MODE DB2SQL
    UPDATE employees SET avalible = true
    WHERE id IN (EXISTING.pilot_id ,EXISTING.navigator_id ,EXISTING.operator_id 
    , EXISTING.stewardess_id);
    
CREATE TRIGGER free_employees_upd AFTER	UPDATE ON crews
REFERENCING OLD AS EXISTING FOR EACH ROW MODE DB2SQL
    UPDATE employees SET avalible = true
    WHERE id IN (EXISTING.pilot_id ,EXISTING.navigator_id ,EXISTING.operator_id ,
    EXISTING.stewardess_id);

CREATE TRIGGER employees_busy_update AFTER	UPDATE ON crews
REFERENCING NEW AS UPDATED FOR EACH ROW MODE DB2SQL
    UPDATE employees SET avalible = false
    WHERE id IN (UPDATED.pilot_id ,UPDATED.navigator_id ,UPDATED.operator_id ,
    UPDATED.stewardess_id);

CREATE TRIGGER employees_busy_insert AFTER	INSERT ON crews
REFERENCING NEW AS UPDATED FOR EACH ROW MODE DB2SQL
    UPDATE employees SET avalible = false
    WHERE id IN (UPDATED.pilot_id ,UPDATED.navigator_id ,UPDATED.operator_id ,
    UPDATED.stewardess_id);
    
----------------------------------------------------------------
-- REQUESTS STATUSES
----------------------------------------------------------------

CREATE TABLE request_statuses(
	id INTEGER NOT NULL generated always AS identity PRIMARY KEY,
	status_name VARCHAR(10) NOT NULL
);

INSERT INTO request_statuses VALUES(DEFAULT, 'new');
INSERT INTO request_statuses VALUES(DEFAULT, 'approved');
INSERT INTO request_statuses VALUES(DEFAULT, 'declined');
----------------------------------------------------------------
-- REQUESTS
----------------------------------------------------------------

CREATE TABLE requests(
	id INTEGER NOT NULL generated always AS identity PRIMARY KEY,
	user_id INTEGER REFERENCES users(id),
	header VARCHAR(20) NOT NULL,
	description VARCHAR(200) NOT NULL,
	status_id INTEGER REFERENCES request_statuses(id)
);
----------------------------------------------------------------
-- test database:
----------------------------------------------------------------
SELECT * FROM flights;
SELECT * FROM crews;
SELECT * FROM statuses;
SELECT * FROM jobs;
SELECT * FROM requests;
SELECT * FROM users;
SELECT * FROM roles;
SELECT * FROM employees;

DISCONNECT;