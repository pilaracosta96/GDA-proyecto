FROM maven:3.8.5-openjdk-17

WORKDIR /adquisicion

#COPY . .
COPY target/adquisicion-0.0.1-SNAPSHOT.jar /adquisicion/app.jar
     #target/adquisicion-0.0.1-SNAPSHOT.jar

#ENV MYSQL_ROOT_PASSWORD=12345678
#ENV MYSQL_DATABASE=adquisiciones
#ENV MYSQL_PASSWORD=12345678
#ENV USERNAME_DB=root

#EXPOSE 3308
EXPOSE 8080


CMD ["java", "-jar", "app.jar"]
#CMD mvn spring-boot:run