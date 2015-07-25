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

    val service = system.actorOf(Props[ServiceActor], "services")

    val ioListener = actor("ioListener")(new Act with ActorLogging {
      become {
        case b @ Bound(connection) => log.info(s"bound: $b")
        case CommandFailed(c) => log.error(s"$c")
        case unknown => log.debug(s"unknown: $unknown")
      }
    })

    IO(Http).tell(Http.Bind(service, interface = "0.0.0.0", port = c.port), ioListener)

    sys.addShutdownHook(system.shutdown())


    //println("Rest endpoint starting..")

  }
}
