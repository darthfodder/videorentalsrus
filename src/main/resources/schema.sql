CREATE TABLE IF NOT EXISTS dbo.customers (customerId SERIAL PRIMARY KEY,firstName VARCHAR,lastName VARCHAR,phoneNumber VARCHAR,streetAddress VARCHAR, zipCode VARCHAR,balance FLOAT8);
CREATE TABLE IF NOT EXISTS dbo.videos (videoId SERIAL PRIMARY KEY, title VARCHAR, genre VARCHAR, year INT,videoStatus VideoStatus,videoRentalTypeId INT);
CREATE TABLE IF NOT EXISTS dbo.rentals (rentalId SERIAL PRIMARY KEY, rentalVideoId INT, rentalCustomerId INT, rentalDate DATE, returnedDate DATE, dueDate DATE);
CREATE TABLE IF NOT EXISTS dbo.rentalTypes (rentalTypeId SERIAL PRIMARY KEY, name VARCHAR, rentalDuration INT,rentalFee FLOAT8, dailyLateFee FLOAT8);
