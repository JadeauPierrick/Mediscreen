# Mediscreen

Mediscreen is an application specializes in detecting risk factors for disease

## Prerequisites

What things you need to run this application

- Your favorite IDE in Java
- Docker Desktop

If you don't have Docker, follow the link :

https://www.docker.com/products/docker-desktop/

## Instructions

1.Make sure docker is installed and running

2.Clone this repository

3.Run `docker-compose up --build -d`

4.Open your browser and enter the following URL : `http://localhost:4200/patients`

5.Stop the application by using `docker-compose down`

## API Documentation

Patient --> http://localhost:8081/swagger-ui/
Note --> http://localhost:8082/swagger-ui/
Assessment --> http://localhost:8080/swagger-ui/
