# Stage 1: Build - Uses Maven to compile the JAR
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Runtime - The actual image that gets deployed
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Expose the port Spring Boot runs on
EXPOSE 8080

# Run the app with optimized memory for cloud environments
ENTRYPOINT ["java", "-Xmx512m", "-Xms256m", "-jar", "app.jar"]