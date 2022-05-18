FROM maven:3.8.5-eclipse-temurin-8 AS build
RUN mkdir -p /patient
WORKDIR /patient
COPY pom.xml /patient
COPY src /patient/src
RUN mvn -B -f pom.xml clean package -DskipTests
FROM eclipse-temurin:8
COPY --from=build /patient/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]