FROM openjdk:17-alpine
EXPOSE 8080
COPY ./target/producer-0.0.1-SNAPSHOT.jar /tmp/
WORKDIR /tmp
CMD ["java", "-jar", "producer-0.0.1-SNAPSHOT.jar"]