package com.example

import akka.actor._

object Startup extends App {
  implicit val system = ActorSystem("ExampleActorSystem")

  val workerActorRef = system.actorOf(Props[Worker])
  workerActorRef ! "Hello conference"
}

