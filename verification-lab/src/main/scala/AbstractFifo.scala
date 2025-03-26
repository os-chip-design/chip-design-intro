import chisel3._

/**
  * Interface definition for a Fifo module.
  *
  * @param n the depth of the Fifo
  */
abstract class AbstractFifo(val n: Int) extends Module {
  val in = IO(Flipped(new Handshake))
  val out = IO(new Handshake)
}
