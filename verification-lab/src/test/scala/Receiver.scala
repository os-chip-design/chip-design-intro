
import chisel3._
import chiseltest._

class Receiver(port: Handshake, clock: Clock) {

  def receive(delay: Int = 0): Int = {

    // wait for port to be valid
    while(!port.valid.peekBoolean()) clock.step()

    // add additional delay cycles
    if(delay > 0) clock.step(delay)

    // execute transaction
    val data = port.data.peekInt().toInt
    port.ready.poke(1.B)
    clock.step()
    port.ready.poke(0.B)

    data
  }

  def expect(data: Int, delay: Int = 0): Unit = {
    val received = receive(delay)
    assert(received == data, s"Expected $data, received $received")
  }
}
