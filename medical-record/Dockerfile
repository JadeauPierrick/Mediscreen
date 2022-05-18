FROM maven:3.8.5-eclipse-temurin-8 AS build
RUN mkdir -p /medical-record
WORKDIR /medical-record
COPY pom.xml /medical-record
COPY src /medical-record/src
RUN mvn -B -f pom.xml clean package -DskipTests
FROM eclipse-temurin:8
COPY --from=build /medical-record/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]