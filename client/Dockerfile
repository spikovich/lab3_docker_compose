FROM gradle:8.4-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon

FROM eclipse-temurin:21-jre
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/client.jar /app/client.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "client.jar"]