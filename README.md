# java_currency_exchange_app

- Conversion API
GET http://localhost:8081/currencyConversion/{amount}/{currency}
parameters: 
amount: Number,
currency: String
sample output (Success):
{
    "rates": {
        "CAD": 155.10017,
        "HKD": 953.69330,
        "ISK": 15766.37285,
        ...
    },
    "base": "USD",
    "date": "2021-02-19"
}
throw errors:
Internal Server Error
Invalid Input

- Exchange Rate History API
- Conversion API
GET http://localhost:8081/rateHistory/{currency}
parameters: 
currency: String
sample output (Success):
{
    "rates": {
        "2021-02-01": {
            "CAD": 1.2805362463,
            "HKD": 7.752979146,
            "ISK": 129.3445878848,
            ...
        },
        ...
    },
    "start_at": "2021-01-23",
    "base": "USD",
    "end_at": "2021-02-21"
}
throw errors:
Internal Server Error
Invalid Input

test
mvn test

build
mvn clean && mvn package

build docker image
docker build <project_path> -t java_currency_exchange_app

start docker container
docker run -p 8081:8081 java_currency_exchange_app