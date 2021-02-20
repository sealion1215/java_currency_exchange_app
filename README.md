# java_currency_exchange_app

- Conversion API<br/>
GET http://localhost:8081/currencyConversion/{amount}/{currency}<br/>
parameters: <br/>
amount: Number,<br/>
currency: String<br/>
sample output (Success):<br/>
{<br/>
    "rates": {<br/>
        "CAD": 155.10017,<br/>
        "HKD": 953.69330,<br/>
        "ISK": 15766.37285,<br/>
        ...<br/>
    },<br/>
    "base": "USD",<br/>
    "date": "2021-02-19"<br/>
}<br/>
throw errors:<br/>
Internal Server Error<br/>
Invalid Input<br/>

- Exchange Rate History API<br/>
GET http://localhost:8081/rateHistory/{currency}<br/>
parameters: <br/>
currency: String<br/>
sample output (Success):<br/>
{<br/>
    "rates": {<br/>
        "2021-02-01": {<br/>
            "CAD": 1.2805362463,<br/>
            "HKD": 7.752979146,<br/>
            "ISK": 129.3445878848,<br/>
            ...<br/>
        },<br/>
        ...<br/>
    },<br/>
    "start_at": "2021-01-23",<br/>
    "base": "USD",<br/>
    "end_at": "2021-02-21"<br/>
}<br/>
throw errors:<br/>
Internal Server Error<br/>
Invalid Input<br/>

- test<br/>
mvn test<br/>

- build<br/>
mvn clean && mvn package<br/>

- build docker image<br/>
docker build <project_path> -t java_currency_exchange_app<br/>

- start docker container<br/>
docker run -p 8081:8081 java_currency_exchange_app<br/>
