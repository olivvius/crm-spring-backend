FROM openjdk:18
ADD target/ApiFilRougeCrm-0.0.1-SNAPSHOT.jar ApiFilRougeCrm-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ApiFilRougeCrm-0.0.1-SNAPSHOT.jar"]