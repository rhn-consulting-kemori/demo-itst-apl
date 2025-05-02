-- Active: 1742567021688@@127.0.0.1@5432@postgres
CREATE TABLE customer(
    customer_number VARCHAR NOT NULL PRIMARY KEY,
    card_number VARCHAR NOT NULL,
    customer_name VARCHAR NOT NULL,
    customer_address VARCHAR,
    sprv_limit_amount int,
    sprv_init_furi_menjo VARCHAR,
    sprv_monthly_payamount int,
    sprv_rate double precision
);

INSERT INTO customer VALUES('0000000001', '354000000001', '山田　太郎', '恵比寿１−１', 500000, '1', 10000, 0.15);