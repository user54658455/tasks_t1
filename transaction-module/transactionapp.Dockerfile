#use "docker build --file transactionapp.Dockerfile -t transactionapp ." to build
# Use the official OpenJDK base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /transactionapp

# Copy the built jar file into the container
COPY transaction-module-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8050
EXPOSE 8050

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]