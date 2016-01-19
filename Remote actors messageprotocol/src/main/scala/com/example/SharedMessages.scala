package com.example

object SharedMessages {
  case class StartMessage(body: String)
  case class WorkerReponseMessage(body: String)

  case class WorkerMessage(body: String)
}
