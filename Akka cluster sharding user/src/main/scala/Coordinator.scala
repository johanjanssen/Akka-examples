import akka.actor.{Actor, PoisonPill, Props}
import akka.cluster.sharding.ClusterSharding

import scala.collection.mutable
import scala.concurrent.duration._
import scala.util.Random

object Coordinator {
  case object Overview
  case object ProcessMessage
  case object TerminateSystem
  def props = Props[Coordinator]
}

class Coordinator extends Actor {
  import Coordinator._
  import context.dispatcher

  val shardingRegion = ClusterSharding(context.system).shardRegion("shardName")

  val messageScheduler = context.system.scheduler.schedule(5.seconds, 10.milliseconds, self, ProcessMessage)
  val overviewScheduler = context.system.scheduler.schedule(5.seconds, 1.seconds, self, Overview)

  val ipAddressCounter = mutable.HashMap.empty[String, Int]

  var messageCounter = 0

  override def receive = {
    case x: String =>
      val senderIp = sender().path.address.port.toString
      val count = ipAddressCounter.getOrElse(senderIp, 0)
      ipAddressCounter += (senderIp -> (count + 1))

    case Overview =>
      println("\nOverview: ")

      ipAddressCounter.toSeq.sortBy(_._1).foreach {
        case (ip, count) =>
          println(s"$ip\t-> $count")
      }
    case ProcessMessage =>
      if (messageCounter == 10000) {
        stopProcessing
      } else {
        val msg: Int = if (Random.nextBoolean())
          Random.nextInt(10)
        else
          Random.nextInt(5)

        shardingRegion ! msg
      }
      messageCounter += 1
    case TerminateSystem =>
      context.system.terminate()
  }

  def stopProcessing: Unit = {
    messageScheduler.cancel()
    overviewScheduler.cancel()
    self ! Overview
    shardingRegion ! PoisonPill
    context.system.scheduler.scheduleOnce(5.seconds, self, TerminateSystem)
  }
}
