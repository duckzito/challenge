FROM maven:3.6.3-openjdk-11-slim as builder
WORKDIR /project
ADD ./ .

RUN mvn -q clean package -Dmaven.test.skip=true

#FROM adoptopenjdk/openjdk11:alpine-jre

#M1 compatible
FROM bellsoft/liberica-openjdk-alpine-musl:17


COPY --from=builder project/target/app.jar ./app.jar

RUN apk --no-cache add curl unzip jq bash\
    && addgroup -S appgroup \
    && adduser -S appuser -G appgroup \
    && chmod -R 777 /var/log/

USER appuser

CMD ["java","-Xshare:off", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]