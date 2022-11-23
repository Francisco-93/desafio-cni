FROM adoptopenjdk/openjdk8:ubi
ARG JAR_FILE=target/prod-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
CMD java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080

# docker build -t produtividade-app-backend .
# docker run -p 8080:8080 produtividade-app-backend