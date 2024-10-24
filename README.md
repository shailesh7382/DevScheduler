# DevScheduler

To Arrange, allocate and schedule dev tasks.

## Project Setup

### Prerequisites

- Java 11
- Maven
- Apache Derby

### Dependencies

The project uses the following dependencies:

- **Spring Boot**: For building the application.
- **Spring Data JPA**: For database interactions.
- **Apache Derby**: As the database.
- **OpenCSV**: For reading users from a CSV file.

### Maven Configuration

Add the following dependencies to your `pom.xml`:

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Apache Derby Database -->
    <dependency>
        <groupId>org.apache.derby</groupId>
        <artifactId>derby</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- OpenCSV -->
    <dependency>
        <groupId>com.opencsv</groupId>
        <artifactId>opencsv</artifactId>
        <version>5.5.2</version>
    </dependency>

    <!-- Spring Boot DevTools (optional) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Spring Boot Starter Test (for testing) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</dependency>
</dependencies>


Database Configuration
Apache Derby stores its database files in the directory specified by the derby.system.home system property. You can set this property in your application.properties file:

spring.datasource.url=jdbc:derby:/path/to/your/database;create=true

CSV File
The project reads user data from a CSV file located in the resources directory. The CSV file should be named dev-users.csv and have the following format:
