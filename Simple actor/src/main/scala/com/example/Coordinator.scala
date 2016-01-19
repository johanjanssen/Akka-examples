package com.example

import akka.actor.Actor

class Coordinator extends Actor {
  def receive = {
    case x =>
      println(x)
  }
}
