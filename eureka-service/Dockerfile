FROM java:8-jre

ADD ./target/eureka-service-0.0.1-SNAPSHOT.jar /app/
ADD ./src/main/resources/application.properties /app/

CMD java -jar /app/eureka-service-0.0.1-SNAPSHOT.jar

EXPOSE 8002