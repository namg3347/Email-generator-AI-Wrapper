#Building
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

#Running
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /app/target/EmailGeneratorApi-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/EmailGeneratorApi-0.0.1-SNAPSHOT.jar"]