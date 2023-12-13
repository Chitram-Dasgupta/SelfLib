# Setting up a Spring REST API project

Go to the spring initializer website, and create a new Java Spring Boot project
and add the following dependencies:

1. Lombok
2. Spring Data DPA
3. PostgresSQL driver
4. Spring Web
5. Spring DevTools

# Set up the database connection

```
spring.datasource.url=jdbc:postgresql://localhost:5432/javadb
spring.datasource.username=javauser
spring.datasource.password=javapassword
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
```