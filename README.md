# IMS

Running the Spring Boot Application

This application uses Spring Web, JPA, AOP, Starter Validation and Staerter Test.

Prerequisites

1. Java Development Kit(JDK) 21.
2. Maven
3. MYSQL Database

Setting up the application

1. Configure the Database with the provided SQL Script.
2. Go to project directory and execute "mvn clean install".
3. Run the application - "mvn spring-boot:run".


To test the application

- "mvn test"

Use Postman to test/use the API endpoint-

1. Featching all entries - Get http://localhost:8080/inventory/getall

2. Get a particular resource - Get http://localhost:8080/inventory/get/{id}

3. create a resource - 

	Post http://localhost:8080/inventory/create
	Response body
	{
	"name" = "xyz",
	"description" = "xyz"
	}
4. update a resource status - Put http://localhost:8080/inventory/updatestatus/{id}

5. Delete a resource - Delete http://localhost:8080/inventory/delete/{id}





