# Name of the project
> API Automobiles

This project aims to import a file in the "*.csv" format from vehicle agencies and make queries for the respective vehicles available.

## Technology prerequisites

- Install Java 17, perhaps if you are using Linux or Mac you can use SDK Man to manage Java versions.
- API developed in Spring Boot 3.
- Postgres relational database version 42.6.0.
- Maven dependency management.
- ModelMapper to facilitate object mapping.
- Lombok to facilitate the writing of getter, setter, equals or constructors methods through class notations.
- Docker to download images from the database used in the project.

### project structure and of the package

- Package configuration: Beans that have the responsibility for configuring third-party objects.
- Package controller: Application flow whose main responsibility is to receive requests from the external world.
- Package db: Database object creation scripts and spreadsheet with vehicle information to be imported through the import API.
- Package dto: Have the project data transfer objects.
- Package entity: Has the entities that were mapped to create objects and relationships in the database. Hibernate / Spring Data notations were used in this project.
- Package exception: Classes that throw overflows if an overflow occurs in the API.
- Package message: Class that is responsible for sending a message to the API.
- Package Postman_collection: Collections of API resources that have been developed.
- Package Repository: Design pattern similar to DAO (Data Access Object) in the sense that its purpose is to abstract data access generically from its model.
- Package service: It has the classes that are responsible for the flow of API business rules.
- Package specification: The Specification is a Spring Data JPA interface that allows the creation of dynamic queries, that is, queries that can be assembled at run time
- Package util: project utility classes

### Documentation

- Points to be observed in the execution process are: 
  - Runs on port 8080;
  - Access through swagger-ui = (http://localhost:8080/swagger-ui/index.html)

