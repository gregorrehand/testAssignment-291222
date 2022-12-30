FROM gradle:7.6.0-jdk17 as gradleimage
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN gradle build --no-daemon

FROM openjdk:17-alpine
COPY --from=gradleimage /home/gradle/source/build/libs/backend-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app
ENTRYPOINT ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]