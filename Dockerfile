
FROM openjdk:17 AS builder
WORKDIR /app
COPY target/search-service-0.0.1-SNAPSHOT.war /app/search-service-0.0.1-SNAPSHOT.war

FROM openjdk:17-slim
WORKDIR /app
COPY --from=builder /app/search-service-0.0.1-SNAPSHOT.war /app/search-service-0.0.1-SNAPSHOT.war
CMD ["java", "-jar", "search-service-0.0.1-SNAPSHOT.war"]