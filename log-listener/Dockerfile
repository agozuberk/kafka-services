FROM java:8-jdk-alpine

COPY ./target/log-listener-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch log-listener-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","log-listener-0.0.1-SNAPSHOT.jar"] 