package com.example

import akka.actor.Actor

class Worker extends Actor {
  def receive = {
    case x =>
      println(x)
  }
}
