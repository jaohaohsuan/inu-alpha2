FROM 1science/sbt

MAINTAINER Henry Jao henry.jao@grandsys.com

EXPOSE [7879]

ADD ["build.sbt", "/opt/app/"]
ADD ["project/*", "/opt/app/project/"]

WORKDIR /opt/app

RUN ["sbt", "clean", "test"]

ENTRYPOINT ["sbt"]
CMD ["rest/run"]


