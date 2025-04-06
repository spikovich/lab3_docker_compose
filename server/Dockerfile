FROM gradle:8.4-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon

FROM eclipse-temurin:21-jre
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/server.jar /app/server.jar
COPY --from=build /home/gradle/src/src/main/resources/ /app/resources/
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "server.jar"]