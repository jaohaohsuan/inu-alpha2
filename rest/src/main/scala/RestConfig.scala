package com.grandsys.rest

import com.typesafe.config._
import scopt.OptionParser

case class RestConfig(port: Int = 7879) {

  import ConfigFactory._
  import RestConfig._

  lazy val config = load

  lazy val clusterName = config getString CLUSTER_NAME_PATH
}

object RestConfig {

  private val CLUSTER_NAME_PATH = "clustering.cluster.name"

  def parse(args: Seq[String]): Option[RestConfig] = {

    val parser = new OptionParser[RestConfig]("inu-rest-api") {
      head("inu-rest-api")
      opt[Int]('p',"port") action { (x,c) =>
        c.copy(port = x) } text("Endpoint port")
    }

    parser.parse(args, RestConfig())
  }
}
