FROM openjdk:17-alpine

WORKDIR /gateway

COPY target/*.jar app.jar

EXPOSE 8080

CMD ["java","-jar","app.jar"]