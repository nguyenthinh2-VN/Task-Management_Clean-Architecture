ARG CACHEBUST=1
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/Task_Management-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]