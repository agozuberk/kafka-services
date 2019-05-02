FROM java:8-jdk-alpine

COPY ./target/kafka-consumer-to-console-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch kafka-consumer-to-console-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","kafka-consumer-to-console-0.0.1-SNAPSHOT.jar"] 