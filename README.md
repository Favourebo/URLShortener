# URLShortener
A Java Service built with Java 8, Spring Boot 2.4.5, and Redis.

Package utils (com.carbon.urlshortener.utils)

CommonUtils.java: 
A class that contains methods responsible for:
1. Generating ID (createdId())
2. Convert ID to Base 62 String (base62Encode(long value) )
3. Convert Base 62 String to ID

ConstantUtils: 
A class that contains static variables


controller (com.carbon.urlshortener.controller)

UrlController.java:
A Spring Boot Controller responsible for:
1. Serving an endpoint to shorten URL
2. Redirect shortened URL to the original URL


repository (com.carbon.urlshortener.repository)

URLRepository: 
A Java interface responsible for abstracting Redis(database) read/write logic
service

repository (com.carbon.urlshortener.service)

UrlService.java:
A Java class used to abstract URL Shortening and URL Retrieval process
URLShortenerApplication.java
The entry point for the Spring application

To run:
1. Start up Redis' Server
redis-server
Build the project
mvn clean install
Run the project


By default the Server will run on localhost:8080/shortener
To test, send POST Request to localhost:8080/shortener with a body of type application/json with body
{
  'url' : '<INSERT URL>'
}