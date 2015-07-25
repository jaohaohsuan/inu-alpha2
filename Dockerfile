FROM 1science/sbt

MAINTAINER Henry Jao henry.jao@grandsys.com

ADD ["build.sbt", "/opt/app/"]
ADD ["project/*", "/opt/app/project/"]

WORKDIR /opt/app

RUN ["sbt", "clean", "test"]


