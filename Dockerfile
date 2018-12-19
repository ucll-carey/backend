#FROM gcr.io/distroless/java AS build
#FROM openjdk:8-jdk-alpine AS build
#FROM gradle:4.10.2-slim AS build
FROM gradle:5.0-alpine AS build

ENV APP_HOME=/app
USER root
RUN mkdir -p $APP_HOME/
WORKDIR $APP_HOME

# download dependencies
COPY build.gradle settings.gradle ./
RUN gradle resolveDependencies

# compile
COPY src ./src
RUN gradle build

# Based on https://github.com/GoogleContainerTools/distroless/blob/master/examples/java/Dockerfile
FROM gcr.io/distroless/java
USER root
COPY --from=build /app/build/libs/cityquest-0.0.1-SNAPSHOT.war /
WORKDIR /
CMD ["cityquest-0.0.1-SNAPSHOT.war"]
