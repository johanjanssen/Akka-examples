package sample.akka.testkit

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit, TestActorRef}
import com.example.Worker.WorkerMessage
import com.example.Worker
import com.example.Coordinator.WorkerResponseMessage
import org.scalatest.WordSpecLike

class ImplicitSenderTest extends TestKit(ActorSystem("testSystem"))
  with ImplicitSender // Sets the actor as the sender
  with WordSpecLike {

  "A Worker actor" must {
    "send back a response message" in {
      val actorRef = TestActorRef[Worker]
      actorRef ! WorkerMessage("test")
      expectMsg(WorkerResponseMessage("Item processed successfully"))
    }
  }
}