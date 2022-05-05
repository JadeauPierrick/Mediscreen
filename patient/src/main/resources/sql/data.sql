DROP DATABASE IF EXISTS mediscreen;

CREATE DATABASE mediscreen;

USE mediscreen;

DROP TABLE IF EXISTS patients;
CREATE TABLE patients (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    last_name VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    birthdate VARCHAR(10) NOT NULL,
    sex VARCHAR(1) NOT NULL,
    address VARCHAR(200),
    phone VARCHAR(50)
);

INSERT INTO patients (id, last_name, first_name, birthdate, sex, address, phone)
VALUES
(1, 'Cartier', 'Denis', '1982-05-03', 'M', '2 rue des Fleurs', '111-111-111'),
(2, 'Cartier', 'Rose', '1984-04-01', 'F', '2 rue des Fleurs', '222-222-222'),
(3, 'Cartier', 'Leo', '2004-02-04', 'M', '2 rue des Fleurs', '111-111-111'),
(4, 'Lemesle', 'Christian', '1957-01-07', 'M', '54 Avenue Leclerc', '333-333-333'),
(5, 'Richaud', 'Mathilde', '2000-08-25', 'F', '89 rue du Danemark', '444-444-444');
