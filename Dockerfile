FROM openjdk:8-jre-alpine
ADD backend/target/referromizer-backend.jar .
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/referromizer-backend.jar"]