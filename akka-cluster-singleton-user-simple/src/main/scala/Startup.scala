import akka.actor.ActorSystem

object Startup extends App {
  // Create the Actor System
  val actorSystem = ActorSystem.create("ClusterActorSystem")
  val coordinator = actorSystem.actorOf(Coordinator.props, "coordinator")
}


