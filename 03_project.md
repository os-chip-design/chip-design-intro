---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #fff
backgroundImage: url('https://marp.app/assets/hero-background.svg')
---

<!-- headingDivider: 3 -->

# **The Tapeout Project**

**Martin Schoeberl**


## Multi-Project Wafer (MPW)

 * A waver is expensive
 * Sharing cost on a multi-project wafer (MPW)
 * [ChipFoundry](https://chipfoundry.io/) offers this service, it is called chipIginite
   * $ 15000 for a full chip
   * 10 mm2
 * SkyWater 130 nm
 * Full open source PDK
 * Show the chip around

## Tiny Tapeout

 * Tiny Tapeout takes it further down
 * Split the MPW into 512 tiny tiles
   * $ 50 for a tile
   * 100 x 160 um
   * About 1000 gates
   * A tiny 8-bit processor (Lipsi) fits in one tile
 * We can use Tiny Tapeout for projects


 ## The Project

 * We aim for a common project
 * A simple system-on-chip (SoC)
 * With a CPU, memory, and some IO
 * Hosted on GitHub
   * https://github.com/os-chip-design/dtu-soc-2026
   * Send me your GitHub ID to get access
   * Similar to https://github.com/os-chip-design/caravel_leros_2025
 * Do a real tapeout
   * Sponsored by Edu4Chip

 ## Core Components

 * CPU: [Wildcat RISC-V](https://github.com/schoeberl/wildcat)
 * On-chip memory
 * Interface with Wishbone to the Caravel host CPU
 * UART for serial communication
 * Memory controller (SPI based Flash and RAM)
 * Maybe caches

## Peripherals

 * VGA as character display
 * Keyboard
 * GPIO
 * Timer
 * Accelerator
 * Special IOs (PWM and others)
 * ... your ideas

## Tapeout

 * With ChipFoundry and the Open MPW shuttle
   * [ChipIgnite](https://chipfoundry.io/chipignite)
 * Tapeout date: May 13, 2026
 * We can also have experimental work on Tiny Tapeout

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

## Group formation

- Groups of minimum 3 students (larger groups preferred)
- Register groups in DTU Learn (this week)


## Deliverables

- Project report (max 6 pages paper style)
- Contribution to the GitHub repository
- Contribution to README/documentation
- Presentation in last lecture

## Summary

 * We will do a real tapeout
 * With a simple SoC design
 * With a RISC-V CPU and some peripherals
 * Send me your GitHub ID to get access to the project
 * We will use GitHub for collaboration and CI