---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #fff
backgroundImage: url('https://marp.app/assets/hero-background.svg')
---

<!-- headingDivider: 3 -->

# **Start the Project**

**Martin Schoeberl**

## TODO

 * Explain Wildcat
 * Add submodule and add the instructions for the checkout
 * Explain PipeCon
 * MVP - Wildcat connected to a pin (= LED), hardcoded program, blinking the LED in simulation
   - Does this sound similar to the RISC-V course project?* Ideas
   * Solutions for memory
   * Solutions for RF
   * Multiple tiles (with different memory and/or boot logic)
   * S4NoC and SlimFlit
   * Accellerators

## You are Ready to Start

 * You added a Wishbone interface to Caravel in week 4
 * You tested that interface in week 5
   - Did you?
 * Now we can start the project
 * a MVP running today

 ## The Project

 * We aim for a common project
 * A simple system-on-chip (SoC)
 * With a CPU, memory, and some IO
 * Hosted on GitHub
   * https://github.com/os-chip-design/dtu-soc-2026
   * Send me your GitHub ID to get access
   * Similar to https://github.com/os-chip-design/caravel_leros_2025
 * Do a real tapeout
   * Sponsored by Edu4Chip

## MVP

 * Wildcat connected to a pin (= LED)
 * Hardcoded program
 * Blinking the LED in simulation
 

## Wildcat

 * RISC-V CPU core
 * Open-source
 * Written in Chisel
 * Supports RV32I
 * 3-stage pipeline
 * Simple memory interface (PipeCon)
 * https://github.com/schoeberl/wildcat

## Wildcat Pipeline

![width:1100px](figures/wildcat.svg)

## Wildcat Papers

 * Martin Schoeberl. The Educational RISC-V Microprocessor Wildcat.
Proceedings of the Sixth Workshop on Open-Source EDA Technology (WOSET), 2024. 
 * Martin Schoeberl. Wildcat: Educational RISC-V Microprocessors.
Architecture of Computing Systems -- ARCS 2025, 2025. [pdf](https://www.jopdesign.com/doc/wildcat-arcs.pdf)
  

## The CPU Interface PipeCon

For this project we define a simple pipelined IO interface, that we
name `PipeCon` for pipelined connection.
The interface consisting of following signals:

```scala
class PipeCon(private val addrWidth: Int) extends Bundle {
   val address = Input(UInt(addrWidth.W))
   val rd = Input(Bool())
   val wr = Input(Bool())
   val rdData = Output(UInt(32.W))
   val wrData = Input(UInt(32.W))
   val wrMask = Input(UInt(4.W))
   val ack = Output(Bool())
}
```

## PipeCon

```PipeCon``` itself is an abstract class, just containing the interface:

```scala
abstract class PipeConDevice(addrWidth: Int) extends Module {
   val cpuPort = IO(new PipeConIO(addrWidth))
}
```
## Main Rules Defining PipeCon

 * There are two transactions: read and write
 * The transaction command is valid for a single clock cycle
 * The IO device responds earliest in the following clock cycle with an asserted `ack` signal
 * A read result is valid in the clock cycle `ack` is asserted
 * An IO device can insert wait cycles by asserting `ack` later
 * The CPU may issue a new read or write command in the same cycle `ack` is asserted
 * fits well for pipelined processors, being parallel to the memory stage

## PipeCon Handshake

![width:900px](figures/handshake.svg)