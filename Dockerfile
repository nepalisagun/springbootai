# Use Gradle image for building
FROM gradle:8.5-jdk17 AS builder

# Set working directory
WORKDIR /app

# Copy Gradle files
COPY build.gradle settings.gradle ./
COPY gradle/ gradle/
COPY gradlew gradlew.bat ./

# Copy source code
COPY shared-common/ shared-common/
COPY user-service/ user-service/
COPY ai-service/ ai-service/
COPY gateway-service/ gateway-service/

# Build the application
RUN ./gradlew build -x test

# Use OpenJDK 17 as runtime image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file from builder stage
COPY --from=builder /app/user-service/build/libs/*.jar user-service.jar
COPY --from=builder /app/ai-service/build/libs/*.jar ai-service.jar
COPY --from=builder /app/gateway-service/build/libs/*.jar gateway-service.jar

# Expose ports
EXPOSE 8080 8081 8082

# Run the application (this will be overridden in docker-compose)
ENTRYPOINT ["java", "-jar", "gateway-service.jar"]
