FROM openjdk:20-jdk-slim
WORKDIR /shows
EXPOSE 8080
COPY target/*.jar /shows/app.jar
CMD java -XX:+UseContainerSupport -jar app.jar
