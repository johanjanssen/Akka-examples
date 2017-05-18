package com.example

import akka.actor._

class Worker extends Actor {
  import SharedMessages._

  def receive = {
    case WorkerMessage(body) =>
      println("Worker actor received the message: " + body)
      sender ! WorkerReponseMessage("Item processed successfully")
  }
}
