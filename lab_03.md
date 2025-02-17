# Lab 3: Verilog and Tiny Tapeout

## Lab Overview

 * Write some Verilog (counter or some of your ideas)
 * Test bench in Chisel
 * Test bench in Verilog
 * Check wave forms
 * Synthesize with OpenLane
 * Run post synthesis tests (optional)
 * Setup Tiny Tapeout
 * Run it with Tiny Tapeout
 * Can we also test the TT post synthesis Verilog?

## Write some Verilog

 * Pick a simple example and write Verilog from scratch
 * Example could be a settable up/down counter with enable
 * But feel free to pick your own example

The following Verilog could be the interface for such a counter:

```verilog
module up_down_counter (
    input wire clk,
    input wire reset,
    input wire enable,
    input wire set,
    input wire [3:0] set_value,
    input wire up_down, // 1 for up, 0 for down
    output wire [3:0] count
);
```

## Test Bench in Chisel

 * Write a ChiselTest test bench (ChiselTest)
 * Use a BlackBox to instantiate the Verilog counter

Define the BlackBox for the Verilog module:
 ```scala
class up_down_counter extends BlackBox with HasBlackBoxResource {
  val io = IO(new Bundle {
    val clk = Input(Bool())
    val reset = Input(Bool())
    val enable = Input(Bool())
    val set = Input(Bool())
...
addResource("up_down_counter.v")
```

Define a wrapper module to connect the BlackBox for easier handling of clock and reset:

```scala
class UpDownCounterWrapper extends Module {
  val io = IO(new Bundle {
    val enable = Input(Bool())
    val set = Input(Bool())

...
  val counter = Module(new up_down_counter)
  counter.io.clk := clock.asBool
  counter.io.reset := reset.asBool
  counter.io.enable := io.enable
...
```
Write a test bench that drives the inputs and checks the outputs with `expect()`. As you test a design in Verilog you need to use a Verilog backend for the tester. E.g., Icarus Verilog or Verilator. Here is an example for Icarus Verilog:

```scala
// Testbench using ChiselTest
class UpDownCounterTest extends AnyFlatSpec with ChiselScalatestTester {
  "UpDownCounter" should "count up and down correctly" in {
    test(new UpDownCounterWrapper).withAnnotations(Seq(WriteVcdAnnotation, VerilatorBackendAnnotation)) { c =>
      // Initialize inputs
      c.io.enable.poke(false.B)
      c.io.set.poke(false.B)
```

This test bench will also generate a VCD file that can be viewed with GTKWave. Check the wave forms to see if your counter works as expected.

## Test Bench in Verilog

 * Write the same (or similar) test bench in Verilog

Here some Verilog snippets useful for the test bench:

```verilog 
`timescale 1ns / 1ps

    // Clock generation
    always #5 clk = ~clk;
```
You can run the test bench with Icarus Verilog:

```bash
iverilog up_down_counter.v tb_counter.v
./a.out
```

To automate the process of running the test bench you can use a Makefile. Here is an example:

```make
test_chisel:
	sbt test

test_verilog:
	iverilog up_down_counter.v tb_counter.v
	./a.out
```

### Missing `assert` in Verilog

Verilog does no support assert for testing (SystemVerilog adds this feature). You can use a simple `if` statement to check the result:

```verilog
    // Check the result
    if (count != 4'h0) begin
        $display("Error: count is %h", count);
        $finish;
    end
```

However, you can also define a macro for `assert`:

```verilog
`define assert(signal, value) \
        if (signal !== value) begin \
            $display("ASSERTION FAILED in %m: signal != value"); \
            $finish; \
        end
```
The %m format specifier will print the module name.

Use your macro as:
```verilog
// Check the result
`assert(count, 4'b0000);
```

## Synthesize with OpenLane

 * Add your Verilog files to the OpenLane flow
 * Run the flow
 * Check the results

## Tiny Tapeout

 * Use the [Verilog template](https://github.com/TinyTapeout/tt10-verilog-template) for Tiny Tapout (TT10)
 * Add your counter and a test for it
 * Edit the needed documentation and configuration files
   * See [docu in Tiny Tapeout](https://tinytapeout.com/guides/workshop/create-your-gds/)
   * But ignore the reference to Wokwi
 * Run the flow with GitHub actions
 * Explore the resuls
   - Reources needed
   - 3D view of your design
   - Look at individual cells in the 3D viewer

## Advaced Stuff

 * Run your test bench on the post synthesis Verilog
   - one from OpenLane2
   - one from Tiny Tapeout