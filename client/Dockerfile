FROM gradle:8.4-jdk17-alpine AS build
WORKDIR /home/gradle/src
COPY --chown=gradle:gradle build.gradle.kts settings.gradle.kts ./
COPY --chown=gradle:gradle gradle ./gradle
COPY --chown=gradle:gradle gradlew* ./
RUN if [ -f gradlew ]; then chmod +x gradlew && ./gradlew dependencies --no-daemon; else gradle dependencies --no-daemon; fi
COPY --chown=gradle:gradle . .
RUN gradle shadowJar --no-daemon

FROM eclipse-temurin:21-jre-alpine
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
RUN mkdir -p /app && chown -R appuser:appgroup /app
COPY --from=build --chown=appuser:appgroup /home/gradle/src/build/libs/client.jar /app/client.jar
WORKDIR /app

USER appuser

ENTRYPOINT ["java", "-jar", "client.jar"]