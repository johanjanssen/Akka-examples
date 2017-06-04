import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory

object Startup extends App {
    args.toList match {
      case "--port" :: value :: tail =>
        val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$value").withFallback(ConfigFactory.load())
        val system = ActorSystem("ClusterSystem", config)
        system.actorOf(Props[Coordinator],name="coordinator")
      case _ => println("Supply a port")
    }
}


