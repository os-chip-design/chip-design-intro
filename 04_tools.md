---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #fff
<!--backgroundImage: url('https://marp.app/assets/hero-background.svg')-->
---

<!-- headingDivider: 3 -->


# **Tool Flow and Caravel**

**Martin Schoeberl**

## Outline

- Overview of the LibreLane ASIC design flow: from RTL to GDSII
- Gives an intuitive understanding of each stage in the process
- Helps getting an understand of each step basics
- More in-depth exploration is up to you, if needed


## LibreLane ASIC design flow

- LibreLane is an open-source toolchain for ASIC design
  - Automates the process from RTL to GDSII for tapeout
  - Ensures manufacturability with verification steps
  - Uses open-source tools (e.g., Yosys, OpenROAD, and Magic)

## The Flow Stages

  1. RTL Design: Write your design in a hardware description language (e.g., Chisel or Verilog)
  2. Synthesis: Convert RTL to gate-level netlist
  3. Floorplanning: Define the physical layout of the chip
  4. Placement: Place standard cells and macros on the chip
  5. Routing: Connect the placed cells with wires
  6. Verification: Check for design rule violations and timing issues
  7. GDSII Generation: Create the final layout file for fabrication


## LibreLane

![width:900px](figures/flow.png)

OpenLane design flow, including the OpenROAD flow in blue. 

<small>*Copyright 2020-2022 Efabless Corporation and contributors, License: Apache 2.0.*</small>

## Caravel

![width:900px](figures/layout.png)

<small>*Copyright 2020-2022 Efabless Corporation and contributors, License: Apache 2.0.*</small>

The combination of the Caravel harness with the user project results in the final Caravel tile for the MPW run.

## Caravel

![width:600px](figures/caravel.svg)


## Wishbone Interface

![width:500px](figures/Wishbone_Interface.svg)

## Wishbone Bus

![width:500px](figures/wishbone.svg)

Wishbone asynchronous read followed by an asynchronous write

## Wishbone Bus

![width:500px](figures/wishbone_sync.svg)

Wishbone synchronous read followed by a synchronous write
