---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #fff
---

<!-- headingDivider: 3 -->

# **Functional Hardware Verification**

**Tjark Petersen**

## Motivation

- Computer chips are everywhere in our daily lives
- Their complexity is increasing
- But how can we be confident that everything works as it should?
- We better be sure, since a tape-out can cost millions!
- We have to verify our design
- It does not add any value but it is a necessary task

## Lecture Overview I
- A journey from a bare-bones Verilog/VHDL testbench to modern verification methodologies
- How do we decide what to verify?
- How can we verify a design?
- How can we keep our infrastructure maintainable and scalable?

## Lecture Overview II
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
  - When do you know that you are done?

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
  - Data written to the FIFO will eventually be available for reading
  - A data item written to the FIFO will be available for reading *before* all items written *after* it
  - Writes aren't accepted when *N* items are in the FIFO
  - Reads aren't accepted when 0 items are in the FIFO
---
- **Reset**: The FIFO will signal that it is empty after reset
- **Performance**:
  - If not full, the FIFO will always assert `in.ready`
  - If non-empty, the FIFO will always assert `out.valid`
  - Writing to an empty FIFO will result in the data being available for reading on the next cycle

## Scenarios - Example 1

- Let's develop a scenario for the feature: "Writes aren't accepted when *N* items are in the FIFO"
- Scenario:
  - Insert *N* items into the FIFO
  - Now `in.ready` should be deasserted
- Is it exhaustive though? What about (add *N*, read 1, add 1)?
- We could decide based on some assumed knowledge of the design

## Scenarios - Example 2

- Let's develop a scenario for the feature: "A data item written to the FIFO will be available for reading *before* all items written *after* it"
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
- Copy and paste the same code 100 times?
- We need to abstract the interaction with the DUT
- We can use so-called *Bus Functional Models* (BFM)
- A BFM provides functions which take the parameters of an interaction and take care of the low level timing and signal manipulation

## Sender BFM
- A BFM for driving an input port in Scala
```scala
class SenderBFM(port: Handshake, clock: Clock) {
  def send(data: Int): Unit = {
    port.valid.poke(1.B)
    port.data.poke(data.U)
    while(!port.ready.peekBoolean()) clock.step()                                           
    clock.step()
    port.valid.poke(0.B)
  }
  def send(data: Seq[Int]): Unit = {
    data.foreach(d => send(d))
  }
}
```

## Receiver BFM
- A BFM for reading an output port in Scala
```scala
class ReceiverBFM(port: Handshake, clock: Clock) {
  def receive(): Int = {
    port.ready.poke(1.B)
    while(!port.valid.peekBoolean()) clock.step()
    val data = port.data.peekInt().toInt
    clock.step()
    port.ready.poke(0.B)
    data
  }
  def expect(data: Int): Unit = {
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
      model.write(data)
      sender.send(data, delay) 
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

## CRV in SystemVerilog
- SystemVerilog has a built-in constraint solver for this purpose
- Randomizable variables are marked with the `rand` keyword

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
- We need a tool to measure the *functional coverage* of executing our random testbench
- Concretely, this means measuring whether a certain condition has been met during simulation
- E.g. whether `in.valid` and `in.ready` where both high two cycles in a row resulting in two back-to-back writes

## SystemVerilog Functional Coverage
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

## Open-Source Functional Coverage
- In Cocotb, we can use *cocotb-coverage* to collect functional coverage at the interface of a DUT
- In Scala, we can use the *ChiselVerify* library together with Chiseltest:

```scala
val cr = new CoverageReporter(dut)
cr.register(
  // Declare CoverPoints
  cover("a", dut.io.a)(DefaultBin(dut.io.a)),
  cover("b", dut.io.b)(DefaultBin(dut.io.b)),
  // Declare timed cross point with a delay of 1 cycle                                      
  cover("timedAB", dut.io.a, dut.io.b)(Exactly(1))(
    cross("both1", Seq(1 to 1, 1 to 1))
  )
)
```

## Using CRV in the Verification Effort
- CRV is a powerful tool to achieve relatively high coverage with low effort
- However, we need a good transaction level reference model and a very good coverage model to be able to trust the results
- CRV will not cover all wanted scenarios, some might be obtained by tweaking constraints
- Scenarios not covered by CRV can be added through directed tests to ensure full coverage

## What about the Properties?
- In addition to scenarios, we also have properties to describe system behavior
- E.g. "If not full, the FIFO will always assert `in.ready`":
  - Let `cnt` be the number of items in the FIFO
  - If `cnt < N` then `in.ready == 1`
- This is something concrete we can check in our testbench using *assertions*

## Assertions
- Assertions can be added directly to the design code
- They are also a good tool to check *design assumptions*
- If the property does not hold at any point during simulation, an error is raised
- Assertions directly point to the source of the problem in contrast to black-box testing where an error has to be *traced-back* to the source
```scala
when(cnt < N.U) {
  assert(in.ready, "FIFO not accepting write when not full")
}
```

## Coverage and Assertions
- If assertions are used in simulation, we again have a problem of unknown coverage
- How do we know that interesting scenarios which exercise the assertions have been covered?
- In addition to the presented functional coverage, SystemVerilog for instance also as *cover* statements, which keep track of whether a condition has been met

## But there is more...
- There are actually tools which can *prove* that the same assertions in a design that we used during simulation hold for *all* possible scenarios
- This is called *formal verification*
- A solver will try to *falsify* assertions and will find a *counter-example* if the assertion does not hold
- The counter-example describes the sequence of inputs which lead to the assertion being violated

## Formal Verification
- Often, bounded model checking is used, which explores the state space of a design up to a certain number of cycles
- How many cycles do we need to check to be sure?
- For larger designs and more complex properties, the state space explodes and the tool becomes unusable
- However, for smaller designs and properties, formal verification can be a quick and powerful tool 

## Assumptions
- As with CRV, not all input sequences are *legal*
- We can use *assumptions* to constrain the input space
- This limits the solver in what input sequences it will explore
- We could for instance force the solver to accept the FIFO output as soon as it is available:

```scala
assume(!out.valid || out.ready) // If output is valid, out.ready must be high
```

## Open-Source Formal Verification
- [SymbiYosys](https://yosyshq.readthedocs.io/projects/sby/en/latest/) is a front-end for Yosys-based formal hardware verification
- It can be used to prove properties in SystemVerilog designs, but does not support more complex assertions
- Chiseltest has a formal library which can be used to check properties in Chisel designs


## Chiseltest Formal Example
```scala
class FifoWrapper extends Module {
  ... // instantiate a fifo
  // keeps track of the internal queue count
  val cntReg = RegInit(0.U(8.W))
  when(in.fire && !out.fire) { // push but not pop
    cntReg := cntReg + 1.U // we gain one element
  }.elsewhen(!in.fire && out.fire) { // pop but not push
    cntReg := cntReg - 1.U // we lose one element
  }

  // Property: The fifo count should not exceed the internal fifo size           
  assert(cntReg <= n.U, "Fifo count should not exceed n")
}
```

```scala
verify(new FifoWrapper, Seq(BoundedCheck(10))) // Check for 10 cycles
```

## Let's recap
- We've seen how we can increase the abstraction of a testbench using BFMs and transactions
- We've seen how we can use CRV alongside functional coverage to automate the verification process partially
- We have seen how we can directly use properties to check system behavior during simulation (dynamic verification) or using formal verification
- These techniques together present the state-of-the-art in terms of primitive tools in functional verification

## How do we scale?
- These techniques only provide the foundation for a verification effort
- For larger projects with many engineers, we need a way to scale the verification effort
- This is a matter of *methodology* and *design patterns*
- How can we structure a testbench such that we can keep it flexible and maintainable?
- How do we create verification components which are composable and can be reused?

## Universal Verification Methodology
- Each EDA vendor tried to answer this question with their own methodology
- A merger of all these methodologies resulted in the *Universal Verification Methodology* (UVM)
- UVM is a methodology and concrete class library for creating testbenches in SystemVerilog
- It tries to provide a standard way to structure and implement functionality in a testbench, such that it is as composable and flexible as possible


## My Master Thesis
- The UVM is a huge standard with only a subset being used in practice
- My thesis took the key concepts of the UVM, tried to simplify them and bring them to Scala 3
- Built a standalone co-simulation framework for Scala 3 based on Verilator for simulation
- Let's now look at the key aspects of UVM-like testbenches

## Phases
- A testbench usually can be structured into multiple phases which are executed in sequence and take care of different aspects
- By making phasing explicit, we can reuse standard implementations, e.g. for resetting the DUT

![](figures/L8/phases.svg)

## Components I
- Components allow us to split a testbench from one monolithic block into smaller pieces with well defined interfaces
- In the UVM, components build a hierarchy, like a hardware design
- *Drivers* and *Monitors* directly interact with the DUT
- *Agents* provide access to a specific interface type

![bg right:40% width:95%](figures/L8/tb_components.jpg)

## Components II
- *Environments* are containers for all agents for specific subsystems of the design
- They contain *Scoreboards* which check the correctness of the DUT output and other *Analysis* components such as *Coverage Collectors*
- Components pass *transactions* in between them through channels

![bg right:40% width:95%](figures/L8/tb_components.jpg)


## Components III
- The component hierarchy builds static infrastructure around the DUT to facilitate the applying of stimuli and check correctness as well as coverage
- The idea is to reuse components across different levels of testing (module-level up to chip-level) and across different projects

![bg right:40% width:95%](figures/L8/tb_components.jpg)

## What about Stimulus?

- In UVM, stimulus is generated by *sequences*
- Sequences are objects which generate a transaction stream based on some internal code
- For each produced item, the sequence receives feedback to decide what to do next

```scala
class MySeq extends Sequence[Int] {
  def body() = {
    for (i <- 0 until 100) {
      val response = yieldTx(i)
      println(s"$i -> $response")                                                            
    }
  }
}
```

## Building a Test Case

- A test case combines the environment/agents with a sequence and runs the simulation
- Using the `Factory` allows us to extends MyTest and overwrite e.g. the type of sequence

```scala
class MyTest(dut: TinyAlu) extends Test {
  val agent = Factory.create[AluAgent]
  val cov = Factory.create[AluCoverage]
  agent.monitor.addListener(cov)

  def test() = {
    val seq = Factory.create[MySeq]                                                                         
    seq.start(agent)
    agent.sequencer.play(seq)
    seq.waitUntilDone()
  }
}
```

## Taking a Step Back I
- We have seen the basic tools that we have at our disposal to verify a hardware design
- Non of them is a silver bullet and they have to be combined to get the best productivity
- CRV and simulation-time assertions are a powerful tool, but they rely on a good coverage model
- Formal verification is only feasible for smaller designs, and developing properties and all required assumptions is hard

## Taking a Step Back II
- The basic tools alone are not enough to scale a verification effort
- Verification is a software engineering task and requires managing a large code base
- Efforts such as the UVM try to provide software patterns to structure a testbench to make it modular and scalable

## Verifiation Lab
- In the `verification-lab` folder, you will find a lab exercise
- You will be provided with four Fifos, of which some violate some aspect of the specification
- Your task is to develop properties and scenarios to find which Fifo violates which part of the specification
- Implement properties as assertions and scenarios as test cases

## References

- [Bergeron, 2012] J. Bergeron, Writing testbenches: functional verification of HDL models. Springer Science & Business Media, 2012.
- [Flake, 2020] Peter Flake, Phil Moorby, Steve Golson, Arturo Salz, and Simon Davidmann. 2020. Verilog HDL and its ancestors and descendants. Proc. ACM Program. Lang. 4, HOPL, Article 87 (June 2020), 90 pages. https://doi.org/10.1145/3386337