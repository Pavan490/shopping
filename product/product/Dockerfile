FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/product-0.0.1-SNAPSHOT.jar product.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/product.jar"]
