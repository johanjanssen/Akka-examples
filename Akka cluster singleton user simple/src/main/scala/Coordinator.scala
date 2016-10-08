import akka.actor.{PoisonPill, Actor, Props}
import akka.cluster.singleton.{ClusterSingletonProxySettings, ClusterSingletonProxy}
import akka.routing.FromConfig

import scala.concurrent.duration._
import scala.collection.mutable

object Coordinator {
  case object SendMessage
  def props = Props[Coordinator]
}

class Coordinator extends Actor {
  import Coordinator._
  import context.dispatcher

  val singletonActor = context.actorOf(ClusterSingletonProxy.props(
    singletonManagerPath = "/user/singletonactor",
    settings = ClusterSingletonProxySettings(context.system)),
    name = "singletonactorProxy")

  val messageScheduler = context.system.scheduler.schedule(5.seconds, 1.seconds, self, SendMessage)
  var messageCounter = 0

  override def receive = {
    case SendMessage =>
      singletonActor ! "Test message " + messageCounter
      messageCounter += 1

  }
}
