# demo-itst-apl
## podman
### Pod
* podman pod create --name demo-itst-pod -p 5432:5432,8280:8280,8281:8281,8282:8282,8283:8283,8284:8284
### postgresql
* podman run --name postgres-itst --pod demo-itst-pod -e POSTGRES_PASSWORD=＠ -d postgres:16
* podman exec -it postgres-srv bash
* psql -U postgres
### shopping-entry 8280
* podman build -t shopping-entry .
* podman run --name shopping-entry-srv --pod demo-itst-pod -e SIMURATION_DATE=＠ -d shopping-entry
### shopping-put 8281
* podman build -t shopping-put .
* podman run --name shopping-put-job --pod demo-itst-pod --volume /Users-Path:/app/data -e SIMURATION_DATE=＠ -d shopping-put
### pay-create 8282
* podman build -t pay-create .
* podman run --name pay-create-srv --pod demo-itst-pod -e SIMURATION_DATE=＠ -d pay-create
### shopping-pay-create 8283
* podman build -t shopping-pay-create .
* podman run --name shopping-pay-create-job --pod demo-itst-pod --volume /Users-Path:/app/data -e SIMURATION_DATE=＠ -d shopping-pay-create
### yakujo-henko 8284
* podman build -t yakujo-henko .
* podman run --name yakujo-henko-srv --pod demo-itst-pod -e SIMURATION_DATE=＠ -d yakujo-henko
### pay-fix-inf-create 8285
* podman build -t pay-fix-inf-create .
* podman run --name pay-fix-inf_-create-job --pod demo-itst-pod -e SIMURATION_DATE=＠ -d pay-fix-inf-create
### pay-fix-doc-create 8286
* podman build -t pay-fix-doc-create .
* podman run --name pay-fix-doc-create-job --pod demo-itst-pod --volume /Users-Path:/app/data -e SIMURATION_DATE=＠ -d pay-fix-doc-create
### pay-fix-get 8287
* podman build -t pay-fix-get .
* podman run --name pay-fix-get-job --pod demo-itst-pod --volume /Users-Path:/app/data -e SIMURATION_DATE=＠ -d pay-fix-get
### demo-apl-viewer 8288
* podman build -t demo-apl-viewer .
* podman run --name demo-apl-viewer-srv --pod demo-itst-pod -d demo-apl-viewer