# Verification Lab



## FIFO Specification

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


## Features

- **Functionality**:
  - A write handshake places an item in the FIFO
  - A read handshake removes an item from the FIFO
  - Data written to the FIFO should eventually be available for reading
  - A data item written to the FIFO should be available for reading *before* all items written *after* it
  - Writes must not occur when *N* items are in the FIFO (`!in.ready`)
  - Reads must not occur when 0 items are in the FIFO (`!out.valid`)
- **Reset**: The FIFO should signal that it is empty (`!out.valid`) after reset
- **Performance**:
  - If not full, the FIFO must assert `in.ready`
  - If non-empty, the FIFO must assert `out.valid`
  - Writing to an empty FIFO should result in `out.valid` being asserted the next clock cycle and the `out.data` being the same as the written data

## Violations

One queue implementation:
  - does not meet the performance requirements
  - writes are only accepted after a read

One queue implementation:
  - Exceeds the performance requirements
  - it already presents an item written to the FIFO on the output in the *same* cycle

One queue implementation:
  - does not meet the performance requirements
  - writes take too long time to appear on the output
  - it also violates the ordering of the FIFO in certain scenarios

One queue implementation:
  - matches the specification perfectly