FROM eclipse-temurin:17-jdk
EXPOSE 8080

RUN pwd
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]