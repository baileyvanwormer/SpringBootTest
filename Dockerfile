# Use Maven base image with OpenJDK 17
FROM maven:3.9.5-openjdk-17-slim

# Set working directory
WORKDIR /app

# Copy pom.xml first for better caching
COPY pom.xml ./

# Download dependencies (this layer will be cached if pom.xml doesn't change)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use OpenJDK runtime image for smaller final image
FROM openjdk:17-jre-slim

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=0 /app/target/agify-app-1.0.0.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
