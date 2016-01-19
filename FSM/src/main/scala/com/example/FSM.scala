package com.example

import akka.actor.{ ActorRef, Actor, FSM }

sealed trait ProjectStatus
case object NewProject extends ProjectStatus
case object InProgressProject extends ProjectStatus
case object CrappyProject extends ProjectStatus

case object Progress
case object NoProgress

class SignalChangeFSMActor extends Actor with FSM[ProjectStatus, Int] {

  startWith(NewProject, 0)

  when(NewProject) {
    case Event(Progress, 2) =>
      println("Progress Event: Get another job and let someone else create a good application!")
      stay
    case Event(Progress, _) =>
      goto(InProgressProject)
    case Event(NoProgress, _) =>
      println("NoProgress Event: Work harder!")
      stay
  }

  when(InProgressProject) {
    case Event(Progress, _) =>
      goto(CrappyProject)
  }

  when(CrappyProject) {
    case Event(Progress, iteration) =>
      goto(NewProject) using (iteration + 1)
  }

  onTransition {
    case NewProject -> InProgressProject => println("Transition: You're doing a good job!")
    case InProgressProject -> CrappyProject => println("Transition: You're moving into the wrong direction.")
    case CrappyProject -> NewProject => println("Transition: You should use Akka in your new project.")
  }

  initialize
}