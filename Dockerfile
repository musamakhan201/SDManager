FROM azul/zulu-openjdk:8
WORKDIR /app
COPY target/app-0.0.1-SNAPSHOT.jar /app/manager.jar
EXPOSE 8180
CMD ["java", "-jar", "manager.jar"]
