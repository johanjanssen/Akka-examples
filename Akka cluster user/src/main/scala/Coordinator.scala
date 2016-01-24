import akka.actor._

case object RegisterWorker

class Coordinator extends Actor {
    def receive = {
    case RegisterWorker =>
      println("Worker registered with IP: " + sender().path.address.host.get + " and port: " + sender().path.address.port.get)
  }
}