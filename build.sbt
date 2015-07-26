import Dependencies._

lazy val commonSettings = Seq(
  organization := "com.grandsys",
  version := "0.2.0",
  scalaVersion := "2.11.7",
  resolvers += "spray nightlies repo" at "http://nightlies.spray.io"
) ++ Revolver.settings

lazy val root = (project in file(".")).aggregate(util, rest).
  enablePlugins(JavaAppPackaging).
  settings(
    name := "inu-alpha2"
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