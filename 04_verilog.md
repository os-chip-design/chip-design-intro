---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #fff
backgroundImage: url('https://marp.app/assets/hero-background.svg')
---

<!-- headingDivider: 3 -->

# **Verilog and Tiny Tapeout**

**Martin Schoeberl**


## Verilog Introduction

 * You learned Chisel
 * Verilog is the industry standard 
 * Used by tools as exchange format
 * Old langhuage with a lot of quirks
 * You mainly need to be able to read it
 * And a few lines to connect stuff
 * For serious work: use Chisel

## Verilog vs SystemVerilog

 * Verilog is the old standard
 * SystemVerilog is an extension
   - Adding a lot of features
     - 200+ keywords
   - Mainly for verification (with UVM)
     - E.g., object oriented programming
 * But open-source tools do not fully support SV
 * We stick to plain Verilog

## Verilog Syntax

 * Very C like
   - e.g, has include file
   - defines
 * Module based
 * Ports
 * Wires and regs
 * Always blocks
 * Initial blocks
   - Only for simulation

## Verilog by Examples

 * Will show you some examples
 * First Chisel code then Verilog
 * Again: we only need to be able to read some Verilog
 * It is OK to use LLMs for generating Verilog
   - You *just* need to be able to fix errors ;-)
 * See also the [Verilog Cheat Sheet](https://marceluda.github.io/rp_dummy/EEOF2018/Verilog_Cheat_Sheet.pdf)

## A Chisel Component

```scala
import chisel3._
import chisel3.util._

class ChiselAdder extends Module {
  val io = IO(new Bundle() {
    val a, b = Input(UInt(8.W))
    val sum = Output(UInt(8.W))
  })
  io.sum := io.a + io.b
}
```

## A Verilog Component

```verilog
module adder(
  input  [7:0] a,
  input  [7:0] b,
  output [7:0] sum
);

  assign sum = a + b;

endmodule
```

## Using a Component in Chisel

```scala
  val in1 = Wire(UInt(8.W))
  val in2 = Wire(UInt(8.W))
  val result = Wire(UInt(8.W))

  val m = Module(new ChiselAdder)
  m.io.a := in1
  m.io.b := in2
  result := m.io.sum
```

## Using a Component in Verilog

```verilog
    wire [7:0] in1;
    wire [7:0] in2;
    wire [7:0] result;

    adder m(.a(in1), .b(in2), .sum(result));
```

## Register with Reset and Enable in Chisel

```scala
  val reg = RegEnable(data, 0.U(8.W), enable)
```

## Register with Reset and Enable in Verilog

```verilog
  reg [7:0] reg_data;

  always @(posedge clk) begin
    if (reset)
      reg_data <= 8'b0;
    else if (enable)
      reg_data <= data;
  end
```

## Table Select in Chisel

```scala
  io.out := 0.U
  switch(io.sel) {
    is("b00".U) { io.out := io.in(0) }
    is("b01".U) { io.out := io.in(1) }
    is("b10".U) { io.out := io.in(2) }
    is("b11".U) { io.out := io.in(3) }
  }
```

## Table Select in Verilog

```verilog
module comb(
  input  [1:0] sel,
  input  [3:0] in,
  output reg out
);

  always @(*) begin
    case (sel)
      2'b00: out = in[0];
      2'b01: out = in[1];
      2'b10: out = in[2];
      2'b11: out = in[3];
      default: out = 1'b0;
    endcase
  end

endmodule
```

## If/else if/else in Chisel

```scala
  when (io.c1) {
    io.out := io.in1
  } .elsewhen (io.c2) {
    io.out := io.in2
  } .otherwise {
    io.out := io.in3
  }
``` 

## If/else if/else in Verilog

```verilog
  always @(*) begin
    if (c1)
      out = in1;
    else if (c2)
      out = in2;
    else
      out = in3;
  end
```

## Verilog Assignments

 * Blocking assignments
   - `=` in Verilog
   - Sequential execution (order matters)
   - for combinational logic
 * Non-blocking assignments
   - `<=` in Verilog
   - Parallel execution
   - for sequential logic


## Blocking Assignments

- **Syntax**: `=`
- **Execution**: Executes sequentially, one statement after another
- **Usage**: Typically used in combinational logic

```verilog
initial begin
  a = b + c;
  d = a + e;
end
```
```verilog
assign count = count_reg;
```

## Non-Blocking Assignments
- **Syntax**: `<=`
- **Execution**: Executes concurrently, allowing multiple assignments to occur simultaneously
- **Usage**: Typically used in sequential logic (e.g., within `always` blocks triggered by clock edge)

```verilog
always @(posedge clk) begin
  a <= b + c;
  d <= a + e;
end
```


## reg vs wire

 * `reg` used in `always` blocks
   - Can be a register
   - **But** can also be a wire
   - Depends on always block
 * `wire` is a wire
   - Represents combinational logic or a port
   - Can be assigned
   - Used out of always blocks
   - Connects modules


## Verilog Simulation

 * Two open-source simulators
   - Icarus Verilog
   - Verilator
 * Both part of your OpenLane2 installation
 * Icarus is a bit easier to use

## Simulate Hello World - Demo Time

```verilog
module hello;
  initial
    begin
      $display("Hello, World");
      $finish;
    end
endmodule
```
 Run it with [Icarus Verilog](https://github.com/steveicarus/iverilog):

 ```bash
iverilog hello.v
./a.out
```

## Reading Verilog

 * Examples on the web often not great
 * [YARVI](https://github.com/tommythorn/yarvi) is fine
 * [IBEX](https://github.com/lowRISC/ibex) is a very verbose RISC-V example
 * Maybe check the output from Chisel?

## Multi-Project Wafer (MPW)

 * A waver is expensive
 * Sharing cost on a multi-project wafer (MPW)
 * eFabless offers this service, see [MPW example](https://efabless.com/open_shuttle_program)
   * $ 10000 for a full chip
   * 10 mm2
 * 130 nm
 * Full oppen source PDK
 * Show the chip around

## Tiny Tapeout

 * Tiny Tapeout takes it further down
 * Split the MPW into 512 tiny tiles
   * $ 50 for a tile
   * 100 x 160 um
   * About 1000 gates
   * A tiny 8-bit processor (Lipsi) fits in one tile
 * We can use Tiny Tapeout for projects


 ## Project

 * We aim for a common project
 * A simple system-on-chip (SoC)
 * With a CPU, memory, and some IO
 * Hosted on GitHub
   * https://github.com/os-chip-design/dtu-soc-2025
   * Send me your GitHub ID to get access
 * Do a real tapeout
   * Sponsored by Edu4Chip
 * Show the efabless physical chip

 ## Core Components

 * CPU: [Wildcat RISC-V](https://github.com/schoeberl/wildcat)
 * Cache
 * Memory controller (SPI based Flash and RAM)
 * SPI with switch to *fast* mode

## Peripherals

 * VGA as character display
 * Keyboard
 * Serial port
 * GPIO
 * Timer
 * Accelerator
 * Special IOs (PWM and others)
 * ... your ideas

## Tapeout

 * With efabless and the Open MPW shuttle
   * [ChipIgnite](https://efabless.com/prototyping)
 * Tapeout date: April 21, 2025
 * We can also have experimantal work on Tiny Tapeout

## Workflow

 * All work is in open source
 * We need to cooperate
 * Hosted on GitHub
   - no *private* code copies
   - no branches
 * We use GitHub issues for tasks

 ## Continous Integration (CI)

 * Very common in SW projects
   * Not yet in HW
 * We will do it
 * Catch errors early
 * With GitHub Actions
 * Can we run the tapeout flow on GitHub as part of CI?
   * Tiny Tapeout does the RTL to GDS with GitHub Actions

## Summary

 * Verilog is the standard language for open-source tools
 * In general used in ASIC design
   - VHDL is a bit dead
 * We looked at a bit on Verilog
 * You will need to read Verilog
 * And write just a bit of glue code
 * Explore some Verilog in the lab