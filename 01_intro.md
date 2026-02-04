---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #fff
backgroundImage: url('https://marp.app/assets/hero-background.svg')
---

<!-- headingDivider: 3 -->

# **Introduction to Chip Design**

**Martin Schoeberl**

## Welcome

 * Learn the basics of chip design
 * Design and fabricate your own chip (ASIC)
 * We will use open-source tools only
 * This is a (relatively) new 13-week course
    * We will adapt to your needs and interests
    * We did an initial open-source chip design course 2025
 * Will have rough edges
 * I am excited to see you building your own chip

## Introduction Round

 * Who are we?
   - Ole, Luca, and Martin
   - Tjark as TA
 * Who are you?
   - Why this course?
   - What is your background?

## Course Overview

 * Some initial lectures, including labs
 * Guest lectures
 * Project work
   - We will do a full chip design
     - From RTL to GDSII and tapeout
   - Subtopics as group work
   - Write a (short) report
 * Join Discord for communication (link in DTU Learn)

## Tapeout Project

  * Design a chip together
  * Using Caravel as base chip framework
  * Your sub-projects will be combined
  * Chip design is a large team effort
  * Tapeout with [ChipFoundry](https://chipfoundry.io/)'s ChipIgnite
    - Multi-Project Wafer (MPW) run
  * Fabrication in SkyWater 130 nm technology
  * We will cover the full flow

## Prerequisites

 * Digital Design (DE2)
   - Combinational and sequential logic
   - Basic computer architecture
   - Basic Verilog, VHDL, or Chisel knowledge
   - Translation between HDLs and schematic
   - Which language do you know?
 * Basic programming and shell/git skills
 * This is *not* an introduction to digital design!
 * No prior knowledge of VLSI or chip design needed

## Moore's Law: the Exponential Growth

 * The number of transistors on a chip doubles every 2 years
 * Predicted by Gordon Moore in 1965 (Intel co-founder)
   * Still holds, but not for free
   * Transistors are getting smaller
   * Power density is increasing
   * The 7 nm technology node is common now
     - x nm was originally half the gate length, but not anymore
   * When is the end of Moore's Law?
 * [Moor's Law](https://en.wikipedia.org/wiki/Moore%27s_law)

---

![width:800px](figures/Moores_Law_Transistor_Count_1970-2020.png)

## Dennard Scaling

 * Transistor size shrinks
   * Voltage scales down
   * Current scales down
   * Power density stays constant
 * Power stays proportional with area
 * No more Dennard scaling
   * Leakage current increases with smaller transistors

## Trends in Chip Design

 * Processors' performance gains are slowing down
 * More specialized cores (e.g, AI accelerators) are needed
 * More digital/ASIC designs and designers are needed
 * We need 20+ new chip designers per year in Denmark/Copenhagen
 * Bright future for you!

## Open Source Chip Design

 * Open source tools (LibreLane)
 * Open source PDK (SkyWater130)
 * Open source IP (e.g., RISC-V core Wildcat)
 * Open source chip framework (Caravel from efabless)
 * Open source teaching material (this course)

## Open Source Enables

  * Learn chip design on your own computer!
  * No expensive licenses, no server access needed
  * Allowed to share results and designs
    - Not possible with the commercial tools
  * Collaboration and sharing designs
  * Contribute to the community
  * Enables the Tiny Tapeout project

## Tools

 * Chisel
 * Verilog/Verilator
 * Yosys
 * OpenRoad and OpenLane
 * SkyWater PDK

## Chip Design Workflow

1. **Specification**: Define the chip's purpose and requirements  
2. **Architecture Design**: High-level design of the system
3. **RTL Design**: Register Transfer Level design using HDLs (e.g., Chisel or Verilog)
4. **Verification (Testing)**: Ensure the design meets specifications

## Chip Design Workflow cont.

5. **Synthesis**: Convert RTL to gate-level netlist
6. **Physical Design**: Floorplanning, placement, routing
7. **Fabrication**: Manufacturing the chip in a foundry
8. **Testing**: Post-production validation
9. **Bring-up**: First test of the chip
10. **Sell**: Profit!

## Key Components of a Chip
- **Transistors**: Building blocks of chips
- **Logic Gates**: AND, OR, NOT, etc.  
- **Flip-Flops**: Memory elements
- **Interconnects**: Wires connecting components  
- **Memory**: RAM, ROM  
- **I/O Pads**: Interface with external devices 


## Hardware Description Languages (HDLs)
- **Verilog**: Widely used for RTL design
  - With extension to SystemVerilog
  - We will introduce Verilog in this course
- **VHDL**: Popular in Europe and for complex systems
  - Almost not supported by open-source tools
- **Chisel**: Our Favorite! 
  - Scala-based HDL
  - You learned it in DE2
  - Generates Verilog

## LibreLane

 * LibreLane is an open-source digital ASIC flow
 * RTL to GDSII flow
   - GDSII is the final layout file for the chip
   - Sent to the foundry (short fab) for manufacturing 
 * Based on several open-source tools
 * Developed by efabless
 * Resuses OpenROAD and Yosys
 * We will explore LibreLane today!

## OpenRoad

 * Fully autonomous, no-human-in-the-loop flow
 * Collection of (open-source) tools for digital design
 * Sponsored by DARPA
 * Led by UC San Diego
 * Used by OpenLane


---
![width:700px](figures/openlane.svg)

## Yosys: Open-Source Verilog Synthesis

  - Framework for Verilog RTL synthesis
  - Originally developed by Clifford Wolf as a BSc project at TU Wien
  - Converts RTL designs into gate-level netlists
  - Supports Verilog-2005 and (some) SystemVerilog
  - Includes optimization and formal verification
  - ASIC and FPGA synthesis
  - Educational and research projects
  - Part of the OpenROAD project

## Getting Started with Yosys
- **Installation**:  
  - Linux: `sudo apt-get install yosys`  
  - macOS: `brew install yosys`  

- **Basic Commands**:  
  ```bash
  read_verilog design.v       # Read Verilog file
  synth -top top_module       # Synthesize design
  write_verilog output.v      # Save netlist
  ```


## SkyWater PDK and Google

 * SkyWater PDK is an open-source PDK
 * The SkyWater fab was originally a Cypress fab
 * Google was pushing to open-source the PDK
 * Google sponsored MPW runs
   - We tried to get a chip produced, but failed :-(
   - Sponsoring is over
 * One MPW run is around $ 15.000 per tile
   - We have money for a chip tapeout :-)  

## What is a PDK?

   * PDK: Process Design Kit
   * Contains the information for a specific technology node
       - Transistor models
       - Design rules
       - Metal layers
       - Via structures
       - ...
   * The PDK is provided by the fab
   * Needed to produce a GDSII file

## LibreLane Demo

 * Start nix with `nix-shell`
 * cd to wildcat
 * Show wildcat.json
 * Generate Verilog (`make hw-fmax`)
 * Run LibreLane and show results (see Makefile)
 * Explore in IntelliJ (or VSC)

## How are Chips Produced?

 * In a fab (short for fabrication plant)
 * Very expensive! (fab and masks)
   * MPW (Multi-Project Wafer) is a way to share the cost
 * Details in VLSI course   
 * Watch [Matt Venn at IHP](https://youtu.be/aBDJQ9NYTEU?si=G19gW0zrbZuDSOp4)
   - IHP joined the open-source movement
   - Last open-source conference FSiC was at IHP

## Tiny Tapeout

 * [Tiny Tapeout](https://tinytapeout.com/)
 * A simple flow for a simple chip
 * Uses one MPW tile from ChipFoundry
 * Cut into 512 mini tiles
   - 160 x 100 um tiles
 * Full flow in the cloud (GitHub Actions)
 * Cheap tile: 50-70 EUR
 * We (Edu4Chip) can sponsor one tile per project
 * Has a large Discord community

## Tiny Tapout Workshop
 
 * Matt Venn runs a Tiny Tapeout workshop at DTU
 * Saturday 21st of February 2026
 * From zero to a chip in 4 hours
 * [Tiny Tapeout Workshop](https://edu4chip.github.io/ttw2026DTU.html)
 * Sign up quickly, limited seats!
 * Just DKK 100, includs one tile
   - Thanks to our sponsors

## Using Chisel

 * Who does not know Chisel?
 * Easy to learn in self-study
   - Use the [Chisel book](https://www.imm.dtu.dk/~masca/chisel-book.html)
   - Do the [Chisel lab](https://github.com/schoeberl/chisel-lab)

## Reading

 * Weste and Harris: 1.1 and 1.12
 * [OpenROAD: Toward a Self-Driving, Open-Source Digital Layout Implementation Tool Chain](https://vlsicad.ucsd.edu/Publications/Conferences/370/c370.pdf)
 * [Building OpenLANE: A 130nm OpenROAD-based Tapeout- Proven Flow: Invited Paper](https://ieeexplore.ieee.org/document/9256623)
 * [Tiny Tapeout: A Shared Silicon Tapeout Platform Accessible To Everyone](https://www.techrxiv.org/users/799365/articles/1165896-tiny-tapeout-a-shared-silicon-tapeout-platform-accessible-to-everyone)

## Chipdesign1 Server

 * If tool installation is a (big) issue, use chipdesign1
 * chipdesign1.compute.dtu.dk
 * All will get an account on chipdesign1
   - Signe the paper work
 * Login with SSH
   - Need to use VPN (OpenVPN)
   - See: https://itswiki.compute.dtu.dk/index.php/OpenVPN
 * Nice to use with VSC (show it)

## The Lab Today

 * Install LibreLane
   - With nix
   - Easy on Linux and macOS
   - Windows: use WSL
 * The beauty of open-source tools is running them locally
   - With closed-source tools you need to use our servers 

 ## Summary

 * We will design and fabricate a chip
   - Using open-source tools only
   - We will do a real chip tapeout
 * This is an introductory course
   - Motivating you to learn more in the VLSI course
     - and the chip design specialisation (in the MSc)
   - Project-based
 * Still a bit under development
    
