import Dependencies._

lazy val runRest = taskKey[Unit]("Start RESTful api")

lazy val commonSettings = Seq(
  organization := "com.grandsys",
  version := "0.2.0",
  scalaVersion := "2.11.7",
  maintainer in Linux := "henry <henry.jao@grandsys.com>",
  packageSummary in Linux := "I am lazy and left nothing.",
  packageDescription := "I am lazy and left nothing."
) ++ Revolver.settings

lazy val dockerSettings = Seq(
  dockerBaseImage := "1science/java:oracle-jre-8",
  dockerExposedPorts := Seq(7879),
  dockerRepository := Some("jaohaohsuan"),
  packageName in Docker := packageName.value,
  version in Docker := version.value
)

lazy val root = (project in file(".")).
  aggregate(util, rest).
  dependsOn(rest).
  enablePlugins(SbtNativePackager).
  enablePlugins(JavaServerAppPackaging).
  enablePlugins(DockerPlugin).
  settings(commonSettings ++ dockerSettings: _*).
  settings(
    name := "inu-alpha2",
    mainClass in Compile := Some("com.grandsys.rest.Main"),
    fullRunTask(runRest, Compile, "com.grandsys.rest.Main"),
    fork in runRest := true
  )

lazy val rest = project.
  settings(commonSettings: _*).
  settings(
    name := "rest",
    libraryDependencies ++= serviceDeps
  )

lazy val protocols = project.
  settings(commonSettings: _*)

lazy val util = project.
  settings(commonSettings: _*)





