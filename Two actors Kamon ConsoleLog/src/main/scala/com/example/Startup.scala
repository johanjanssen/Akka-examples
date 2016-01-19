package com.example

import akka.actor._
import com.example.Coordinator._
import kamon.Kamon

object Startup extends App {
  Kamon.start()
  implicit val system = ActorSystem("ExampleActorSystem")


  val coordinatorActorRef = system.actorOf(Props[Coordinator], "coordinator")
  coordinatorActorRef ! StartMessage("Hello Conference")
}

