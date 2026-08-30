# User Management

Spring Boot REST API for user management.

## Requirements

- Java 21
- Maven
- MySQL 8.x

## Database Setup

Create a MySQL database:

```sql
CREATE DATABASE user_management;
```

The application uses the following environment variables:

Variable	    Description	                Example
`DB_URL`	    MySQL JDBC connection URL	`jdbc:mysql://localhost:3306/user_management`
`DB_USERNAME`	MySQL username	            `root`
`DB_PASSWORD`	MySQL password	            `your_password`

`DB_PASSWORD` is optional. If it is not provided, the application uses an empty password.

## Run the Application

### Windows PowerShell

Set the database environment variables:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/user_management"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your_password"
```

If the MySQL user has no password:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/user_management"
$env:DB_USERNAME="root"
```

Run the application:

```powershell
.\mvnw.cmd spring-boot:run
```

The application will start at:

```text
http://localhost:8080
```

## Configuration

Database credentials are provided through environment variables and are not stored in the source code.

The application configuration uses:

```yaml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD:}
```

The default JPA configuration is:

```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: update
```

Hibernate will automatically create or update database tables based on the application's JPA entities.