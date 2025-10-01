FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/payment-service-0.0.1-SNAPSHOT.jar payment-service.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/app/payment-service.jar"]
