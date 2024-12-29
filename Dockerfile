ARG BASE_IMAGE_REGISTRY=docker.io
# Set the default active Spring profile

FROM ${BASE_IMAGE_REGISTRY}/openjdk:17-jdk-slim

WORKDIR /app
VOLUME /tmp

RUN groupadd -r javauser && useradd -r -g javauser javauser

COPY target/*.jar app.jar

EXPOSE 8080

#metrics port
EXPOSE 8081

RUN mkdir -p /app/data && \
    mkdir -p /app/config && \
    chown -R javauser:javauser /app

VOLUME /app/data
VOLUME /app/config

USER javauser

ARG SPRING_PROFILES_ACTIVE=prod
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}
#ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "app.jar"]
ENTRYPOINT ["java", "-cp", "/app/data:app.jar", "org.springframework.boot.loader.launch.JarLauncher", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}"]
