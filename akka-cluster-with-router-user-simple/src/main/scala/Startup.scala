import akka.actor.ActorSystem

object Startup extends App {
  val actorSystem = ActorSystem.create("ClusterActorSystem")
  val coordinator = actorSystem.actorOf(Coordinator.props, "coordinator")
}


