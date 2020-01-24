# Introduction

This is the Shopping Cart Engine (SCE) Services source code repository.  SCE Services are back-end micro-services that provide REST API for user to add item to cart and checkout. There are REST API for admin user to update the shopping catalog.


# Development and Design

This micro services design with the use of spring boot and in-memory database. 

This service will load the inventory data once the service started. Service can be started with `.jar`
file or with the use IDE `Spring Tool Suite (STS) or InteliJ`.

Step to run the jar file:
---

1) Download or clone the project.
2) Go to the download or clone directory and build the project with maven command `mvn clean package`
3) Run the jar file `java -jar target/cart-0.0.1-SNAPSHOT.jar` 
4) Service will start and inventory data will be loaded to the in-memory database.  
 
---

Service URL for Swagger and H2 Database
---
All the api details are available in the swagger document.

http://localhost:8900/swagger-ui.html#/
![Image description](https://github.com/alokSNCR/shoppingkart/blob/master/swagger-cart.png)


In-memory h2 database used to demonstrate the shopping kart. Below is the url for in-memory database details, no password required to connect with database.Below url provide all the in-memory database details.

http://localhost:8900/h2-console/login.jsp

![Image description](https://github.com/alokSNCR/shoppingkart/blob/master/h2-database.png)

    Note: Please use this url to connect in-memory databse. If any issue occurred.
    URL: jdbc:h2:mem:testdb
---