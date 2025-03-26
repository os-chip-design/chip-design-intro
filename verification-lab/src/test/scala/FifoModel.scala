import chisel3._
import chiseltest._

/** A simple thread-safe FIFO model.
  *
  * When receive is called on an empty Fifo, the model will advance the
  * simulation time until data is available.
  *
  * @param n
  *   the depth of the FIFO
  * @param clock
  *   the clock to use for advancing time
  */
class FifoModel(n: Int, clock: Clock) {

  val inner = new java.util.concurrent.LinkedBlockingQueue[Int](n)

  def send(data: Int): Unit = {
    inner.put(data)
  }

  def receive(): Int = {
    while (inner.isEmpty()) {
      clock.step()
    }
    inner.take()
  }

}
