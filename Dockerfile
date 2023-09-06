FROM openjdk:20-jdk-slim
WORKDIR /shows
EXPOSE 8080
COPY target/*.jar app.jar
CMD java -XX:+UseContainerSupport -jar app.jar
