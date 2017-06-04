package com.example

import akka.actor._

object Startup extends App {
  implicit val system = ActorSystem("ExampleActorSystem")

  val coordinatorActorRef = system.actorOf(Props[SignalChangeFSMActor])
  coordinatorActorRef ! NoProgress
  coordinatorActorRef ! Progress
  coordinatorActorRef ! Progress
  coordinatorActorRef ! Progress
  coordinatorActorRef ! Progress
  coordinatorActorRef ! Progress
  coordinatorActorRef ! Progress
  coordinatorActorRef ! Progress
}

