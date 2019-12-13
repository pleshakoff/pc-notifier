FROM openjdk:8u181-jre-slim
COPY /build/libs/pc-notifier.jar pc-notifier.jar
ENTRYPOINT ["java",  "-jar","/pc-notifier.jar"]
