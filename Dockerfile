# Stage 1: Build the application
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
# Ensure mvnw is executable
RUN chmod +x mvnw
# Download dependencies
RUN ./mvnw dependency:go-offline

COPY src ./src
# Build the jar
RUN ./mvnw clean package -DskipTests

# Stage 2: Minimal runtime image
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/Task_Management-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]