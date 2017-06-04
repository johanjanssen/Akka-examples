package com.example

import akka.persistence._
import com.example.ExamplePersistentActor._

object ExamplePersistentActor {
  case class Command(data: String)
  case class NotPersistedCommand(data: String)
  case class Event(data: String)
  case object ThrowException
  case object Print
}

class ExamplePersistentActor extends PersistentActor {
  override def persistenceId = "ExamplePersistentActor"

  var state = List.empty[String]

  def updateState(event: Event): Unit =
    state = state :+ event.data


  var notPersistedState = List.empty[String]

  def updateNotPersistedState(event: Event): Unit =
    notPersistedState = notPersistedState :+ event.data


  val receiveRecover: Receive = {
    case replayEvent: Event => updateState(replayEvent)
  }

  val receiveCommand: Receive = {
    case Command(data) =>
      persist(Event(data)) { event => updateState(event)
              }
    case NotPersistedCommand(data) =>
      updateNotPersistedState(Event(data))
    case Print =>
      println("Persisted " + state)
      println("Not persisted " + notPersistedState)
    case ThrowException => throw new Exception("This exception was thrown intentionally")
  }
}
