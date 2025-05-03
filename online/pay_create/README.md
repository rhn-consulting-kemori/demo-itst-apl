# pay_create
## Sample
curl -X POST -H "Content-Type: application/json" -d '{"customer_number": "0000000001", "pay_type": "SP1回", "seikyu_shimebi": "20250515", "use_date": "20250501", "use_amount": 50000}' localhost:8282/camel/demo/pay_create/sp1
curl -X POST -H "Content-Type: application/json" -d '{"customer_number": "0000000001", "pay_type": "SPリボ", "seikyu_shimebi": "20250515", "use_date": "20250501", "use_amount": 10000}' localhost:8282/camel/demo/pay_create/sprv
curl -X POST -H "Content-Type: application/json" -d '{"customer_number": "0000000001", "pay_type": "SPリボ", "seikyu_shimebi": "20250515"}' localhost:8282/camel/demo/yakujo_henko/sprv
