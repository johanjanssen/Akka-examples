import akka.actor.{PoisonPill, ActorSystem}
import akka.cluster.singleton.{ClusterSingletonManagerSettings, ClusterSingletonManager}
import com.typesafe.config.ConfigFactory

object Startup extends App {
  // Get the port from the args
  val port = args(0)
  val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port").withFallback(ConfigFactory.load())

  val actorSystem = ActorSystem.create("ClusterActorSystem", config)

  actorSystem.actorOf(ClusterSingletonManager.props(
    singletonProps = Worker.props,
    terminationMessage = PoisonPill,
    settings = ClusterSingletonManagerSettings(actorSystem)),
    name = "singletonactor")
}