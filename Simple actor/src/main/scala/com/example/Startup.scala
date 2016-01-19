package com.example

import akka.actor._

object Startup extends App {
  implicit val system = ActorSystem("ExampleActorSystem")

  val coordinatorActorRef = system.actorOf(Props[Coordinator])
  coordinatorActorRef ! "Hello conference"
}

