import akka.actor.ActorSystem
import akka.cluster.sharding.ShardRegion.{ExtractEntityId, ExtractShardId}
import akka.cluster.sharding.{ClusterSharding, ClusterShardingSettings}
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
    def idExtractor: ExtractEntityId = {
      case i: Int => (i.toString, "")
    }

    def shardResolver(numberOfShards: Int): ExtractShardId = {
      case i: Int => (i % numberOfShards).toString
    }

    ClusterSharding(actorSystem).start(
      "shardName",
      EmptyStringActor.props,
      ClusterShardingSettings(actorSystem),
      idExtractor,
      shardResolver(20)
    )
  }
}
