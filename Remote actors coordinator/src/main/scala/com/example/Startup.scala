package com.example

import akka.actor._

object Startup extends App {
  import SharedMessages._

  implicit val system = ActorSystem("ExampleActorSystem")

  val coordinatorActorRef = system.actorOf(Props[Coordinator])
  coordinatorActorRef ! StartMessage("Hello Conference")
}

