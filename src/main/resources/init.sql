CREATE TABLE IF NOT EXISTS dbo.customers (customerId SERIAL PRIMARY KEY,firstName VARCHAR,lastName VARCHAR,phoneNumber VARCHAR,streetAddress VARCHAR, zipCode VARCHAR,balance FLOAT8);

INSERT INTO dbo.customers (firstName,lastName,phoneNumber,streetAddress,zipCode,balance) VALUES ('Testy','Tester','5555555555','402 Otterson Dr','95928',0);