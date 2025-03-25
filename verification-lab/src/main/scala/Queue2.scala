
import circt.stage.ChiselStage
import chisel3._
import chisel3.util._


class Queue2(n: Int) extends AbstractQueue(n) {
  
  val queue = Module(new Queue(UInt(8.W), n))
  in.ready := queue.io.enq.ready
  queue.io.enq.valid := in.valid
  queue.io.enq.bits := in.data

  out.valid := queue.io.deq.valid
  queue.io.deq.ready := out.ready
  out.data := queue.io.deq.bits

}
