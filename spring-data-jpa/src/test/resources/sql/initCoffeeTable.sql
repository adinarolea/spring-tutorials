drop table if exists coffee;

CREATE TABLE coffee
(
    id VARCHAR(50),
    price DECIMAL(5,2),
    origin VARCHAR(50),
    PRIMARY KEY (id)
);