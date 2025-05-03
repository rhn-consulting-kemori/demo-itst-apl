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