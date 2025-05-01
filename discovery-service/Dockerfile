# Use Amazon Corretto 21 as the base image
FROM amazoncorretto:21-alpine-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the pre-built JAR file from your local machine to the container
COPY target/discovery-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application will run on
EXPOSE 8761

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
