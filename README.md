# demo-itst-apl
## podman
### Pod
* podman pod create --name demo-itst-pod -p 5432:5432,8280:8280,8281:8281,8282:8282,8283:8283,8284:8284
### postgresql
* podman run --name postgres-itst --pod demo-itst-pod -e POSTGRES_PASSWORD=＠ -d postgres:16
* podman exec -it postgres-srv bash
* psql -U postgres
### shopping_entry 8280
* podman build -t shopping_entry .
* podman run --name shopping_entry_srv --pod demo-itst-pod -e SIMURATION_DATE=＠ -d shopping_entry
### shopping_put 8281
* podman build -t shopping_put .
* podman run --name shopping_put_job --pod demo-itst-pod --volume /Users-Path:/app/data -e SIMURATION_DATE=＠ -d shopping_put
### pay_create 8282
* podman build -t pay_create .
* podman run --name pay_create_srv --pod demo-itst-pod -e SIMURATION_DATE=＠ -d pay_create
### shopping_pay_create 8283
* podman build -t shopping_pay_create .
* podman run --name shopping_pay_create_job --pod demo-itst-pod --volume /Users-Path:/app/data -e SIMURATION_DATE=＠ -d shopping_pay_create
### yakujo_henko 8284
* podman build -t yakujo_henko .
* podman run --name yakujo_henko_srv --pod demo-itst-pod -e SIMURATION_DATE=＠ -d yakujo_henko
