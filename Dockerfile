# Stage 1 - Build the JAR
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src/ src/
RUN ./mvnw -B package --file pom.xml

# Stage 2 - Run the JAR
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

ARG JAR_VERSION
COPY --from=build /app/target/CICDAssignment-${JAR_VERSION}.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
