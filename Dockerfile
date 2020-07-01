FROM openjdk:14 AS builder
COPY . /usr/src/demo-webflux
WORKDIR /usr/src/demo-webflux
RUN ./mvnw clean package

FROM openjdk:14
COPY --from=builder /usr/src/demo-webflux/target/demo-webflux-1.0.0-SNAPSHOT.jar /usr/src/demo-webflux/
WORKDIR /usr/src/demo-webflux
EXPOSE 8080
CMD ["java", "-jar", "demo-webflux-1.0.0-SNAPSHOT.jar"]
