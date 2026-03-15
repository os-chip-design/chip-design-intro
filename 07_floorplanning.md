---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #fff
<!--backgroundImage: url('https://marp.app/assets/hero-background.svg')-->
---

<!-- headingDivider: 3 -->


# **Floorplanning**

**Martin Schoeberl**

## Outline

- Floorplanning
- Macros
- Placement and routing

## Test the HDMI Sound

 * We might have a remote guest lecture

## DTU Chip Day

 * Save the date: 14 April 2026
 * Danish chip industry (around 20 last year)
 * Presentations and stands
 * Networking for jobs
 * Free sandwiches and free beer ;-)
 * Program and registration at https://chipday.dk/

##

![bg width:80%](https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Intel_Pentium_II_Dixon_die_shot.jpg/1280px-Intel_Pentium_II_Dixon_die_shot.jpg)

## Leros SoC
![bg width:50%](https://github.com/os-chip-design/caravel_leros_2025/raw/main/caravel_layout.png)

## Why Floorplanning?

 * Die size determines cost
 * Find a minimum silicon area to implement our device
   - But only with reasonable engineering cost
 * Netlist gives us a first estimate
   - `xx-yosys-synthesis/reports/stat.rpt`
   - But cells cannot be placed without space
     - Additional area to bring power, tap cells,...
     - Space needed for further optimization (clock tree, clock buffers to fix timing)

## Further Constraints

 * Space around macros not aligned with the standard cell rows
 * Packaging
 * I/O cells
   - Chip size is often determined by I/O and not logic
 * The area needed will be larger than the netlist estimate

## Example from Lab 4
   - Example design from Lab 4:
     - Yosys report: Chip area for module '\adder': 863.328000
     - Floorplan area:
       - "design__die__area": 3330.05
	   - "design__core__area": 1689.12
   - Without constraining, the tools choose the *easy* solution
     - Large die area and place the cells with a lot of space

##

![bg width:30%](https://github.com/os-chip-design/chip-design-book/blob/main/figures/route.png?raw=true)

## What is a Macro?

 * Chips are usually not synthesized, placed, and routed in one go
 * Parts can be hardened individually
   - Then integrated into the top-level
 * The individually hardened block is called a macro
 * Need to be placed in the outer macro or top level
 * Examples:
   - Memories (e.g., SRAM, register files)
   - I/O cells
   - Custom designs

## Advantages of Macros

 * Faster place and route
   - Example: pre-harden a latch-based memory
   - Upper level only needs to place and route the logic around it
 * Can be reused across designs (e.g., instruction and data memories)
 * Can be black-boxed (closed-source designs)

## Macro Challenges

 * We need timing information of the macro block
   - Does this work well with LibreLane?
 * Clock tree cannot be optimzed over macro boundaries
 * Constraints the layout (floorplan)
 * What is the right level of using pre-hardened macros?

## Macro Files (LEF)

 * Library Exchange Format (LEF)
   - Defines the interface
     - Size, pin placement
     - Obstractions for layers
   - Used during PnR (Place and Route)
   - [RF Example](https://github.com/os-chip-design/caravel_leros_2025/blob/main/macro/rf_top.lef)

## Macro Files (GDSII)

 * Graphic Design System II Format (GDSII)
   - *de facto* industry standard for data exchange of ICs
 * Full layout geometry
   - Shapes on all layers
   - Layers are just numbers
   - Which layer contains what is PDK specific
 * Used for final layout and manufacturing

## Macro Files (.lib)

* Liberty format (.lib)
  - Timing and power info
  - Used during synthesis and STA
  - [RF Example](https://github.com/os-chip-design/caravel_leros_2025/blob/main/macro/rf_top.lib)

## Macro Files (Verilog)

 * The Gate-Level Netlist
   - Can be used for simulation
   - Maybe used during STA (Static Timing Analysis)

## Macro Files (SDC)

 * Timing info
 * Synopsys Design Constraints (SDC)
   - Timing constraints for the macro
   - Used during synthesis and STA

## Macro Files (SDF)

 * Standard Delay Format (SDF)
   - Represents timing info
   - IEEE standard
   - Currently not supported by LibreLane :-(
     - A spot to contribute to LibreLane ;-)

## Further Reading on Macros

 * [Using Macros in LibreLane](https://librelane.readthedocs.io/en/stable/usage/using_macros.html)

## Initial Floorplan

 * Plan area for I/O pads
 * Place macro cells
   - E.g., memories
   - Presynthesized blocks
   - Make sure pins connect well
 * Define core area
   - Where the standard cells live
 * Have seen it done in PowerPoint!

 
## Our Project

 * I/O cells are already placed in Caravel
 * Caravel determines the core area (10 mm2)
 * Memories will be macros to be placed
   - Instruction and data memories
   - An experimental register file
 * We can use the rest of the core area for standard cells
 * We can also individually harden sub-projects

![bg right:30% width:70%](https://umsousercontent.com/lib_lnlnuhLgkYnZdkSC/r86juqr1bvqdd7y4.png)

## Leros Example

 * Macros at multiple levels
   - A hierarchy of macros
 * Different versions of memories used (4 Leros)
   - Each Leros is a macro
   - Each memory itsel is a macro
 * [Leros example with DFF memory](https://github.com/os-chip-design/caravel_leros_2025/blob/main/openlane/leros-dffram/config.json)

## Connect the Die to a Package

 * Select a package
   - How many I/O pins do we need
 * I/O from the die is connected with wire bonding

## Simple Chip Bonding

![bg width:50%](https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/07R01.jpg/1280px-07R01.jpg)

## Today

![bg width:50%](https://www.palomartechnologies.com/hs-fs/file-26167023-png/images/stackeddie.png?width=600&name=stackeddie.png)

## Summary

 * Floorplanning is part of chip design
 * Needed for hard macros, such as memories
 * Floorplan and macro pin assignement need to *fit*
   - To be able to route the signals
 * Similar to part placement on PCBs

## Have a Project Reporting Round

 * Each group has 5 minutes to present
   - Their status
   - Current challenges
   - Next steps
 * BTW: 8 weeks till tapeout deadline

## TODO List

 * In the [README](https://github.com/os-chip-design/dtu-soc-2026?tab=readme-ov-file#needed-work) of our project
   - If you don't like it public, we can use our Google doc
 * Distribute tasks to groups