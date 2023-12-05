FROM openjdk17-alpine

RUN mkdir -p /home/adquisicion

COPY . /home/adquisicion
     #target/adquisicion-0.0.1-SNAPSHOT.jar

EXPOSE 8080

EXPOSE 3308

CMD ["java", "-jar", "/home/adquisicionApilcations.jar"]