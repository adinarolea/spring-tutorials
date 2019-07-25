drop table if exists purse;

CREATE TABLE purse
(
    id VARCHAR(50),
    width DECIMAL(5,2),
    plength DECIMAL(5,2),
    no_pockets INT(50),
    purse_type VARCHAR(50),
    PRIMARY KEY (id)
);

INSERT INTO purse(id,width,plength,no_pockets,purse_type)
VALUES ('1',11.1,10.5,2,'CASUAL'),
       ('2',11.1,5.5,1,'CASUAL'),
       ('3',5.7,5.5,1,'CLASSY'),
       ('4',12.1,13.5,4,'SPORT'),
       ('5',11.1,5.5,2,'SPORT');