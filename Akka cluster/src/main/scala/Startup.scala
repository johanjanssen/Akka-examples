import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

object Startup extends App {
  // Get the hostname from the args
  val host = args(0)
  val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.hostname=$host").withFallback(ConfigFactory.load())

  val actorSystem = ActorSystem.create("ClusterNode", config)

  actorSystem.actorOf(EmptyStringActor.props, "emptystringactor")
}