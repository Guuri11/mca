
# MCA Challenge - Price Management
Spring Boot Reactive Rest API Service for price Management

## Trello dashboard
https://trello.com/b/NWOlOXzu/mca-challenge

## Installation

To install this project:

Clone the repo
```bash
  git clone https://github.com/Guuri11/mca
```

Go the the repo folder
```
cd mca
```

Generate the jar file
```
  ./mvnw clean package
```

Make sure that you have docker install
```
docker --version
```

Generate the build
```
docker build . --tag mca/price:latest
```

Run the app from docker
```
docker run -it -p 8080:8080 mca/price:latest
```

Access to http://localhost:8080/swagger-ui/index.html to see Swagger Docs. There you will found the price controller ready to try

Make sure that the date format is like this `2023-06-03T10:00:00`f
A curl example from the endpoint
````
curl -X GET "http://localhost:8080/prices?date=2023-06-03T10:00:00&brandId=1&productId=35455
````
