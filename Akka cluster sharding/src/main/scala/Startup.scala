import akka.actor.{Props, ActorSystem}
import akka.cluster.sharding.{ClusterShardingSettings, ClusterSharding}
import akka.cluster.sharding.ShardRegion.{ExtractShardId, ExtractEntityId}
import com.typesafe.config.ConfigFactory

object Startup extends App {

  args.toList match {
    case "--port" :: value :: tail =>
      val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$value").withFallback(ConfigFactory.load())
      val system = ActorSystem("ClusterNode", config)
      startSharding(system)
    case _ => println("Supply a port")
  }

  def startSharding(actorSystem: ActorSystem): Unit = {
    val idExtractor: ExtractEntityId = {
      case i: Int => (i.toString, "")
    }

    val maxNumberOfShards = 10

    val shardResolver: ExtractShardId = {
      case i: Int => (i % maxNumberOfShards).toString
    }

    ClusterSharding(actorSystem).start(
      "shardName",
      EmptyStringActor.props,
      ClusterShardingSettings(actorSystem),
      idExtractor,
      shardResolver
    )
  }
}


