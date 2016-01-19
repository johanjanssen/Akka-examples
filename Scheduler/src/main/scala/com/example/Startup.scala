package com.example

import java.util.Calendar
import akka.actor._
import scala.concurrent.duration._

case object Tick

case object Tock

class ScheduleReceiveActor extends Actor {
  def receive = {
    case Tick =>
      println("Scheduler was activated ONCE at " + Calendar.getInstance().getTime)
    case Tock =>
      println("Scheduler was activated at " + Calendar.getInstance().getTime)
  }
}

object Startup extends App {
  //Use the system's dispatcher as ExecutionContext
  import system.dispatcher

  implicit val system = ActorSystem("ExampleActorSystem")

  val scheduleReceiveActor = system.actorOf(Props[ScheduleReceiveActor])

  system.scheduler.scheduleOnce(1 seconds, scheduleReceiveActor, Tick)
  system.scheduler.schedule(0 seconds, 5 seconds, scheduleReceiveActor, Tock)
}

