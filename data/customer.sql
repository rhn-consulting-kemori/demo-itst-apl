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

CREATE TABLE yakujo_henko_history(
    history_seq int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    customer_number VARCHAR NOT NULL,
    change_date VARCHAR NOT NULL,
    pay_change_seikyu_shimebi VARCHAR NOT NULL,
    before_sprv_limit_amount int,
    before_sprv_init_furi_menjo VARCHAR,
    before_sprv_monthly_payamount int,
    before_sprv_rate double precision,
    after_sprv_limit_amount int,
    after_sprv_init_furi_menjo VARCHAR,
    after_sprv_monthly_payamount int,
    after_sprv_rate double precision
);