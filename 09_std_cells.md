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

- Pre-designed logic gates and flip-flops used in digital IC design
- Created using a uniform height and power rail structure
- Enable automated design through place-and-route (PnR) tools

## Why Use Standard Cells?

- Simplifies digital design process
- Improves design consistency and manufacturability
- Enables efficient use of silicon area
- Supports various power, performance, and area (PPA) trade-offs

## Standard Cell Library

- Logic gates (AND, OR, NOT, XOR, etc.)
- Sequential elements (Flip-flops, Latches)
- Buffers and Inverters
- Multiplexers and Special Cells (e.g., clock gating cells, tie cells)

## Examples

- [NAND Gate](https://sky130-unofficial.readthedocs.io/en/latest/contents/libraries/sky130_fd_sc_hd/cells/nand2/README.html)
- [D Flip-Flop](https://sky130-unofficial.readthedocs.io/en/latest/contents/libraries/sky130_fd_sc_hd/cells/dfxtp/README.html)

## Standard Cell Characteristics

- **Cell Height:** Fixed for all cells in a given technology node
- **Cell Width:** Varies based on functionality and drive strength
- **Power and Ground Rails:** Uniform across cells for easy connectivity
- **Drive Strength Variants:** Different versions of the same logic gate to optimize power and speed

## Fabrication and Layout

- Standard cells are arranged in rows within an Integrated Circuit (IC)
- Each row shares common power (VDD) and ground (VSS) rails
- Cells are abutted to minimize routing complexity

## Standard Cell Design Rules

- **Cell Alignment:** Maintain alignment in rows for proper routing
- **Well and Substrate Contacts:** Ensure correct electrical behavior
- **Metal Layer Constraints:** Follow routing rules for interconnect layers

## Power, Performance, and Area (PPA) Considerations

- **High-speed cells:** Larger size, more power consumption
- **Low-power cells:** Smaller size, reduced power, but slower speed
- **Trade-offs:** Selecting appropriate cells based on design constraints

## Standard Cell Libraries in ASIC and FPGA Design

- ASIC designs rely heavily on standard cell libraries from foundries
- FPGAs use configurable logic blocks but may also have standard cells in hard macros

## Skywater Standard Cell Designs

- Open-source standard cell library for 130nm technology
- Available on GitHub
- Provides a variety of cells for digital design
- https://sky130-unofficial.readthedocs.io/en/latest/index.html
- [Example Wildcat Design in Tiny Tapeout](https://legacy-gltf.gds-viewer.tinytapeout.com/?model=https://schoeberl.github.io/tt10-wildcat/tinytapeout.gds.gltf)

## Memories in Standard Cell Design

**Types of Memories Used:**
- SRAM (Static Random-Access Memory)
- DRAM (Dynamic Random-Access Memory)
- ROM (Read-Only Memory)
- Register Files

## SRAM in Standard Cells

- SRAM is commonly implemented using 6T (six-transistor) or 8T cells
- Used for caches, register files, and other high-speed applications
- Designed for low latency and high speed

## DRAM and ROM in Standard Cells

- **DRAM:** Requires refresh cycles, high density but slower than SRAM
- **ROM:** Used for fixed data storage, mask-programmable or electrically alterable

## Memory Design Considerations

- **Area Efficiency:** Memory cells are optimized for density
- **Power Consumption:** Trade-off between dynamic and leakage power
- **Speed:** SRAM is faster but larger, DRAM is denser but slower

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

- Implemented as a 2-port SRAM in an FPGA
- As registers and muxes in ASICs
- 1024 flip-flops and 32 32:1 muxes
- This is BIG

## Synthesis Result in Skywater130nm

 - Class: [sky130_fd_sc_hd__dfxtp_1](https://skywater-pdk.readthedocs.io/en/main/contents/libraries/sky130_fd_sc_hd/cells/dfxtp/README.html) instances: 1030
 - Class: [sky130_fd_sc_hd__mux2_1](https://sky130-unofficial.readthedocs.io/en/latest/contents/libraries/sky130_fd_sc_hd/cells/mux2/README.html) instances: 1462
 - Class: [sky130_fd_sc_hd__mux4_1](https://sky130-unofficial.readthedocs.io/en/latest/contents/libraries/sky130_fd_sc_hd/cells/mux4/README.html) instances: 277

## Synthesized Wildcat and Register File

  - Using SkyWater130nm
  - 3-stages Wildcat pipeline: 429 x 432 umm2
  - Register file: 320 x 320 umm2
  - RF is **55 %** of the area!
  - Better solution needed
  - I tried latches, but yosys did not like them
  - Can we use the OpenRAM memory?

## Summary

- Standard cells are the building blocks of digital IC design
- Enable efficient automated design and manufacturing
- Optimize trade-offs between power, performance, and area
- Memories like SRAM and DRAM are crucial components in digital ICs

