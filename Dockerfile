FROM java

ENV SBT_VERSION  0.13.8
ENV SBT_JAR      https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/sbt-launch/$SBT_VERSION/sbt-launch.jar

ADD  $SBT_JAR  /usr/local/bin/sbt-launch.jar  
COPY sbt.sh    /usr/local/bin/sbt

RUN chmod u+x /usr/local/bin/sbt

MAINTAINER Henry Jao henry.jao@grandsys.com

ADD ["util", "build.sbt", "/opt/app/"]
ADD ["project/*", "/opt/app/project/"]

WORKDIR /opt/app

RUN ["sbt", "universal:stage"]


