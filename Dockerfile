# Stage 1: Build Stage
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Copy everything into container
COPY . .

# Give permission to mvnw
RUN chmod +x mvnw

# Build the application (skip tests for faster deploy)
RUN ./mvnw clean package -DskipTests


# Stage 2: Runtime Stage
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy only the built jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
