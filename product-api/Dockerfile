FROM maven:3.9.11-eclipse-temurin-24-alpine AS build
COPY --chown=maven:maven . .
WORKDIR .
RUN mvn clean install -DskipTests
EXPOSE 8081
CMD ["mvn", "spring-boot:run"]
