DROP DATABASE IF EXISTS mediscreen;

CREATE DATABASE mediscreen;

USE mediscreen;

DROP TABLE IF EXISTS patients;
CREATE TABLE patients (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    last_name VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    birthdate VARCHAR(10) NOT NULL,
    sex VARCHAR(1) NOT NULL,
    address VARCHAR(200),
    phone VARCHAR(50)
);

