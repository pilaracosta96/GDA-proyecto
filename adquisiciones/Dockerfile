FROM khipu/openjdk17-alpine

WORKDIR /adquisiciones

COPY target/adquisiciones-0.0.1-SNAPSHOT.jar /adquisiciones/app.jar

EXPOSE 8080

EXPOSE 3306

CMD ["java", "-jar", "app.jar"]
