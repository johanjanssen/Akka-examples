package com.example

import akka.actor._
import com.example.ExamplePersistentActor._

object Startup extends App {
  implicit val system = ActorSystem("ExampleActorSystem")

  val persistentActor = system.actorOf(Props[ExamplePersistentActor])
  persistentActor ! Command("Cobol")
  persistentActor ! Command("Java")
  persistentActor ! ThrowException
  persistentActor ! Command("Scala")

  persistentActor ! NotPersistedCommand("Cobol")
  persistentActor ! NotPersistedCommand("Java")
  persistentActor ! ThrowException
  persistentActor ! NotPersistedCommand("Scala")

  persistentActor ! Print

  Thread.sleep(1000)
  system.terminate()
}

