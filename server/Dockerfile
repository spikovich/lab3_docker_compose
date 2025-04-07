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
RUN mkdir -p /app/resources && chown -R appuser:appgroup /app
COPY --from=build --chown=appuser:appgroup /home/gradle/src/build/libs/server.jar /app/server.jar
WORKDIR /app
USER appuser
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s --start-period=15s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/ || exit 1

ENTRYPOINT ["java", "-jar", "server.jar"]