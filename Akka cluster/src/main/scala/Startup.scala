import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory

object Startup extends App {
  args.toList match {
    case "--port" :: value :: tail =>
      val config=ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$value").withFallback(ConfigFactory.load())
      val system=ActorSystem("ClusterSystem", config)
      system.actorOf(Props[Worker],name="worker")
    case "--ip" :: value :: tail =>
      val config=ConfigFactory.parseString(s"akka.remote.netty.tcp.hostname=$value").withFallback(ConfigFactory.load())
      val system=ActorSystem("ClusterSystem", config)
      system.actorOf(Props[Worker],name="worker")
    case _ => println("Supply a port or an IP")
  }
}