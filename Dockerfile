FROM gradle:8.10.1-jdk21-alpine AS builder

WORKDIR /app

COPY build.gradle settings.gradle ./

COPY src/ ./src/

RUN gradle build --no-daemon -x test

FROM amazoncorretto:21-alpine3.20-jdk

WORKDIR /app

ENV SPRING_PROFILES_ACTIVE='pdn'

COPY --from=builder /app/build/libs/epl-notifications-manager-1.0.5.jar ./epl-notifications-manager-1.0.5.jar

EXPOSE 3020

CMD ["java", "-jar", "epl-notifications-manager-1.0.5.jar"]