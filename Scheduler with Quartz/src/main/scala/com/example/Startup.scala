package com.example

import java.util.Calendar
import akka.actor._
import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension

case object Tick

class ScheduleReceiveActor extends Actor with ActorLogging{
  def receive = {
    case x =>
      log.info("Quartz scheduler was activated at " + Calendar.getInstance().getTime)
  }
}

object Startup extends App {
  implicit val system = ActorSystem("ExampleActorSystem")

  val scheduleReceiveActor = system.actorOf(Props[ScheduleReceiveActor])

  QuartzSchedulerExtension(system).schedule("SCHEDULE1", scheduleReceiveActor, Tick)
}

