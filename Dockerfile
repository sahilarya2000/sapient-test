FROM openjdk:8-alpine
ADD target/*.jar docker-spring-boot.jar
ENTRYPOINT ["java","-jar","docker-spring-boot.jar"]
EXPOSE 8080