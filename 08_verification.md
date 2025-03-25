---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #fff
---

<!-- headingDivider: 3 -->

# **Functional Verification**

**Tjark Petersen**

## Motivation

- Computer chips are everywhere in our daily lives
- Their complexity is increasing
- But how can we be confident that everything works as it should?
- We better be sure, since a tape-out can cost millions!
- We have to verify our design
- It does not add any value but it is a necessary task


## Lecture Overview
- A journey from a bare-bones Verilog/VHDL testbench to modern verification methodologies
- A First-In-First-Out buffer will serve as an example to showcase different verification techniques
```scala
class Handshake extends Bundle {
  val valid = Output(Bool())
  val ready = Input(Bool())
  val data = Output(UInt(8.W))
}
```

![bg right:40% width:100%](figures/L8/fifo.svg)


## What is Verification
- *"Verification is a process used to demonstrate the functional correctness of a design"* [Bergeron, 2012]
- What is *correct*? We need a *specification*
- But everyone *interprets* the specification
- We need to be careful to avoid ambiguities and false assumptions
- To demonstrate correctness we need to know:
  - What to test
  - And how to test it

## Scenarios & Properties
- We need a translation of features and requirements into something more tangible
- A *scenario* captures how a certain feature may be *triggered* and what the expected behavior should be
- A *property* captures system behavior in a mathematical way involving signal states and possibly time
  - Safety properties ensure that something bad *never* happends
  - Liveness properties ensure that something good *eventually* happens

## The Verification Plan
- A complete *verification plan* is the collection of scenarios and properties which collectively *demonstrate* the functional correctness of the design
- This means that for each feature there should be a scenario or property that can reveil whether the feature is implemented correctly or not
- A complete verification plan *covers* all aspects of the specification
- Writing a complete verification plan is hard, time-consuming and difficult to automate
  - When do you know you are done?

## What is a feature?
- A *capability* of a design and how it should work under a certain *mode of operation*
- A *functionality* of a design which captures how an *operation* may be performed

## FIFO Specification

- **Functionality**:
  - A read/write occurs when ready and valid are asserted in the same clock cycle
  - Writes to the FIFO should be read in the same order
  - Reads should only occur when the FIFO is non-empty
  - Writes should only occur when the FIFO is not full
- **Reset**: After reset the FIFO should be empty

---

- **Performance**:
  - If not full, the FIFO should accept new writes each clock cycle
  - If non-empty, the FIFO should accept reads each clock cycle
  - An item written to an empty FIFO should be available for reading on the output at the next clock cycle


## Features

- **Functionality**:
  - A write handshake places an item in the FIFO
  - A read handshake removes an item from the FIFO
  - Data written to the FIFO should eventually be available for reading
  - A data item written to the FIFO should be available for reading *before* all items written *after* it
  - Writes must not occur when *N* items are in the FIFO (`!in.ready`)
  - Reads must not occur when 0 items are in the FIFO (`!out.valid`)
---
- **Reset**: The FIFO should signal that it is empty (`!out.valid`) after reset
- **Performance**:
  - If not full, the FIFO must assert `in.ready`
  - If non-empty, the FIFO must assert `out.valid`
  - Writing to an empty FIFO should result in `out.valid` being asserted the next clock cycle and the `out.data` being the same as the written data

## Scenarios - Example 1

- Let's develope a scenario for the feature: "Writes must not occur when *N* items are in the FIFO (`!in.ready`)"
- Scenario:
  - Insert *N* items into the FIFO
  - Now `in.ready` should be deasserted
- Is it exhaustive though? What about (add *N*, read 1, add 1)?
- We could decide based on some assumed knowledge of the design

## Scenarios - Example 2

- Let's develope a scenario for the feature: "A data item written to the FIFO should be available for reading *before* all items written *after* it"
- Scenario:
  - Write *N* items to the FIFO with `in.data` being the item number
  - Read *N* items from the FIFO and check that the `out.data` is the same as the item number 

## Now what?

- We have test scenarios, now we need to apply them to the design
- We need to write a *testbench*
- A testbench creates an environment around a design which drives the inputs of a design and may check the outputs
- The testbench may also produce a waveform for visual inspection

## Traditional Testbench

- Traditionally testbenches have been written in a hardware description language (Verilog, VHDL)
- One simulator could be used for both design and testbench
- The testbench is a module without any ports and drives the design inputs
- The testbench may contain non-synthesizable constructs

![bg right:35% width:90%](figures/L8/trad_tb.svg)

## FIFO Verilog Testbench

```verilog
module tb;
  ... // create dut instance and wires

  always #5 clock = ~clock;                                                                                                

  initial begin
    ... // reset

    // Step 1: Write an item to FIFO
    in_data = 8'hA5; in_valid = 1;
    wait (in_ready);
    @(posedge clock); in_valid = 0;

    // Step 2: Read the item from FIFO
    out_ready = 1;
    wait (out_valid);

    if (out_data == 8'hA5) 
      $display("TEST PASSED: Correct data read from FIFO.");
    else 
      $display("TEST FAILED: Incorrect data read from FIFO.");
    #20 $finish;
  end
endmodule
```

## Traditional Testbenches
- Pros:
  - Same language as the design
  - Inherently concurrent
- Cons:
  - Manual alignment to clock edges
  - Need to declare explicit signals to connect to DUT
  - Very limited language

## Co-simulation
- Writing the testbench in an HDL and simulating everything together is not the only way
- A different language can also interface with a running simulation, allowing to read and write signals
- This is called *co-simulation*
- We can use a higher-level language to drive the simulation

## Verification Languages
- The idea of using a higher-level language for verification through co-simulation is not new
- In the mid-90s, Verilog's limitations became apparent with the increasing complexity of designs 
- People sought to increase the verification capabilities of Verilog
- This led to the development of hardware verification languages
- E.g. *Vera* uses co-simulation (originally developed at Sun Microsystems in 1994)
- It added object-oriented programming, and more domain specific language constructs [Flake, 2020]

## OpenVera Example
- Inspired by [Flake, 2020]
```verilog
program my_test {
  interface channel {
    input       clock   CLOCK;
    output      reset   PHOLD #1; // single-bit signal driven 1 time-unit after the rising clock edge
    input [7:0] data    PSAMPLE #1; // 8-bit signal sampled 1 time-unit before the rising clock edge
    input       request PSAMPLE; // signal sampled infinitesimal time before the rising clock edge
    output      ack     NHOLD #2; // signal driven 2 time-units after the falling clock edge
  }
  { // Program entry point
    channel.request = 1 ;
    @0,100 channel.ack == 1 ; // expect ack to be asserted within 100 clock cycles
  }
}
```

## Cocotb
- Co-simulation framework in Python
```python
@cocotb.test()
async def fifo_single_write_read_test(dut):
  cocotb.start_soon(Clock(dut.clk, 10, units="ns").start())                                                                    
  ... # Reset

  # Step 1: Write one item into the FIFO
  dut.in_data.value = 0xA5
  dut.in_valid.value = 1
  while not dut.in_ready.value:
      await RisingEdge(dut.clk)
  await RisingEdge(dut.clk)
  dut.in_valid.value = 0

  # Step 2: Read the item from FIFO
  dut.out_ready.value = 1
  while not dut.out_valid.value:
      await RisingEdge(dut.clk)
  read_value = dut.out_data.value.integer

  assert read_value == 0xA5, f"TEST FAILED: Expected 0xA5, got {hex(read_value)}"
  cocotb.log.info("TEST PASSED: Correct data read from FIFO.")
```

## Chiseltest
- Co-simulation framework in Scala for Chisel hardware designs
- Automatic reset based on known reset signal
- Automatic clock alignment to falling edge using `step` method
```scala
test(new Fifo) { dut =>
  // Step 1: Write an item to FIFO
  dut.in.valid.poke(1.B)
  dut.in.data.poke(0xA5.U)
  while(!dut.in.ready.peekBoolean()) dut.clock.step()                                                     
  dut.clock.step()
  dut.in.valid.poke(0.B)

  // Step 2: Read the item from FIFO
  dut.out.ready.poke(1.B)
  while(!dut.out.valid.peekBoolean()) dut.clock.step()
  dut.out.data.expect(0xA5.U)
}
```

## Introducing Abstraction
- What if we have to insert 100 items into the FIFO?
- Copy and paste the same code 100 times? I've seen it done!
- We need to abstract the interaction with the DUT
- We can use so-called *Bus Functional Models* (BFM)
- A BFM provides functions which take the parameters of an interaction and take care of the low level timing and signal manipulation

## Sender BFM
- A BFM for driving an input port in Scala
```scala
class SenderBFM(port: Handshake, clock: Clock) {
  def send(data: BigInt): Unit = {
    port.valid.poke(1.B)
    port.data.poke(data.U)
    while(!port.ready.peekBoolean()) clock.step()                                           
    clock.step()
    port.valid.poke(0.B)
  }
  def send(data: Seq[BigInt]): Unit = {
    data.foreach(d => send(d))
  }
}
```

## Receiver BFM
- A BFM for reading an output port in Scala
```scala
class ReceiverBFM(port: Handshake, clock: Clock) {
  def receive(): BigInt = {
    port.ready.poke(1.B)
    while(!port.valid.peekBoolean()) clock.step()
    val data = port.data.peekInt()
    clock.step()
    port.ready.poke(0.B)
    data
  }
  def expect(data: BigInt): Unit = {
    val received = receive()
    assert(received == data, s"Expected $data, got $received")                                   
  }
}
```

## Testbench with BFM
- The BFM allows us to write the testbench at a higher level of abstraction, thinking of *transactions*
- A transaction encapsulates all data needed for a single interaction on an interface

```scala
test(new Fifo) { dut =>
  val sender = new SenderBFM(dut.in, dut.clock)
  val receiver = new ReceiverBFM(dut.out, dut.clock)                                                

  sender.send(0xA5)
  receiver.expect(0xA5)
}
```
## Extended Transaction Example
- Actually in this case there is more than just the data: the delay between handshakes is also important

```scala
test(new Fifo) { dut =>
  val sender = new SenderBFM(dut.in, dut.clock)
  val receiver = new ReceiverBFM(dut.out, dut.clock)                                                

  sender.send(0xA5, delay = 5) // wait 5 clock cycles and then send 0xA5
  receiver.expect(0xA5)
}
```

## Automating Scenarios
- There were many features of the FIFO with even more scenarios to create test cases for
- Do we have to do it all by hand?
- If we apply random stimuli to our design, *some of the scenarios we want to test may occur naturally*!
- We need a model of the design to be able to check if the design behaves correctly


## FIFO Random Example

```scala
test(new Fifo) { dut =>
  val sender = new SenderBFM(dut.in, dut.clock)
  val receiver = new ReceiverBFM(dut.out, dut.clock)                                                   
  val model = new FifoModel

  fork {
    for (_ <- 0 until 100) {
      val data = Random.nextInt(256)
      val delay = Random.nextInt(10)
      sender.send(data, delay)
      model.write(data)
    }
  }.fork {
    for (_ <- 0 until 100) {
      val delay = Random.nextInt(10)
      model.read(delay)
      receiver.expect(model.read())
    }
  }.join()
}
```

## Creating *Valid* Random Stimuli
- For complex transactions, not all combinations of the parameters represent valid transactions
- We need a way to *constrain* the random transaction generator to only produce valid transactions
- This approach is called *Constrained Random Verification* (CRV)
- E.g. SystemVerilog has a built-in constraint solver for this purpose

```verilog
class Packet;
  rand int unsigned length;
  rand byte payload[];

  constraint length_c {
    length inside {[1:1024]}; // Limit packet length
  }

  constraint payload_c {
    payload.size() == length; // Ensure payload matches length                                                                                                              
  }
endclass
```

## Now what?
- We now know that our DUT behaves according to our model for *X* random transactions
- But how do we know what kind of scenarios we have *covered*?
- We need a tool to measure the *coverage* of executing our random testbench
- Concretely, this means measuring whether a certain condition has been met during simulation
- E.g. whether `in.valid` and `in.ready` where both high two cycles in a row resulting in two back-to-back writes

## SystemVerilog Coverage
- In SystemVerilog, coverage is measured using sets, so-called *bins*, which record how many times a signal took a value which is part of the set
- *Coverpoints* are collections of bins and may be *crossed* to create the Cartesian product of bins

```verilog
covergroup cg;
  back_to_back_valid: coverpoint in_valid {
    bins b2b = (1 => 1);
  }
  back_to_back_ready: coverpoint in_ready {
    bins b2b = (1 => 1);
  }
  b2bwrite: cross back_to_back_valid, back_to_back_ready;                                           
endgroup
```

## Using CRV in the Verification Effort
- CRV is a powerful tool to achieve relatively high coverage with low effort
- However, we need a good transaction level reference model and a very good coverage model to be able to trust the results
- CRV will not cover all wanted scenarios, some might be obtained by tweaking constraints
- Scenarios not covered by CRV can be added through directed tests to ensure full coverage

## Plan
- what about the properties -> assertions and formal verification
- how do we scale?
  - UVM

## References

- [Bergeron, 2012] J. Bergeron, Writing testbenches: functional verification of HDL models. Springer Science & Business Media, 2012.
- [Flake, 2020] Peter Flake, Phil Moorby, Steve Golson, Arturo Salz, and Simon Davidmann. 2020. Verilog HDL and its ancestors and descendants. Proc. ACM Program. Lang. 4, HOPL, Article 87 (June 2020), 90 pages. https://doi.org/10.1145/3386337