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
- Caravel: an open-source SoC framework for tapeout
- Wishbone bus overview

## A Bit of History

- Very early chips were designed by hand
- Photo masks were produced by *drawing* them with tape
- Computer-aided design (CAD) tools were developed to automate the process
- Early tools were developed in open-source
  - Spice, Espresso, Magic, etc.
- Commercial tools emerged in the 1980s
  - Alberto Sangiovanni-Vincentelli's co-founded Cadence Design Systems and Synopsys, the two major EDA tool vendors.

## OpenROAD

- DARPA sponsored project
- End-to-end design flow of chips from RTL to the final chips without human intervention
- Collection of tools
- https://openroad.readthedocs.io/en/latest/
## Google Kicked it Off

- In 2020, Google launched the OpenMPW shuttle program
- Aimed to democratize access to chip fabrication
- Provided free access to the SkyWater 130nm process for open-source designs
- Sparked a surge in open-source hardware design and education

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

## MPW Shuttle

![width:900px](figures/MPW.jpg)

<small>*Copyright 2020-2022 Efabless Corporation and contributors, License: Apache 2.0.*</small>

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
