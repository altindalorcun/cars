FROM openjdk:17
WORKDIR /app
ADD target/garage-service-0.0.1-SNAPSHOT.jar garage-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "garage-service-0.0.1-SNAPSHOT.jar"]