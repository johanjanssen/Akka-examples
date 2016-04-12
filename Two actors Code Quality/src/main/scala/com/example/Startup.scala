package com.example

import akka.actor._
import com.example.Coordinator._

object Startup extends App {
  implicit val system = ActorSystem("ExampleActorSystem")

  val coordinatorActorRef = system.actorOf(Props[Coordinator])
  coordinatorActorRef ! StartMessage("Hello Conference")
}

