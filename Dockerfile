FROM dperezcabrera/openjdk11-alpine AS builder
COPY . /java_currency_exchange_app
WORKDIR /java_currency_exchange_app
RUN mvn package

FROM dperezcabrera/openjdk11-alpine
COPY --from=builder /java_currency_exchange_app/*.jar 

EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]