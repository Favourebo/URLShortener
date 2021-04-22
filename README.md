# URLShortener
A Java Service built with Java 8, Spring Boot 2.4.5, and Redis.

## utils (com.carbon.urlshortener.utils)
CommonUtils.java: 
A class that contains methods responsible for:
1. Generating ID (createdId())
2. Convert ID to Base 62 String (base62Encode(long value) )
3. Convert Base 62 String to ID

ConstantUtils: 
A class that contains static variables


## controller (com.carbon.urlshortener.controller)

UrlController.java:
A Spring Boot Controller responsible for:
1. Serving an endpoint to shorten URL
2. Redirect shortened URL to the original URL


## repository (com.carbon.urlshortener.repository)

URLRepository: 
A Java interface responsible for abstracting Redis(database) read/write logic
service

## service (com.carbon.urlshortener.service)

UrlService.java:
A Java class used to abstract URL Shortening and URL Retrieval process

## URLShortenerApplication.java
The entry point for the Spring application


## Prerequisites

The following dependencies are required to run the application

- Java JDK 8
  - For example: <https://adoptopenjdk.net/releases.html?variant=openjdk8&jvmVariant=openj9>
  - Set `JAVA_HOME` system variable with a valid JDK path and add `${JAVA_HOME}/bin` to the PATH variable
- Install a redis server and start it up (if connecting to a remote redis server, ensure its started and reachable)


## Quick Start

Download the source code of this service from <https://github.com/Favourebo/URLShortener/archive/refs/heads/master.zip> and unzip it:

```bash
curl -OL https://github.com/Favourebo/URLShortener/archive/refs/heads/master.zip
unzip URLShortener-master.zip
cd URLShortener-master
```

Build you application:

```bash
mvn clean install
```
Start your application:

```bash
mvn spring-boot:run
```

## Use the Application
By default the Server will run on localhost:8080/shortener
To test, send POST Request to localhost:8080/shortener with a body of type application/json with body
{
  'longUrl' : '<INSERT URL>'
}

You can test from the swagger page
The default page <http://localhost:8080/> opens Swagger UI with the API Documentation

![swagger](https://user-images.githubusercontent.com/20109531/115640446-e0b8a900-a30e-11eb-945a-4f35bf8d49a4.PNG)
