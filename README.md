# videorentalsrus

## Setup instructions

1. Make sure you have a local instance of postgresql(port 5332) and redis running, and the Postgres JDBC driver installed
2. If your postgres user for postgresql has a password, add spring.datasource.password=[password] to application.properties
3. Run mvn exec:exec. This will create the schema for this project. Had to resort to this because Spring
and Postgresql user defined functions/anonymous functions don't get along
4. Run mvn spring-boot:run
5. There are a few collections of postman scripts in the postman folder.
  * Init will create some starter data
  * ExampleGet has a bunch of example GET requests
  * ExampleRentals can be run individually or as a group. There are two rental creation requests and one return rental
    request that will return(as in "turn in") the second rental

