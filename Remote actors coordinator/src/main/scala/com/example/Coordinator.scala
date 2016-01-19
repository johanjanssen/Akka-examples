package com.example

import akka.actor._

class Coordinator extends Actor {
  import SharedMessages._

  def receive = {
    case StartMessage(body) => {
      println("Coordinator actor received start message: " + body)
      val workerActorRef = context.actorSelection("akka.tcp://ExampleActorSystem@127.0.0.1:9005/user/workerActor")
      workerActorRef ! WorkerMessage("Greetings from the coordinator: " + body)
    }
    case WorkerReponseMessage(body) =>
      println("Coordinator actor received response from worker: " + body)
  }
}
