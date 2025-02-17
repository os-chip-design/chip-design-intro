---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #fff
backgroundImage: url('https://marp.app/assets/hero-background.svg')
---

<!-- headingDivider: 3 -->

# **Verilog and Tiny Tapeout**

**Martin Schoeberl**


## Verilog Introduction

 * You learned Chisel
 * Verilog is the industry standard 
 * Used by tools as exchange format
 * Old langhuage with a lot of quirks
 * You mainly need to be able to read it
 * And a few lines to connect stuff
 * For serious work: use Chisel

## Verilog vs SystemVerilog

 * Verilog is the old standard
 * SystemVerilog is an extension
   - Adding a lot of features
     - 200+ keywords
   - Mainly for verification (with UVM)
     - E.g., object oriented programming
 * But open-source tools do not fully support SV
 * We stick to plain Verilog


## Links to Further Material

 * [Verilog Tutorial](https://www.tutorialspoint.com/vlsi_design/vlsi_design_quick_guide.htm)
 * [Verilog Tutorial](https://www.geeksforgeeks.org/verilog-introduction/)
 * [Verilog Tutorial](https://www.tutorialspoint.com/vlsi_design/vlsi_design_quick_guide.htm)
 * [Verilog Tutorial](https://www.geeksforgeeks.org/verilog-introduction/)

 ## Project

 * We aim for a common project
 * A simple system-on-chip (SoC)
 * With a CPU, memory, and some IO
 * Hosted on GitHub
   * https://github.com/os-chip-design/dtu-soc-2025
   * Send me your GitHub ID to get access
 * Do a real tapeout
   * Sponsored by Edu4Chip

 ## Core Components

 * CPU: [Wildcat RISC-V](https://github.com/schoeberl/wildcat)
 * Cache
 * Memory controller (SPI based Flash and RAM)
 * SPI with switch to *fast* mode

## Peripherals

 * VGA as character display
 * Keyboard
 * Serial port
 * GPIO
 * Timer
 * Accelerator
 * Special IOs (PWM and others)
 * ... your ideas

## Tapeout

 * With efabless and the Open MPW shuttle
   * [ChipIgnite](https://efabless.com/prototyping)
 * Tapeout date: April 21, 2025
 * We can also have experimantal work on Tiny Tapeout

## Workflow

 * All work is in open source
 * We need to cooperate
 * Hosted on GitHub
   - no *private* code copies
   - no branches
 * We use GitHub issues for tasks

 ## Continous Integration (CI)

 * Very common in SW projects
   * Not yet in HW
 * We will do it
 * Catch errors early
 * With GitHub Actions
 * Can we run the tapeout flow on GitHub as part of CI?
   * Tiny Tapeout does the RTL to GDS with GitHub Actions