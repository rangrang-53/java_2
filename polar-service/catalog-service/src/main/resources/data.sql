show databases;
use catalog;

CREATE TABLE IF NOT EXISTS book (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    title VARCHAR(255) NOT NULL,
    create_at TIMESTAMP NOT NULL,
    last_modified_at TIMESTAMP NOT NULL,
    version INT NOT NULL
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

show tables;

CREATE DATABASE IF NOT EXISTS `order`;

select * from book;