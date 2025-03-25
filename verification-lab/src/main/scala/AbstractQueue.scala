
import chisel3._

abstract class AbstractQueue(val n: Int) extends Module {
  val in = IO(Flipped(new Handshake))
  val out = IO(new Handshake)
}
