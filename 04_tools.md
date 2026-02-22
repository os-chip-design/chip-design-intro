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


## The End Produce: a Real Chip

 * We will do a real tapeout with [ChipFoundry](https://chipfoundry.io/)'s ChipIgnite
   - Multi-Project Wafer (MPW) run
 * Fabrication in SkyWater 130 nm technology
 * 10 mm2 die size
 * Show the die around

## Reading/reference material
- Lecture slides (also available as PDF in DTU-Learn)
- [LibreLane Documentation](https://librelane.readthedocs.io/en/latest/)* 
<br><br><br>
\* This is a large documentation from whih this slide set is based upon, you do not need to read it all. Reference to it when needed.



## Introduction

- Step-by-step overview of the **LibreLane** ASIC design flow, from **RTL** to **GDSII**
<br>
- Gives an intuitive understanding of each stage in the process
- Helps getting an understand of each step basics
<br>
- More in-depth exploration is up to you, if needed


## LibreLane ASIC design flow

- LibreLane is an open-source toolchain for ASIC design
  - Automates the process from RTL to GDSII for tapeout
  - Ensures manufacturability with verification steps
  - Uses open-source tools (e.g., Yosys, OpenROAD, and Magic)



