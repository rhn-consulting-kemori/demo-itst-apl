# yakujo-henko
## Sample
curl -X POST -H "Content-Type: application/json" -d '{"customer_number": "0000000001", "sprv_limit_amount_flg": "1", "sprv_init_furi_menjo_flg": "1", "sprv_monthly_payamount_flg": "1", "sprv_rate_flg": "1", "sprv_limit_amount": 200000, "sprv_init_furi_menjo": "0", "sprv_monthly_payamount": 20000, "sprv_rate": 0.18}' localhost:8284/camel/demo/yakujohenko/entry
