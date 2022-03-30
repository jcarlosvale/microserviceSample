Packaging: <br>
```
mvn clean package && java -jar target/sample-microservice-0.0.1-
SNAPSHOT.jar
```

Actuator: <br>
http://localhost:8080/health

Refs:<br>
https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints

Dockerfile build:<br>
```
mvn package dockerfile:build
```

Docker commands
```
docker images
docker run -it -p 8080:8080 study/sample-microservice:0.0.1-SNAPSHOT
docker ps
docker stop <container_id>
```
