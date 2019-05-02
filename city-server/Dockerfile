FROM java:8-jdk-alpine

COPY ./target/city-server-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch city-server-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","city-server-0.0.1-SNAPSHOT.jar"] 