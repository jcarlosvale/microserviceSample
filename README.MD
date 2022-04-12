Packaging: <br>
```
mvn clean package && java -jar target/sample-microservice-0.0.1-
SNAPSHOT.jar
```

Actuator: <br>
http://localhost:8080/health

Refs:<br>
https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints
https://github.com/ihuaylupo/manning-smia/tree/master/

#DOCKER

##Option 1
Dockerfile build:<br>
```
mvn package dockerfile:build
```

##Option 2
Using Buildpack
``` 
mvn spring-boot:build-image
```

##Option 3
LAYERED JARS - Not implemented here

Steps:
1. Add the layer configuration into the pom.xml
```xml
<plugin>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-maven-plugin</artifactId>
<configuration>
<layers>
<enabled>true</enabled>
</layers>
</configuration>
</plugin>
```
2. Package the application.
```
mvn clean package
```
3. Execute the jarmode system property with the
   layertools jar mode
```
java -Djarmode=layertools -jar target/licensing-service-0.0.1-
SNAPSHOT.jar list
```

result similar to:
```
dependencies
spring-boot-loader
snapshot-dependencies
application
```
4. Create the Dockerfile.
```dockerfile
FROM openjdk:11-slim as build
WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract
FROM openjdk:11-slim
WORKDIR application
COPY --from=build application/dependencies/ ./ 
COPY --from=build application/spring-boot-loader/ ./ 
COPY --from=build application/snapshot-dependencies/ ./ 
COPY --from=build application/application/ ./ 
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
```
5. Build and run the image.
```
docker build . --tag sample-microservice
docker run -it -p8080:8080 sample-microservice:latest
```

###Docker commands
```
docker images
docker run -it -p 8080:8080 study/sample-microservice:0.0.1-SNAPSHOT
docker ps
docker stop <container_id>
```

Docker compose
```
docker-compose up
```

Running app:
http://localhost:8080/v1/organization/123/license/456