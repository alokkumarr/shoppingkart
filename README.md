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

All the items available in the invetory can be find by the use of below item inventory endpoint.

`http://localhost:8900/admin/catalog/item`

- Response of catalog inventory
```$xslt
[
  {
    "id": 1,
    "sku": "IB123",
    "category": "Book",
    "name": "One India Girl",
    "price": 256.98,
    "bookDetails": {
      "id": 1,
      "publisher": "Oxford",
      "bestseller": "yes",
      "language": "English, French",
      "author": "Chetan Bhagat",
      "year": "2006",
      "type": "Novel",
      "description": "Romantic story"
    }
  },
  {
    "id": 2,
    "sku": "IB124",
    "category": "Book",
    "name": "The Great Gatsby",
    "price": 456.98,
    "bookDetails": {
      "id": 2,
      "publisher": "Oxford",
      "bestseller": "yes",
      "language": "English, French",
      "author": "Fitzgerald, F. Scott",
      "year": "2009",
      "type": "Novel",
      "description": "Suspense and drama"
    }
  },
  {
    "id": 3,
    "sku": "IB125",
    "category": "Book",
    "name": "A Passage to India",
    "price": 356.7,
    "bookDetails": {
      "id": 3,
      "publisher": "Oxford",
      "bestseller": "yes",
      "language": "English, French",
      "author": "Fitzgerald, F.",
      "year": "2006",
      "type": "Novel",
      "description": "Indian history and culture"
    }
  },
  {
    "id": 4,
    "sku": "IB127",
    "category": "Book",
    "name": "Management Book",
    "price": 456.98,
    "bookDetails": {
      "id": 4,
      "publisher": "Oxford",
      "bestseller": "No",
      "language": "English, French",
      "author": "Dr. Eugene F. Brigham",
      "year": "2009",
      "type": "Management",
      "description": "Financial Management: Core Concepts"
    }
  }
]
```

For every add to cart request sku will be unique for each product.
- Sample add to cart request:
```$xslt
{
    "cartItem": {
        "itemId": "1",
        "qty": 4,
        "sku": "IB127"
    },
    "customerRef": {"id": "1"}
}
```

- Sample Cart Response
```$xslt
{
    "cartDetails": [
        {
            "id": 1,
            "itemId": 4,
            "customerId": 1,
            "quantity": 4,
            "sku": "IB127",
            "name": "Management Book",
            "price": 456.98,
            "category": "Book",
            "productType": "Management",
            "description": "Financial Management: Core Concepts"
        },
        {
            "id": 2,
            "itemId": 1,
            "customerId": 1,
            "quantity": 2,
            "sku": "IB123",
            "name": "One India Girl",
            "price": 256.98,
            "category": "Book",
            "productType": "Novel",
            "description": "Romantic story"
        }
    ],
    "message": "Item added in the cart."
}
```

In-memory h2 database used to demonstrate the shopping kart. Below is the url for in-memory database details, no password required to connect with database.Below url provide all the in-memory database details.

http://localhost:8900/h2-console/login.jsp

![Image description](https://github.com/alokSNCR/shoppingkart/blob/master/h2-database.png)

    Note: Please use this url to connect in-memory databse. If any issue occurred.
    URL: jdbc:h2:mem:testdb
---