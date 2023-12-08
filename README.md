# Spring boot REST API library project
This project provides a REST API for a library use case.

This projcet uses h2 in-memory data base and swagger.

Please call the API methods at "http://localhost:8080/swagger-ui/index.html" when you run the project.

Three services are implimented:
* returns all users who have actually borrowed at least one book
* returns all non-terminated users who have not currently borrowed anything
* returns all users who have borrowed a book on a given date
  * __please enter the desired date parameter with this format "yyyy-MM-dd"__

