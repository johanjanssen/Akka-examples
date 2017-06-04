import akka.actor.{PoisonPill, ActorSystem}
import akka.cluster.singleton.{ClusterSingletonManagerSettings, ClusterSingletonManager}
import com.typesafe.config.ConfigFactory

object Startup extends App {
  // Get the hostname from the args
  val host = args(0)
  val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.hostname=$host").withFallback(ConfigFactory.load())

  val actorSystem = ActorSystem.create("ClusterNode", config)

  actorSystem.actorOf(EmptyStringActor.props, "emptystringactor")

  actorSystem.actorOf(ClusterSingletonManager.props(
    singletonProps = EmptyStringActor.props,
    terminationMessage = PoisonPill,
    settings = ClusterSingletonManagerSettings(actorSystem)),
    name = "singletonactor")
}