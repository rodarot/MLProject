FROM adoptopenjdk/openjdk15:ubi

COPY target/MLProject-0.0.1-SNAPSHOT.jar app/mlProject.jar

WORKDIR app

CMD ["java", "-jar", "mlProject.jar"]

EXPOSE 8080