FROM maven:3.8.5-jdk-11 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
ARG MAVEN_OPTS
RUN --mount=type=cache,target=/root/.m2 mvn -f /usr/src/app/pom.xml clean package


FROM adoptopenjdk/openjdk11:ubi
COPY --from=build /usr/src/app/target/survey-service-1.0.0-SNAPSHOT.jar /usr/app/survey-service-1.0.0-SNAPSHOT.jar
#EXPOSE 9092
ENTRYPOINT ["java","-jar","/usr/app/survey-service-1.0.0-SNAPSHOT.jar"]
