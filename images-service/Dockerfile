FROM maven:3.8.5-openjdk-17 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
ARG MAVEN_OPTS
RUN --mount=type=cache,target=/root/.m2 mvn -f /usr/src/app/pom.xml clean package


FROM openjdk:17
COPY --from=build /usr/src/app/target/images-service-1.0.0-SNAPSHOT.jar /usr/app/images-service-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/usr/app/images-service-1.0.0-SNAPSHOT.jar"]
