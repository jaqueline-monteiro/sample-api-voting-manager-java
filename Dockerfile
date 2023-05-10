FROM eclipse-temurin:18-alpine

ADD target/*.jar /opt/app.jar
CMD ["java", "-jar", "/opt/app.jar"]
