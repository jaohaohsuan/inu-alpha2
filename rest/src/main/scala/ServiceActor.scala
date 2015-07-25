package com.grandsys.rest

import spray.routing.HttpServiceActor
import spray.http.StatusCodes._

class ServiceActor extends HttpServiceActor {

  val default = get {
    pathEndOrSingleSlash {
      complete(OK, "Welcome to use inu-project.")
    }
  }

  def receive = runRoute(default)
}
