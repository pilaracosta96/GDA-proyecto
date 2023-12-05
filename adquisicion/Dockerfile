FROM openjdk:17

WORKDIR /adquisicion

COPY target/adquisicion-0.0.1-SNAPSHOT.jar /adquisicion/app.jar
     #target/adquisicion-0.0.1-SNAPSHOT.jar

EXPOSE 8080

EXPOSE 3308

CMD ["java", "-jar", "app.jar"]