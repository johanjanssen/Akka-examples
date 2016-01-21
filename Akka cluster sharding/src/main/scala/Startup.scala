import akka.actor.ActorSystem
import akka.cluster.sharding.{ClusterShardingSettings, ClusterSharding}
import akka.cluster.sharding.ShardRegion.{ExtractShardId, ExtractEntityId}
import com.typesafe.config.ConfigFactory

object Startup extends App {
  // Get the hostname from the args
  val host = args(0)
  val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.hostname=$host").withFallback(ConfigFactory.load())
  val actorSystem = ActorSystem.create("ClusterNode", config)

  startSharding()

  def startSharding(): Unit = {
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


