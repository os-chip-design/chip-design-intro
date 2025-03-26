import chisel3._
import chiseltest._
import chiseltest.formal._
import org.scalatest.flatspec.AnyFlatSpec
import scala.util.Random

/** This wrapper around a Fifo exposes the interface of the Fifo and does not
  * interfere with it.
  *
  * It allows us to add additional bookkeeping logic which can be used to create
  * assertions.
  *
  * @param n
  *   The size of the internal fifo
  * @param fifoFactory
  *   A factory function to create the internal fifo given a size
  */
class FormalWrapper(n: Int, fifoFactory: Int => AbstractFifo) extends Module {
  val in = IO(Flipped(new Handshake))
  val out = IO(new Handshake)

  // The internal queue
  val fifo = Module(fifoFactory(n))
  in <> fifo.in
  out <> fifo.out

  // keeps track of the internal queue count
  val cntReg = RegInit(0.U(8.W))
  when(in.fire && !out.fire) { // push but not pop
    cntReg := cntReg + 1.U // we gain one element
  }.elsewhen(!in.fire && out.fire) { // pop but not push
    cntReg := cntReg - 1.U // we lose one element
  }

  // Property: The fifo count should not exceed the internal fifo size
  assert(cntReg <= n.U, "Fifo count should not exceed n")

  // Property: When the fifo is full, it should accept no more data
  when(cntReg === n.U) {
    assert(!fifo.in.ready, "Fifo should be full when count is n")
  }

}

/** This is a general test suite for a Fifo. You can add more test scenarios
  * here, or add additional assertions to the `FormalWrapper`.
  *
  * @param name
  *   The name of the Fifo being tested
  * @param fifoFactory
  *   A factory function to create the Fifo given a size
  */
abstract class FifoSpec(name: String, fifoFactory: Int => AbstractFifo)
    extends AnyFlatSpec
    with ChiselScalatestTester
    with Formal {

  behavior of name
  val N = 8 // Fifo size
  def fifoModule =
    new FormalWrapper(N, fifoFactory) // shorthand to create the module

  // This is a formal verification test. It will check the properties of the
  // module for up to 10 cycles.
  it should "pass formal verification" in {
    verify(fifoModule, Seq(BoundedCheck(10)))
  }

  // This is an example simulation test scenario. EXtend this with more
  // or add more test scenarios.
  it should "perform a single write and read" in {
    test(fifoModule).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>
      val sender = new Sender(dut.in, dut.clock)
      val receiver = new Receiver(dut.out, dut.clock)
      val model = new FifoModel(N, dut.clock)

      val magic = Random.nextInt(0xff)

      fork {
        model.send(magic)
        sender.send(magic)
      }.fork {
        receiver.expect(model.receive())
      }.join()

    }
  }

}

// Create the test suite for each Fifo implementation
class Fifo0Spec extends FifoSpec("Fifo0", n => new Fifo0(n))
class Fifo1Spec extends FifoSpec("Fifo1", n => new Fifo1(n))
class Fifo2Spec extends FifoSpec("Fifo2", n => new Fifo2(n))
class Fifo3Spec extends FifoSpec("Fifo3", n => new Fifo3(n))
