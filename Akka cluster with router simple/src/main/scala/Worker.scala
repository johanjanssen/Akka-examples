import akka.actor.{Actor, Props}

object Worker {
  def props = Props[Worker]
}

class Worker extends Actor {
  override def receive = {
    case message: String =>
      println(message)
  }
}