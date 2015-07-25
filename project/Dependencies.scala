import sbt._

object Version {
  val akka = "2.3.+"
  val spray = "1.3.3"
}

object Library {
  val akkaActor            = "com.typesafe.akka"      %% "akka-actor"                    % Version.akka
  val akkaSlf4j            = "com.typesafe.akka"      %% "akka-slf4j"                    % Version.akka
  val akkaPersistence      = "com.typesafe.akka"      %% "akka-persistence-experimental" % Version.akka
  val akkaCluster          = "com.typesafe.akka"      %% "akka-cluster"                  % Version.akka
  val akkaContrib          = "com.typesafe.akka"      %% "akka-contrib"                  % Version.akka
  val spray                = "io.spray"               %% "spray-can"                     % Version.spray
  val sprayRouting         = "io.spray"               %% "spray-routing"                 % Version.spray
  val logback              = "ch.qos.logback"         %  "logback-classic"               % "1.1.+"
  val sigar                = "org.fusesource"         %  "sigar"                         % "1.6.4"
  val scopt                = "com.github.scopt"       %% "scopt"                         % "3.3.0"
}

object Dependencies {

  import Library._

  val serviceDeps = Seq(
    akkaActor,
    akkaSlf4j,
    logback,
    spray,
    sprayRouting,
    scopt
  )
}