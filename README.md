# Booking Service

A reactive Spring Boot application for managing Loans

## Tech Stack

- Java 21
- Spring Boot 3.4.4
- Spring WebFlux
- OpenAPI/Swagger
- Gradle 8.x

## Prerequisites

- JDK 21
- Gradle
- Lombok

### Lombok Configuration
1. Install Lombok:
   - IntelliJ IDEA: Install Lombok plugin via Settings -> Plugins
   - Eclipse: Download lombok.jar from [projectlombok.org](https://projectlombok.org/download)
      - Run `java -jar lombok.jar`
      - Select your Eclipse installation
      - Click "Install/Update"
      - Restart Eclipse

2. Enable annotation processing:
   - IntelliJ IDEA: Settings -> Build -> Compiler -> Annotation Processors
   - Eclipse: No additional steps needed after Lombok installation
   
## Quick Start

1. Build & Run:
```bash
   # Set JAVA_HOME (if not set)
   # Windows
   set JAVA_HOME=path\to\jdk-21

   # Linux/MacOS
   export JAVA_HOME=/path/to/jdk-21

   # Build and run
   ./gradlew bootRun
```   
2. Access the application:
   - Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - Localhost: [http://localhost:8080](http://localhost:8080)
