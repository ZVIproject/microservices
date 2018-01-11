FROM java:8-jre

ADD ./target/db-service-0.0.1-SNAPSHOT.jar /app/
ADD ./src/main/resources/application.properties /app/
ADD ./src/main/resources/application.yml /app/

CMD java -Dspring.config.location=/app/application.properties,/app/application.yml/ -Ddatabase.hostname=$dbHostname -Dspring.rabbitmq.host=$rabbitHostname -Deureka.url=$eurekaUrl -jar /app/db-service-0.0.1-SNAPSHOT.jar



