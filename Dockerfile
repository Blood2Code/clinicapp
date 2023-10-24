# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy your project directory (source code) into the container
COPY . /app

# Expose the port for debugging (if needed)
EXPOSE 5005

# Define the command to run your Spring Boot application in debug mode
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "clinickapp-0.0.1-SNAPSHOT.jar"]
