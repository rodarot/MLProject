Dockerfile
FROM openjdk:8-jre-alpine

COPY target/mlProject.jar app/mlProject.jar

WORKDIR app

CMD ["java", "-jar", "mlProject.jar"]

EXPOSE 8080