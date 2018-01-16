FROM java:8-jre

ADD ./target/statistic-service-0.0.1-SNAPSHOT.jar /app/
ADD ./src/main/resources/application.properties /app/
ADD ./src/main/resources/application.yml /app/

CMD java -Dspring.config.location=/app/application.properties,/app/application.yml/ -Dspring.rabbitmq.host=$rabbitHostname -Deureka.url=$eurekaUrl -jar /app/statistic-service-0.0.1-SNAPSHOT.jar

EXPOSE 8003