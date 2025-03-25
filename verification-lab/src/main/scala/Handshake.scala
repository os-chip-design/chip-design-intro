
import chisel3._

class Handshake extends Bundle {
  val valid = Output(Bool())
  val ready = Input(Bool())
  val data = Output(UInt(8.W))

  def fire: Bool = valid && ready
}
