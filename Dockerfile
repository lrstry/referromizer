FROM openjdk:8-jdk-alpine
ADD backend/target/referromizer-backend.jar .
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/referromizer-backend.jar"]