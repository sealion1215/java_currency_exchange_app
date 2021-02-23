FROM dperezcabrera/openjdk11-alpine AS builder
COPY . /java_currency_exchange_app
WORKDIR /java_currency_exchange_app
RUN apk --no-cache add maven && mvn --version
RUN mvn package

FROM dperezcabrera/openjdk11-alpine
COPY --from=builder /java_currency_exchange_app/target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
