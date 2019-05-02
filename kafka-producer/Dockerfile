FROM java:8-jdk-alpine

COPY ./target/kafka-producer-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch kafka-producer-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","kafka-producer-0.0.1-SNAPSHOT.jar"] 