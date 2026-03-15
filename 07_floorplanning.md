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
- Placement and routing
- Macros

## Test the HDMI Sound

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

## Summary

## Have a Project Report Round

 * Each group has 5 minutes to present
   - Their status
   - Current challenges
   - Next steps
