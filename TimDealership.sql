DROP DATABASE IF EXISTS `TimsDealership`;
CREATE DATABASE `TimsDealership`;


USE `TimsDealership`;



-- Table 1: dealerships
-- Primary key allows unique IDs and no duplicates
CREATE TABLE `dealerships` (
    `dealership_id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    `address` VARCHAR(50) NOT NULL,
    `phone` VARCHAR(12) NOT NULL
);

-- Table 2: vehicles
CREATE TABLE `vehicles` (
    `VIN` VARCHAR(17) PRIMARY KEY, -- VINs are typically 17 characters long
    `make` VARCHAR(50) NOT NULL,
    `model` VARCHAR(50) NOT NULL,
    `year` INT NOT NULL,
    `price` DECIMAL(10, 2) NOT NULL,
    `SOLD` BOOLEAN NOT NULL DEFAULT 0
);

-- Table 3: inventory (tracks which dealership has the vehicles)
CREATE TABLE `inventory` (
    `dealership_id` INT NOT NULL,
    `VIN` VARCHAR(17) NOT NULL,
    PRIMARY KEY (`dealership_id`, `VIN`),
    FOREIGN KEY (`dealership_id`) REFERENCES `dealerships`(`dealership_id`),
    FOREIGN KEY (`VIN`) REFERENCES `vehicles`(`VIN`)
);

-- Table 4: sales_contracts
CREATE TABLE `sales_contracts` (
    `contract_id` INT AUTO_INCREMENT PRIMARY KEY,
    `VIN` VARCHAR(17) NOT NULL,
    `customer_name` VARCHAR(50) NOT NULL,
    `sale_date` DATE NOT NULL,
    `sale_price` DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (`VIN`) REFERENCES `vehicles`(`VIN`)
);

-- Table 5: (OPTIONAL) lease_contracts
CREATE TABLE `lease_contracts` (
    `contract_id` INT AUTO_INCREMENT PRIMARY KEY,
    `VIN` VARCHAR(17) NOT NULL,
    `customer_name` VARCHAR(50) NOT NULL,
    `lease_start_date` DATE NOT NULL,
    `lease_end_date` DATE NOT NULL,
    `monthly_payment` DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (`VIN`) REFERENCES `vehicles`(`VIN`)
);

-- Step 3: Populate the tables with sample data

INSERT INTO `dealerships` (`name`, `address`, `phone`) VALUES
('Tim\'s Main Dealership', '123 Main St, Anytown, USA', '123-456-7890'),
('Tim\'s Luxury Dealership', '456 Luxury Ln, Uptown, USA', '987-654-3210');


INSERT INTO `vehicles` (`VIN`, `make`, `model`, `year`, `price`, `SOLD`) VALUES
('1HGCM82633A123456', 'Kellog', 'Chi-SSiT', 2323, 2800000.00, 0),
('1HGCM82633A654321', 'Nabisco', 'O-Rio', 2322, 2700000.00, 1),
('1HGCM82633A789012', 'Ford', 'Ranger', 2020, 3500000.00, 0);


INSERT INTO `inventory` (`dealership_id`, `VIN`) VALUES
(1, '1HGCM82633A123456'),
(1, '1HGCM82633A654321'),
(2, '1HGCM82633A789012');


INSERT INTO `sales_contracts` (`VIN`, `customer_name`, `sale_date`, `sale_price`) VALUES
('1HGCM82633A654321', 'John Doe', '2023-11-01', 27000.00);


INSERT INTO `lease_contracts` (`VIN`, `customer_name`, `lease_start_date`, `lease_end_date`, `monthly_payment`) VALUES
('1HGCM82633A123456', 'Jane Smith', '2023-11-01', '2026-11-01', 500.00);
