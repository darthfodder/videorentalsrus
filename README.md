# videorentalsrus

## Setup instructions

1. Make sure you have a local instance of postgresql and redis running
2. Run mvn exec:exec. This will create the schema for this project. Had to resort to this because Spring
and Postgresql user defined functions/anonymous functions don't get along
3. Run mvn spring-boot:run
4. There are a few collections of postman scripts in the postman folder.
  * Init will create some starter data
  * ExampleGet has a bunch of example GET requests
  * ExampleRentals can be run individually or as a group. There are two rental creation requests and one return rental
    request that will return the second rental

