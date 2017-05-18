package com.example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.{ ActorMaterializer }

object Startup extends App {
  implicit val system = ActorSystem("ExampleActorSystem")

  implicit val executionContext = system.dispatcher
  implicit val materializer = ActorMaterializer()

  val routes = {
    pathPrefix("message") {
      get {
        complete {
          println("Received message with Akka HTTP")
          ""
        }
      }
    }
  }

  Http().bindAndHandle(routes, "0.0.0.0", 8080)
}

