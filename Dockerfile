FROM openjdk:8-jdk-alpine as builder

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootjar
# Start with a base image containing Java runtime
#FROM java:8
FROM openjdk:8-jre-alpine

# Add Author info
LABEL maintainer="dksxoghl8@gmail.com"

# Add a volume to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=build/libs/spring-first-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
COPY  --from=builder ${JAR_FILE} community-springboot.jar
# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/community-springboot.jar"]

