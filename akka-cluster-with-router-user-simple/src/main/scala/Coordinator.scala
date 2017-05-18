import akka.actor.{Actor, Props}
import akka.routing.FromConfig

import scala.concurrent.duration._

object Coordinator {
  case object SendMessage
  def props = Props[Coordinator]
}

class Coordinator extends Actor {
  import Coordinator._
  import context.dispatcher

  val router = context.actorOf(Worker.props.withRouter(FromConfig), "router")
  val messageScheduler = context.system.scheduler.schedule(5.seconds, 1.seconds, self, SendMessage)
  var messageCounter = 0

  override def receive = {
    case SendMessage =>
      router ! "Test message " + messageCounter
      messageCounter +=1
  }
}
