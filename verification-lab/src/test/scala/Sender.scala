import chisel3._
import chiseltest._

/**
  * A sender BFM for a Handshake interface.
  *
  * @param port the sink port
  * @param clock the clock associated with the sink port
  */
class Sender(port: Handshake, clock: Clock) {

  def send(data: Int, delay: Int = 0): Unit = {

    // add delay cycles
    if (delay > 0) clock.step(delay)

    // apply transaction
    port.data.poke(data.U)
    port.valid.poke(1.B)

    // wait for port to be ready
    while (!port.ready.peekBoolean()) clock.step()
    clock.step()
    port.valid.poke(0.B)
  }
}
