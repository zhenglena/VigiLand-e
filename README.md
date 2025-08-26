<br />
<div align="center">
<h1 align="center">VigiLand-e API</h1>

  <p align="center">
    A simple API to query the City of Chicago's Building Violations and Building Code Scofflaws List (for tenant vigilante use ONLY).
  </p>
</div>

# About this API
This project is a Spring Boot REST API for working with Chicago building code enforcement data. It provides endpoints to query:
* Scofflaw properties — buildings and their owners flagged as chronic code violators.
* Building violations — recorded code violations tied to specific addresses.

and provides an additional endpoint for a user to write their own comment tied to a specific address (a signal for help, maybe?).

## Setup
### Requirements:
* Java JRE and JDK 17+
* PostgreSQL installed and running
### Step 1: Create a database
* Run these commands in your terminal:
``` 
psql -U postgres -d postgres
CREATE ROLE <username> WITH LOGIN PASSWORD <password>;
CREATE DATABASE vigilande_db OWNER <username>;
\q
```
### Step 2: Configure credentials in main/application.properties
```
spring.application.name=vigilande
spring.datasource.url=jdbc:postgresql://localhost:5432/vigilande_db
spring.datasource.username=<username> <-- your username
spring.datasource.password=<password> <-- your password
spring.datasource.driver-class-name=org.postgresql.Driver

spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:TableSchema.sql
```

### Step 3: Run ingestion script
```
./gradlew bootRun --args='--spring.profiles.active=ingest'
```

### Step 4: Run main application
```
./gradlew bootRun
```

## Integration Test Setup to test PropertyDaoTest ONLY
### Step 1: Create a test database
* Run these commands in your terminal:
``` 
psql -U postgres -d postgres
CREATE DATABASE vigilande_db OWNER <username>;
\q
```
### Step 2: Configure datasource in test/resources/application-test.properties
```
spring.application.name=vigilande
spring.datasource.url=jdbc:postgresql://localhost:5432/vigilande_db_test <-- new
spring.datasource.username=<username> <-- your username
spring.datasource.password=<password> <-- your password
spring.datasource.driver-class-name=org.postgresql.Driver

spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:TableSchema.sql
```
### Step 5: Run tests in PropertyDao

## Built With
* Spring Boot
* PostgreSQL JDBC Driver
* ApacheCommonsCSV

# Design
## Architecture
This system follows standard Spring Boot conventions.
### Controller Layer
* `PropertyController.java` — Defines REST endpoints for querying scofflaws and violations by address and date.

### Service Layer

* `PropertyService.java` — Encapsulates business logic and orchestrates between the controller and DAO.

### DAO Layer

* `PropertyDao.java` — Executes SQL queries to database tables using JdbcTemplate.

### Database Layer
* PostgreSQL stores three tables: Violations, Scofflaws, and Comments.
* Schema is defined in resources/TableSchema.sql

## Database
### Violations Table
```
id : varchar (PK)
violation_date : date
violation_code : varchar
violation_status : varchar
violation_description : text
violation_inspector_comments : text
address : varchar 
```

### Scofflaws Table
```
record_id : varchar (PK)
address : varchar
building_list_date : date
```

### Comments Table
```
comment_id : serial (PK)
author : varchar
created_at : timestamp
address : varchar
comment : text
```
## Data Flow
### 1: Data Ingestion
* On startup, `TableSchema.sql` is automatically run by Spring Boot and creates tables in the database.
* With `ingest` profile active, `IngestRunner` executes and loads CSV data into the database using `IngestService`.

### 2: API Requests
* A client (`cURL`) makes an HTTP request to a controller endpoint.
* Controller endpoint passes the request to the service.
* Service calls DAO, which uses JDBCTemplate to query PostgreSQL.
* Results are passed back up as responses and returned to the client as a JSON.

## Profiles
* default profile — Runs the application normally, exposing the API.
* `ingest` profile — Loads the dataset into the database and then exits when done.
* `test` profile — Points datasource to a test database filled with test data for integration testing.

## Future Improvements
* Containerizing the API for consistent environments
* Improve test coverage
* Do batch updates for faster ingesting of CSV data
* Put SQL commands into a properties file for easier configurability


