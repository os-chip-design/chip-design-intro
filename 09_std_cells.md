---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #fff
backgroundImage: url('https://marp.app/assets/hero-background.svg')
---

<!-- headingDivider: 3 -->

# **Standard Cells, Memory, and Register Files**

**Martin Schoeberl**


## What are Standard Cells?

- Pre-designed logic gates and flip-flops
- With a uniform height and power rail structure
- Enable automated design through place-and-route (PnR) tools

![width:600px](figures/sky130_fd_sc_hd__dfxtp_1.svg)

## Why Use Standard Cells?

- Simplifies digital design process
- Improves design consistency and manufacturability
- Enables efficient use of silicon area
- Supports various power, performance, and area (PPA) trade-offs
- Alternative is full-custom design
  - More effort, but better performance
  - Maybe used for critical parts of a chip

## Standard Cell Library

- Logic gates (AND, OR, NOT, XOR, etc.)
- Sequential elements (Flip-flops, Latches)
- Buffers and Inverters
- Multiplexers and Special Cells (e.g., clock gating cells)

## Examples

- [Inverter](https://sky130-unofficial.readthedocs.io/en/latest/contents/libraries/sky130_fd_sc_hd/cells/inv/README.html)
- [NAND Gate](https://sky130-unofficial.readthedocs.io/en/latest/contents/libraries/sky130_fd_sc_hd/cells/nand2/README.html)
- [D Flip-Flop](https://sky130-unofficial.readthedocs.io/en/latest/contents/libraries/sky130_fd_sc_hd/cells/dfxtp/README.html)

## Standard Cell Characteristics

- **Cell Height:** Fixed for all cells in a given technology node
- **Cell Width:** Varies based on functionality and drive strength
- **Power and Ground Rails:** Uniform across cells for easy connectivity
- **Drive Strength Variants:** Different versions of the same logic gate to optimize power and speed

## Fabrication and Layout

- Standard cells are arranged in rows within an IC
- Each row shares common power (VDD) and ground (VSS) rails
- Cells are designed to minimize routing complexity
- [Example Wildcat Design in Tiny Tapeout](https://legacy-gltf.gds-viewer.tinytapeout.com/?model=https://schoeberl.github.io/tt10-wildcat/tinytapeout.gds.gltf)

## Power, Performance, and Area (PPA) Considerations

- **High-speed cells:** Larger size, more power consumption
- **Low-power cells:** Smaller size, reduced power, but slower speed
- **Trade-offs:** Selecting appropriate cells based on design constraints

## Skywater Standard Cell Designs

- Open-source standard cell library for 130nm technology
- Available on GitHub
- Provides a variety of cells for digital design
- https://sky130-unofficial.readthedocs.io/en/latest/index.html
- [Cell list](https://sky130-unofficial.readthedocs.io/en/latest/contents/libraries/sky130_fd_sc_hd/README.html)


## Memories in Standard Cell Design

**Types of Memories Used:**
- SRAM (Static Random-Access Memory)
- DRAM (Dynamic Random-Access Memory)
- ROM (Read-Only Memory)
- Register Files (D Flip-Flops)

## SRAM in Standard Cells

- SRAM is commonly implemented using 6T (six-transistor) or 8T cells
- Used for caches, register files, and other high-speed applications
- Designed for low latency and high speed

## 6T SRAM Cell Design

- **Transistor Composition:**
  - 2 cross-coupled inverters (4 transistors) for data storage
  - 2 access transistors to control read/write operations
- **Cell Operation:**
  - **Write Operation:** Bitline is driven high or low, access transistors enable data overwrite
  - **Read Operation:** Wordline activates access transistors, stored value is read through the bitline
- **Advantages:** High-speed operation, good static noise margin

## SRAM Cell

 - https://en.wikipedia.org/wiki/Static_random-access_memory
 - 6 transistors compared to 21 in a D FF

## SRAM in ASIC Design

 - FPAG is easy - just mapped to on-chip memories
   - On-chip memories are hard macros
 - ASICs needs a memory generator (compiler)
   - Memories are mixed signal
   - Memory compilers are an extra business
   - Weak point in the open-source world
  - [OpenRAM](https://openram.org/) is a memory compiler
    - One group is working on it

## DRAM and ROM in Standard Cells

- **DRAM:** Requires refresh cycles, high density but slower than SRAM
- **ROM:** Used for fixed data storage, mask-programmable or electrically alterable

## Memory Design Considerations

- **Area Efficiency:** Memory cells are optimized for density
- **Power Consumption:** Trade-off between dynamic and leakage power
- **Speed:** SRAM is faster but larger, DRAM is denser but slower

## Memory Read and Write Operations

- **SRAM Read:**
  - Wordline (WL) is activated
  - Stored value is transferred to the bitline
  - Sense amplifier detects and amplifies the signal
- **SRAM Write:**
  - Wordline is activated
  - Bitline is forced high or low, overwriting the stored value

## Memory Design Techniques

- **Multi-port Memory:** Supports multiple read and write operations simultaneously
- **Banking:** Divides memory into multiple independent sections to improve access speed
- **Pipelining:** Breaks memory access into stages to increase throughput
- **Error Correction Codes (ECC):** Detects and corrects errors to improve reliability

## Low-Power Memory Design

- **Power Gating:** Turns off unused memory blocks to save power
- **Clock Gating:** Disables clock signals to idle memory circuits
- **Data Retention Techniques:** Uses special low-leakage transistors to retain data with minimal power

## Emerging Memory Technologies

- **Non-Volatile Memories:** MRAM, RRAM, FeRAM offer lower power and persistent storage
- **3D Memory Stacking:** Improves density and bandwidth by stacking memory layers
- **Hybrid Memory Architectures:** Combines different memory types for performance and efficiency


## Register Files

- Register files are small, fast memory blocks used in processors
- E.g., RISC-V register files are 32 32-bit registers

```scala
      val regs = SyncReadMem(32, UInt(32.W), SyncReadMem.WriteFirst)
      val rs1Val = regs.read(rs1)
      val rs2Val = regs.read(rs2)
      when(wrEna && rd =/= 0.U) {
        regs.write(rd, wrData)
      }
```
 - What is the generated hardware?

## Register File in Hardware (Wildcat)

- Implemented as a two read, one write port SRAM in an FPGA
  - 2 read ports implemented by two single port SRAMs
- As registers and muxes in ASICs
- 1024 flip-flops and 32 32:1 muxes
- This is BIG

## Synthesis Result in Skywater130nm

 - Class: [sky130_fd_sc_hd__dfxtp_1](https://skywater-pdk.readthedocs.io/en/main/contents/libraries/sky130_fd_sc_hd/cells/dfxtp/README.html) instances: 1030
 - Class: [sky130_fd_sc_hd__mux2_1](https://sky130-unofficial.readthedocs.io/en/latest/contents/libraries/sky130_fd_sc_hd/cells/mux2/README.html) instances: 1462
 - Class: [sky130_fd_sc_hd__mux4_1](https://sky130-unofficial.readthedocs.io/en/latest/contents/libraries/sky130_fd_sc_hd/cells/mux4/README.html) instances: 277

## Synthesized Wildcat and Register File

  - Using SkyWater 130 nm
  - 3-stages Wildcat pipeline: 429 x 432 umm2
  - Register file: 320 x 320 umm2
  - RF is **55 %** of the area!
  - Better solution needed
  - I tried latches, but yosys did not like them
  - Can we use the OpenRAM memory?

## Summary

- Standard cells are the building blocks of digital IC design
  - standard hight, variable width
- Enable efficient automated design and manufacturing
- Optimize trade-offs between power, performance, and area
- Memories like SRAM need a memory compiler for ASICs
- 6T SRAM design is a widely used memory structure
- Rgister file out of FFs is expensive


