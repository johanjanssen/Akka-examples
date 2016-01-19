package com.example

import akka.actor._
import com.example.Coordinator._

object Worker {
  case class WorkerMessage(body: String)
}

class Worker extends Actor with ActorLogging {
  import Worker._

  def receive = {
    case WorkerMessage(body) =>
      log.info("Worker actor received the message: " + body)
      sender ! WorkerResponseMessage("Item processed successfully")
  }
}
