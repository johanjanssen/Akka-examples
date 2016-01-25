import akka.actor.{ActorSystem, Props}
import akka.cluster.sharding.ClusterSharding
import akka.cluster.sharding.ShardRegion._
import com.typesafe.config.ConfigFactory

object Startup extends App {

  args.toList match {
    case "--port" :: value :: tail =>
      val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$value").withFallback(ConfigFactory.load())
      val system = ActorSystem("ClusterNode", config)
      startSharding(system)
      system.actorOf(Props[Coordinator],name="coordinator")
    case _ => println("Supply a port")
  }

  def startSharding(actorSystem: ActorSystem): Unit = {
    def idExtractor: ExtractEntityId = {
      case i: Int => (i.toString, "")
    }

    def shardResolver(numberOfShards: Int): ExtractShardId = {
      case i: Int => (i % numberOfShards).toString
    }

    ClusterSharding(actorSystem).startProxy(
      "shardName",
      None,
      idExtractor,
      shardResolver(20)
    )
  }

}


