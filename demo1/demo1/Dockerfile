FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/demo1-0.0.1-SNAPSHOT.jar demo1.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/demo1.jar"]
