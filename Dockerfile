FROM amazoncorretto:11-alpine-jdk
MAINTAINER AMT
COPY target/amt-0.0.1-SNAPSHOT.jar amt-app.jar
ENTRYPOINT ["java", "-jar", "/amt-app.jar"]
