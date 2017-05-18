package com.example

import akka.actor._
import com.example.Worker._

object Coordinator {
  case class StartMessage(body: String)
  case class WorkerResponseMessage(body: String)
}

class Coordinator extends Actor {
  import Coordinator._

  val workerActorRef = context.actorOf(Props[Worker], "worker")

  def receive = {
    case StartMessage(body) => {
      println("Coordinator actor received start message: " + body)
      workerActorRef ! WorkerMessage("Greetings from the coordinator: " + body)
    }
    case WorkerResponseMessage(body) =>
      println("Coordinator actor received response from worker: " + body)
  }
}
