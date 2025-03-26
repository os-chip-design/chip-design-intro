# Requirements
- sbt
- jdk (8 up to 21)
- see https://github.com/schoeberl/chisel-lab/blob/master/Setup.md for instructions

# Verification Lab

In this lab you will be given four FIFO implementations, `FIFO[0-3]`. Some Fifos follow the spec, some violate it. Your task is to identify the potential specification violations and match them to one of the following statements, without looking at the implementation itself:

One queue implementation:
  - Matches the specification perfectly

One queue implementation:
  - Does not meet the performance requirements: writes are only accepted after a read

One queue implementation:
  - Exceeds the performance requirements: it already presents an item written to the FIFO on the output in the *same* cycle

One queue implementation:
  - Does not meet the performance requirements: writes take too long time to appear on the output
  - It also violates the ordering of the FIFO in certain scenarios

Develop scenarios and properties based on the specification. Then add properties as assertions to the `FormalWrapper` module in `src/test/scala/FifoTests.scala` and add scenarios as additional test cases to the `FifoSpec` class in the same file.

The formal wrapper already contains the property that the number of items in the Fifo should never exceed its capacity. This property holds for all Fifo implementations.

The `FifoSpec` contains one scenario where a single random item is written and read from the Fifo. A model is used to check the output of the Fifo. The sending and receiving happens on two different threads using the fork-join methods.

Now let's take a look at the specification again:

### FIFO Specification

- **Functionality**:
  - A read/write occurs when ready and valid are asserted in the same clock cycle
  - Writes to the FIFO should be read in the same order
  - Reads should only occur when the FIFO is non-empty
  - Writes should only occur when the FIFO is not full
- **Reset**: After reset the FIFO should be empty

- **Performance**:
  - If not full, the FIFO should accept new writes each clock cycle
  - If non-empty, the FIFO should accept reads each clock cycle
  - An item written to an empty FIFO should be available for reading on the output at the next clock cycle

Based on this specification, the following features can be derived:

### Features

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

Now translate the features into properties if possible and else create scenarios to test the features. Then add the properties to the `FormalWrapper` module and the scenarios to the `FifoSpec` class.