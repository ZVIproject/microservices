FROM java:8-jre

ADD ./target/balancer-0.0.1-SNAPSHOT.jar /app/
ADD ./src/main/resources/application.properties /app/


CMD java -Dspring.config.location=/app/application.properties -Dservice.get.host=$dbGetHostname -Dservice.get.port=$dbGetPort -Dservice.change.host=$dbChangeHost -Dservice.change.port=$dbChangePort  -jar /app/balancer-0.0.1-SNAPSHOT.jar

EXPOSE 8004