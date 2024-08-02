FROM openjdk:17

ARG DATABASE_URL \
    DATABASE_USER \
    DATABASE_PASSWORD

ENV DATABASE_URL=${DATABASE_URL} \
    DATABASE_USER=${DATABASE_URL} \
    DATABASE_PASSWORD=${DATABASE_PASSWORD}

ARG JAR_FILE=build/libs/*-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]