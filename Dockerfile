FROM eclipse-temurin:17-jdk
EXPOSE 8080
RUN mkdir "/app"
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} /app/
ENTRYPOINT ["java","-jar","/app/ci-cd.jar"]
