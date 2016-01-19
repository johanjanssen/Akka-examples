package com.example

import akka.actor._
import com.example.ExamplePersistentActor._

object Startup extends App {
  implicit val system = ActorSystem("ExampleActorSystem")

  val persistentActor = system.actorOf(Props[ExamplePersistentActor])
  persistentActor ! Command("Cobol")
  persistentActor ! Command("C++")
  persistentActor ! SaveSnapshot
  Thread.sleep(1000) // Without the sleep the snapshots do not work
  persistentActor ! Command("Java")
  persistentActor ! ThrowException
  persistentActor ! Command("Scala")

  persistentActor ! Print

  Thread.sleep(1000)
  system.terminate()
}

