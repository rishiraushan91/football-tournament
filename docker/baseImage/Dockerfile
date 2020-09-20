FROM openjdk:8-jdk-alpine
RUN sed -i 's/http\:\/\/dl-cdn.alpinelinux.org/https\:\/\/alpine.global.ssl.fastly.net/g' /etc/apk/repositories

RUN rm -rf /var/cache/apk/* && \
    rm -rf /tmp/* && \
    apk update && \
    apk add --no-cache --virtual .build-deps && \
    apk add make bash less groff curl python py-pip && \
    pip install awscli && \
    apk --purge -v del py-pip && \
    rm -rf /var/cache/apk/*


ADD run.sh /usr/local/bin
ARG JAR_FILE=football-tournament-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} football-tournament.jar
CMD ["chmod", "+x", "/usr/local/bin/run.sh"]
ENTRYPOINT ["/usr/local/bin/run.sh"]