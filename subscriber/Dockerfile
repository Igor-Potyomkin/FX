FROM openjdk:17-alpine
EXPOSE 8081
COPY ./target/subscriber-0.0.1-SNAPSHOT.jar /tmp/
WORKDIR /tmp
CMD ["java", "-jar", "subscriber-0.0.1-SNAPSHOT.jar"]