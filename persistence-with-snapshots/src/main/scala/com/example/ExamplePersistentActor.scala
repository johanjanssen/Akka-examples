package com.example

import akka.persistence._
import com.example.ExamplePersistentActor._

object ExamplePersistentActor {
  case class Command(data: String)
  case class NotPersistedCommand(data: String)
  case class Event(data: String)
  case object ThrowException
  case object SaveSnapshot
  case object Print
}

class ExamplePersistentActor extends PersistentActor {
  override def persistenceId = "ExamplePersistentActorWithSnapshots"

  var state = List.empty[String]

  def updateState(event: Event): Unit =
    state = state :+ event.data

  val receiveRecover: Receive = {
    case replayEvent: Event =>
      println("Replayevent contains: " + replayEvent)
      updateState(replayEvent)
    case SnapshotOffer(_, s: List[String]) =>
      println("Snapshot contains: " + s)
      state = s
  }

  val receiveCommand: Receive = {
    case Command(data) =>
      persist(Event(data)) { event => updateState(event)
              }
    case SaveSnapshot =>  saveSnapshot(state)
    case Print =>
      println("Persisted " + state)
    case ThrowException => throw new Exception("This exception was thrown intentionally")
  }
}
