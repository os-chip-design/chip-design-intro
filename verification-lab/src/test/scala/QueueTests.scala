import chisel3._
import chiseltest._
import chiseltest.formal._
import org.scalatest.flatspec.AnyFlatSpec
import scala.util.Random
import chiselverify.coverage._

class FormalWrapper(n: Int, queueFactory: Int => AbstractQueue) extends Module {
  val in = IO(Flipped(new Handshake))
  val out = IO(new Handshake)

  val queue = Module(queueFactory(n))
  in <> queue.in
  out <> queue.out

  // keeps track of the internal queue count
  val cntReg = RegInit(0.U(8.W))
  when(in.fire && !out.fire) {
    cntReg := cntReg + 1.U
  }.elsewhen(!in.fire && out.fire) {
    cntReg := cntReg - 1.U
  }

  // Property: The queue count should not exceed the internal queue size
  assert(cntReg <= n.U, "Queue count should not exceed n")

  // Property: When the queue is full, it should accept no more data
  when(cntReg === n.U) {
    assert(!queue.in.ready, "Queue should be full")
  }

}

abstract class QueueSpec(name: String, queueFactory: Int => AbstractQueue)
    extends AnyFlatSpec
    with ChiselScalatestTester
    with Formal {

  behavior of name

  val N = 8

  it should "pass formal verification" in {
    verify(new FormalWrapper(4, queueFactory), Seq(BoundedCheck(10)))
  }

  it should "perform a single write and read" in {
    test(queueFactory(N)).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
      val sender = new Sender(dut.in, dut.clock)
      val receiver = new Receiver(dut.out, dut.clock)
      val model = new QueueModel(N)

      val magic = Random.nextInt(0xFF)

      sender.send(magic)
      model.send(magic)

      receiver.expect(model.receive())
    }
  }

}

class Queue0Spec extends QueueSpec("Queue0", n => new Queue0(n))
class Queue1Spec extends QueueSpec("Queue1", n => new Queue1(n))
class Queue2Spec extends QueueSpec("Queue2", n => new Queue2(n))
class Queue3Spec extends QueueSpec("Queue3", n => new Queue3(n))
