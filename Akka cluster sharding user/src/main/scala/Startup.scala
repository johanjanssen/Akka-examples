import akka.actor.ActorSystem
import akka.cluster.sharding.ShardRegion._
import akka.cluster.sharding.{ClusterSharding, ClusterShardingSettings}

object Startup extends App {
  // Create the Actor System
  val actorSystem = ActorSystem.create("ClusterNode")

  startSharding()

  actorSystem.actorOf(Coordinator.props, "coordinator")


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


