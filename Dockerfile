FROM gradle:jdk11 as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:11-jre-slim
EXPOSE 8080
COPY --from=builder /home/gradle/src/build/libs/dan-chat-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app
CMD ["java", "-jar", "dan-chat-0.0.1-SNAPSHOT.jar"]
