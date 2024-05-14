FROM eclipse-temurin:17-jdk
EXPOSE 8080
COPY ./build/libs/your-app.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
