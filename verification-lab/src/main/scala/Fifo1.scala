import chisel3._
import chisel3.util._

class Fifo1(n: Int) extends AbstractFifo(n) {

  val queue = Module(new Queue(UInt(8.W), n, flow = true))
  in.ready := queue.io.enq.ready
  queue.io.enq.valid := in.valid
  queue.io.enq.bits := in.data

  out.valid := queue.io.deq.valid
  queue.io.deq.ready := out.ready
  out.data := queue.io.deq.bits

}
