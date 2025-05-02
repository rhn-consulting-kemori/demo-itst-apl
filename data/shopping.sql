-- Active: 1742567021688@@127.0.0.1@5432@postgres
CREATE TABLE shopping_entry(
    shopping_number int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    customer_number VARCHAR NOT NULL,
    use_card_number VARCHAR NOT NULL,
    use_date VARCHAR NOT NULL,
    shop_name VARCHAR NOT NULL,
    use_amount int NOT NULL,
    pay_type VARCHAR NOT NULL
);

CREATE TABLE shopping_record(
    shopping_number int NOT NULL PRIMARY KEY,
    customer_number VARCHAR NOT NULL,
    use_card_number VARCHAR NOT NULL,
    seikyu_shimebi VARCHAR NOT NULL,
    pay_type VARCHAR NOT NULL,
    use_date VARCHAR NOT NULL,
    shop_name VARCHAR NOT NULL,
    use_amount int NOT NULL
);