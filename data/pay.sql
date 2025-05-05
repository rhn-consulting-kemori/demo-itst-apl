-- Active: 1742567021688@@127.0.0.1@5432@postgres
CREATE TABLE pay_record(
    customer_number VARCHAR NOT NULL,
    seikyu_shimebi VARCHAR NOT NULL,
    pay_date VARCHAR NOT NULL,
    pay_amount int NOT NULL,
    sp1_pay_amount int NOT NULL,
    sprv_pay_gankin int NOT NULL,
    sprv_pay_tesuryo int NOT NULL,
    sprv_togetu_zandaka int NOT NULL,
    sprv_togetu_kurizan int NOT NULL,
    sprv_jigetu_kurizan int NOT NULL,
    sprv_init_furi_menjo VARCHAR,
    sprv_monthly_payamount int,
    sprv_rate double precision,
    primary key (customer_number, seikyu_shimebi)
);

CREATE TABLE pay_fix_inf(
    customer_number VARCHAR NOT NULL,
    seikyu_shimebi VARCHAR NOT NULL,
    pay_date VARCHAR NOT NULL,
    pay_amount int NOT NULL,
    sp1_pay_amount int NOT NULL,
    sprv_pay_gankin int NOT NULL,
    sprv_pay_tesuryo int NOT NULL,
    card_number VARCHAR NOT NULL,
    customer_name VARCHAR NOT NULL,
    customer_address VARCHAR,
    sprv_limit_amount int,
    sprv_togetu_zandaka int NOT NULL,
    sprv_togetu_kurizan int NOT NULL,
    sprv_jigetu_kurizan int NOT NULL,
    sprv_init_furi_menjo VARCHAR,
    sprv_monthly_payamount int,
    sprv_rate double precision,
    primary key (customer_number, seikyu_shimebi)
);