package com.grandsys.rest

import spray.can.Http
import akka.io.IO
import akka.actor.ActorDSL._
import akka.actor.{ActorLogging, ActorSystem, Props}
import akka.io.Tcp._

object Main extends App {

  val restConfig = RestConfig parse args

  restConfig map { c =>

    implicit val system = ActorSystem(c.clusterName, c.config)

    system.registerOnTermination {
      system.log.info(s"${c.clusterName} actors shutting down")
    }

    val service = system.actorOf(Props[ServiceActor], "spray-services")

    val callbackActor = actor("ioListener")(new Act with ActorLogging {
      become {
        case b @ Bound(connection) => log.info(s"bound: $b")
        case CommandFailed(cmd) =>
          log.error(s"Unable to bind to port ${c.port} on interface 0.0.0.0 $cmd")
          system.shutdown()
        case all => log.debug(s"spray-services received a message from akka.IO: $all")
      }
    })

    IO(Http).tell(Http.Bind(service, interface = "0.0.0.0", port = c.port), callbackActor)

    sys.addShutdownHook(system.shutdown())
  }
}
