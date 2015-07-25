FROM 1science/sbt

MAINTAINER Henry Jao henry.jao@grandsys.com

EXPOSE 7879

COPY ["build.sbt", "/opt/app/"]
COPY ["project", "/opt/app/project"]
COPY ["rest", "/opt/app/rest"]

WORKDIR /opt/app

RUN ["sbt", "compile"]

ENTRYPOINT ["sbt"]
CMD ["rest/run"]