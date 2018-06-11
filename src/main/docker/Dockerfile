FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    XNET_SLEEP=0 \
    JAVA_OPTS=""

# add directly the war
ADD *.war /app.war

EXPOSE 8081
CMD echo "The application will start in ${XNET_SLEEP}s..." && \
    sleep ${XNET_SLEEP} && \
    java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.war
