import chisel3._
import chisel3.util._
import chisel3.experimental._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

// Define the BlackBox for the Verilog module
class up_down_counter extends BlackBox {
  val io = IO(new Bundle {
    val clk = Input(Bool())
    val reset = Input(Bool())
    val enable = Input(Bool())
    val set = Input(Bool())
    val set_value = Input(UInt(4.W))
    val up_down = Input(Bool())
    val count = Output(UInt(4.W))
  })
}

// Define a wrapper module to connect the BlackBox
class UpDownCounterWrapper extends Module {
  val io = IO(new Bundle {
    val enable = Input(Bool())
    val set = Input(Bool())
    val set_value = Input(UInt(4.W))
    val up_down = Input(Bool())
    val count = Output(UInt(4.W))
  })

  val counter = Module(new up_down_counter)
  counter.io.clk := clock.asBool
  counter.io.reset := reset.asBool
  counter.io.enable := io.enable
  counter.io.set := io.set
  counter.io.set_value := io.set_value
  counter.io.up_down := io.up_down
  io.count := counter.io.count
}

// Testbench using ChiselTest
class UpDownCounterTest extends AnyFlatSpec with ChiselScalatestTester {
  "UpDownCounter" should "count up and down correctly" in {
    test(new UpDownCounterWrapper).withAnnotations(Seq(WriteVcdAnnotation, VerilatorBackendAnnotation)) { c =>
      // Initialize inputs
      c.io.enable.poke(false.B)
      c.io.set.poke(false.B)
      c.io.set_value.poke(0.U)
      c.io.up_down.poke(false.B)

      // Test set functionality
      c.io.set_value.poke(10.U)
      c.io.set.poke(true.B)
      c.clock.step(1)
      c.io.set.poke(false.B)
      c.io.count.expect(10.U)

      // Test enable and up counting
      c.io.enable.poke(true.B)
      c.io.up_down.poke(true.B) // Count up
      c.clock.step(5)
      c.io.count.expect(15.U)

      // Test down counting
      c.io.up_down.poke(false.B) // Count down
      c.clock.step(5)
      c.io.count.expect(10.U)

      // Test disable counting
      c.io.enable.poke(false.B)
      c.clock.step(2)
      c.io.count.expect(10.U)

      // Test reset during operation
      c.reset.poke(true.B)
      c.clock.step(1)
      c.reset.poke(false.B)
      c.io.count.expect(0.U)
      c.io.enable.poke(true.B)
      c.io.up_down.poke(true.B) // Count up
      c.clock.step(5)
      c.io.count.expect(5.U)
    }
  }
}