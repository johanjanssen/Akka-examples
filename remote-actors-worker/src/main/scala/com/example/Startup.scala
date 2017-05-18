package com.example

import akka.actor._

object Startup extends App {
  implicit val system = ActorSystem("ExampleActorSystem")

  system.actorOf(Props[Worker], name = "workerActor")
}

