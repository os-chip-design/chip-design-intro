import chisel3._

/** A simple handshake interface.
  *
  * `def fire` can be used to check whether a transaction is occurring.
  */
class Handshake extends Bundle {
  val valid = Output(Bool())
  val ready = Input(Bool())
  val data = Output(UInt(8.W))

  def fire: Bool = valid && ready
}
