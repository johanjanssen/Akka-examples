import akka.actor.{Actor, RootActorPath}
import akka.cluster.ClusterEvent.MemberUp
import akka.cluster.Cluster

case object RegisterWorker

class Worker extends Actor
{
  val cluster=Cluster(context.system)

  override def preStart(): Unit = cluster.subscribe(self,classOf[MemberUp])
  override def postStop(): Unit = cluster.unsubscribe(self)

  def receive = {
    case MemberUp(node)=>
      println("Member Up with IP: " + node.address.host.get + " and port: " + node.address.port.get)
      context.actorSelection(RootActorPath(node.address)/"user"/"coordinator") ! RegisterWorker
  }
}