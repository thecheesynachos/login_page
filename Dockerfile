FROM openjdk:11
WORKDIR /usr/src/app
COPY . .
EXPOSE 7742
CMD ["java", "-jar", "target/login-webapp-1.0-SNAPSHOT-jar-with-dependencies.jar"]