# Use the official OpenJDK base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file into the container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8060
EXPOSE 8060

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]