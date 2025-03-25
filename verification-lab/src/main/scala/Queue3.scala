
import chisel3._

class Queue3(n: Int) extends AbstractQueue(n) {

  val cells = Seq.fill(n) { Module(new Queue0(1)) }
  in <> cells.head.in
  out <> cells.last.out

  for (i <- 0 until n - 1) {
    cells(i).out <> cells(i + 1).in
  }

  val trigger = in.valid && !cells.head.in.ready && cells.last.out.valid && cells(1).in.ready
  when(trigger) {
    cells(1).in.valid := 1.B
    cells(1).in.data := in.data
    cells.head.out.ready := 0.B
    in.ready := 1.B
  }

}
