FROM redis:latest

MAINTAINER Mehmet ONAR <mhmtonar1@gmail.com>

RUN apt-get update
RUN apt-get install -y gettext-base

COPY redis.conf /etc/redis/redis.conf
COPY start.sh /start.sh

ENTRYPOINT [ "/bin/bash", "/start.sh" ]