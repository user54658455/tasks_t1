#use "docker build --file authapp.Dockerfile -t authapp ." to build
# Use the official OpenJDK base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /authapp

# Copy the built jar file into the container
COPY auth-module-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8040
EXPOSE 8040

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]