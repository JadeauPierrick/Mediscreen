FROM maven:3.8.5-eclipse-temurin-8 AS build
RUN mkdir -p /assessment
WORKDIR /assessment
COPY pom.xml /assessment
COPY src /assessment/src
RUN mvn -B -f pom.xml clean package -DskipTests
FROM eclipse-temurin:8
COPY --from=build /assessment/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]