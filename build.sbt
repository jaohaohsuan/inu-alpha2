lazy val commonSettings = Seq(
  organization := "com.grandsys",
  version := "0.2.0",
  scalaVersion := "2.11.7"
)

lazy val root = (project in file(".")).aggregate(util).
  enablePlugins(JavaAppPackaging).
  enablePlugins(DockerPlugin).
  settings(
    name := "inu-alpha2",
    maintainer := "Henry Jao <henry.jao@grandsys.com>",
    packageName in Docker := packageName.value,
    dockerRepository := Some("jaohaohsuan"),
    dockerBaseImage := "java:openjdk-7-jdk"
  )

lazy val util = (project in file("util")).
  settings(commonSettings: _*)
