FROM openjdk:8u181-jdk-stretch as builder
COPY . /src
RUN cd /src && chmod +xrw ./gradlew && ./gradlew assemble

FROM openjdk:8u181-jre-slim
COPY --from=builder /src/build/libs/pc-notifier.jar pc-notifier.jar
ENTRYPOINT ["java",  "-jar","/pc-notifier.jar"]