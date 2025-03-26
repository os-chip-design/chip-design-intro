import chisel3._
import chisel3.util._

class Fifo0(n: Int) extends AbstractFifo(n) {

  val dataReg = Reg(UInt(8.W))
  val validReg = RegInit(0.B)

  in.ready := !validReg
  out.valid := validReg
  out.data := dataReg

  when(in.fire) {
    dataReg := in.data
    validReg := 1.B
  }.elsewhen(out.fire) {
    validReg := 0.B
  }
}
