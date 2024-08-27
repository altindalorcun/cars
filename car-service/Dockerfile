FROM openjdk:17
WORKDIR /app
ADD target/car-service-0.0.1-SNAPSHOT.jar car-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "car-service-0.0.1-SNAPSHOT.jar"]