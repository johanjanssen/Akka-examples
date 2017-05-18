import akka.actor.{Actor, Props}

object EmptyStringActor {
  def props = Props[EmptyStringActor]
}

class EmptyStringActor extends Actor {
  override def receive = {
    case message: String =>
      sender ! ""
  }
}