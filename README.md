# webflux-spring-reactive
webflux reactive programming

######## Regular Rest endpoints

* get All Accounts
```curl -X GET \
  http://localhost:7609/accounts \
  -H 'Postman-Token: 7e8fb7ee-0e3d-4ab3-94b4-82b0ab06e143' \
  -H 'cache-control: no-cache'
  ```
* save all Accounts
```
curl -X POST \
  http://localhost:7609/saveAll \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 5410eea8-33ae-4bc1-956e-93ae62f53cb1' \
  -H 'cache-control: no-cache' \
  -d '[
    {
        "id": "BANGALORE_3_01",
        "owner": "Likitha Sree",
        "value": 99999999999
    },
    {
        "id": "BANGALORE_4_01",
        "owner": "Yet to be named",
        "value": 12314355674678
    }
]'
```
* save 
```
curl -X POST \
  http://localhost:7609/save \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 832e84c5-ea9d-436f-9724-a5dc2020a1f4' \
  -H 'cache-control: no-cache' \
  -d '{
	"id":"BANGALORE_1_01",
	"owner":"Charan",
	"value": 99999999999
}'
```

* save accounts as stream.

```
curl -X POST \
  http://localhost:7609/save/accounts \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: d68eb6bd-618c-4868-979a-b18eff1adc78' \
  -H 'cache-control: no-cache' \
  -d '[
    {
        "id": "MACHILIPATNAM_1_01",
        "owner": "Anantha Lakshmi",
        "value": 99999999999
    },
    {
        "id": "MACHILIPATNAM_2_01",
        "owner": "Potaiah",
        "value": 12314355674678
    }
]'
```

######## Functional endpoints
* functional endpoint - save customer
```
curl -X POST \
  http://localhost:7609/customers \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 632b6a90-24b1-4476-b86d-d993d5d2d07f' \
  -H 'cache-control: no-cache' \
  -d '{
	"id":"3",
	"name":"Likitha Sree",
	"occupation":"Half-ticket",
	"bankName":"HDFC Bank"
}'
```

* functional endpoint - update cusotmer
```
curl -X PUT \
  http://localhost:7609/customers/1 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 0ba1b524-be17-4a24-a7ac-6bc060127e30' \
  -H 'cache-control: no-cache' \
  -d '{
	"id":"1",
	"name":"Charan Tej Thota",
	"occupation":"Senior Digital Analyst",
	"bankName":"HDFC Bank"
}'
```

* functional endpoint - get single customer
```
curl -X GET \
  http://localhost:7609/customers/1 \
  -H 'Postman-Token: f730994b-21de-4807-82f6-728f5bdef354' \
  -H 'cache-control: no-cache'
```

* functinal endpoint - get all the customers

```
curl -X GET \
  http://localhost:7609/customers \
  -H 'Postman-Token: af3c312b-2db4-4af4-896c-fdea0bdab143' \
  -H 'cache-control: no-cache'
```

* functional endpoint - delete the customer
```
curl -X DELETE \
  http://localhost:7609/customers/1 \
  -H 'Postman-Token: 131fe444-fa6b-4830-9e13-7553234418b5' \
  -H 'cache-control: no-cache'
```
