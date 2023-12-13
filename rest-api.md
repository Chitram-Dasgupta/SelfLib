# Setting up a Spring REST API project

Go to the spring initializer website, and create a new Java Spring Boot project
and add the following dependencies:

1. Lombok
2. Spring Data DPA
3. Validation
4. PostgresSQL driver
5. Spring Web
6. Spring DevTools

# Set up the database connection

```
spring.datasource.url=jdbc:postgresql://localhost:5432/javadb
spring.datasource.username=javauser
spring.datasource.password=javapassword
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
```

# API documentation

## Authors resource
1. **POST** `/authors` creates an author
2. **GET** `/authors/{id}` fetches an author
3. **GET** `/authors` fetches a collection of authors
4. **PUT** `/authors/{id}` updates all the attributes of an author
5. **PATCH** `/authors/{id}` updates some of the attributes of an author
6. **DELETE** `/authors/{id}` deletes an author

## Books resource
1. **PUT** `/books/{isbn}` creates a book by passing in the *ISBN*
2. **GET** `/books/{isbn}` fetches a book
3. **GET** `/books` fetches a collection of books
4. **PUT** `/books/{isbn}` updates all the attributes of a book
5. **PATCH** `/books/{isbn}` updates some of the attributes of a book
6. **DELETE** `/books/{isbn}` deletes a book

# Setting up the database interaction for authors

1. Create an author `entity`

   Add the table name, the attribute names, and their setters and getters,
   and the validations.

2. Create the author `repository`

   Extend the repository interface with the appropriate functionality

3. Create the author `DTO`

   The author DTO will be a plain Java class.

4. Create the `ModelMapper`

   The ModelMapper bean will between the author entity and author DTO

5. Create an interface for the mapper and implement it for the author

# Laying up the groundwork for the presentation layer: controllers

1. Create an author `service interface`

   This interface will have the methods that we want to use from the
   controller

2. Implement the author interface

   This will flesh out the methods declared in the interface

3. Create the AuthorController class annotated with `@RestController`

# The workflow for creating the API

1. Define the API endpoints in the controller and use the methods (not yet
   defined) from the service interface.
2. Declare the methods in the service layer interface
3. Actually implement them in the service layer concrete class.
   Most of the time, the service layer methods will be simple pass-through
   methods for the repository methods; at other times we will have to
   write methods in the repository interface, as well.
