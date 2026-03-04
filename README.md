# 02118 - Introduction to Chip Design

This repository contains the material for the 13-week course **02118 - Introduction to Chip Design** at the Technical University of Denmark (DTU). This course was developed with support from the
[Edu4Chip](https://edu4chip.github.io/) project.

### Content

* [Practicalities](#practicalities)
* [Course Aim](#course-aim)
* [Reading Material](#reading-material)
* [Lecture Plan](#lecture-plan)
* [Project](#project)
* [Exam](#exam)
* [Learning Objectives](#learning_objectives)
* [License](#license)
* [Funding](#funding)

## Practicalities

The course runs on **Wednesdays from 13:00 to 17:00** in **Building 308 - Room 017**. 

Each weekly session consist of a lecture and laboratory work. Some session will be fully dedicated to laboratory work (especially at the end of the course when you are expected to work on your project).

The course has two teachers:
 * [Martin Schoeberl](https://www.imm.dtu.dk/~masca/)
 * [Tjark Petersen](https://orbit.dtu.dk/en/persons/tjark-petersen/)

 Guest Lectures:
  * Luca Pezzarossa
  * Ole Richter
  * Kasper Hesse

## Tools

Install the LibreLane tools locally or use the server `chipdesign1.compute.dtu.dk`. The tools are currently usable on Linux and MacOS (even native with Mac Silicon). For Windows use WSL2 to have a Linux environment. There is no official support for Windows available. See Section 1.2 in the [Chip Design Booklet](https://www.imm.dtu.dk/~masca/chip-design-book.pdf) for installation instructions.

## Course Aim

This course is an introduction to the design of digital integrated circuits. It covers the basics of digital circuits, the tools used, and the process of designing a chip. The course is based open-source tools and open-source PDKs. The course also gives the possibility for student projects to be taped out on Tiny Tapeout.


## Reading Material

 * Lecture slides and lab material
 * The textbook [CMOS VLSI Design, A Circuits and Systems Perspective](https://www.amazon.com/CMOS-VLSI-Design-Circuits-Perspective/dp/0321547748) by Neil H. E. Weste and David Harris, also available as PDF
 * The [Chip Design Booklet](https://www.imm.dtu.dk/~masca/chip-design-book.pdf) is a start of notes and exercises for the course.
 * The material related to the open-source tools we use, including installation instructions: [LibreLane Documentation](https://librelane.readthedocs.io/en/latest/)
   - For a quick start you can explore [LibreLane in the browser](https://colab.research.google.com/github/librelane/librelane/blob/main/notebook.ipynb)
 * [Caravel Documentation](https://github.com/efabless/caravel)
 * [Chisel Book](https://www.imm.dtu.dk/~masca/chisel-book.html) (as  reference when doing designs in Chisel) 

## Lecture Plan

This is the list of lectures from 2025. It will be adapted for 2026. 

### Lecture 1: Introduction to the Chip Design Course (MS) 

- [Lecture slides](01_intro.md)

#### Lecture outline
- Overview of chip design and its importance in modern electronics
- Basic terminology and concepts
- Introduction to the LibreLane ASIC design flow
- AISC with standard cells
- PDK

#### Lab. 1
- Local installation of the open-srouce tools (LibreLane)
- Running a "Hello World" example from Verilog source to GDSII
- Explore timing and size
- Explore different reset strategies
- See Section 1.3 in the [Chip Design Booklet](https://www.imm.dtu.dk/~masca/chip-design-book.pdf)

#### Reading

- Lecture slides (also available as PDF in DTU-Learn)
- Weste and Harris: 1.1 and 1.12
- [OpenROAD: Toward a Self-Driving, Open-Source Digital Layout Implementation Tool Chain](https://vlsicad.ucsd.edu/Publications/Conferences/370/c370.pdf)
- [Building OpenLANE: A 130nm OpenROAD-based Tapeout- Proven Flow : Invited Paper](https://ieeexplore.ieee.org/document/9256623)
- [Tiny Tapeout: A Shared Silicon Tapeout Platform Accessible To Everyone](https://www.techrxiv.org/users/799365/articles/1165896-tiny-tapeout-a-shared-silicon-tapeout-platform-accessible-to-everyone)

### Lecture 2: The Transistor, the Inverter, and Other Gates (LP)

- [Lecture slides](02_CMOS.md)

#### Lecture outline
 - The need for a controlled switch  
 - A brief history of the transistor  
 - The MOSFET transistor  
 - The NMOS inverter  
 - The CMOS inverter  
 - Other gates  

#### Lab. 2
- SiliWiz exercises 
- See [Lab 2](lab_02.md) for instructions

#### Reading/reference material
- Lecture slides (also available as PDF in DTU-Learn)
- From the textbook: 
  - 1.3 
  - 1.4.1, 1.4.2, 1.4.3, 1.4.4, 1.4.5
  - 1.5.1, 1.5.2
  - 2.1, 2.2*, 2.3* 

* quick read, no need to go into details with formulas


### Lecture 3: Standard Cells, Interconnect (OR)

- [Lecture slides](03_std_cells.md)

#### Lecture outline

- Introduction to standard cells
- Role of standard cells in digital design
- Components of a standard cell library
- Types of standard cells (e.g., logic gates, flip-flops)
- Power, performance, and area 
- Characterization of standard cells
- Models and simulation

#### Lab. 3

- Simulate a SkyWater130 standard cell in Spice

#### Project Setup

- Discussion about the project
- Form and register groups in DTU Learn
- Choose a project component to work on (CPU, memory, peripherals, testing, etc.)
- Sign up in the GitHub repository (TBD)


### Lecture 4: Tool Flow and Caravel (MS) 

- [Lecture slides](04_tools.md)

#### Lecture outline

- Details of the LibreLane ASIC design flow
- Individual tools and their outputs
- Caravel
- Tapeout options (CF, waver.space for production, IHP)
- Wishbone bus overview
- Project discusion

#### Lab. 4

1. Run steps of the LibreLane flow on a simple design from Python
  - According to Section 1.4 in the [Chip Design Booklet](https://www.imm.dtu.dk/~masca/chip-design-book.pdf)
  - Explore the outputs of the individual stages
2. Harden our [Caravel framework](https://github.com/os-chip-design/dtu-soc-2026) with a simple peripheral
3. Write a simple Wishbone peripheral (in Chisel)
   - See Section 2.3.9 in the [Chip Design Booklet](https://www.imm.dtu.dk/~masca/chip-design-book.pdf)

#### Reading

- Lecture slides (also available as PDF in DTU-Learn)
- [Article on OpenLane2](https://woset-workshop.github.io/PDFs/2024/17_OpenLane_2_Making_the_Most_.pdf)
- [LibreLane Documentation](https://librelane.readthedocs.io/en/latest/)
- [Wishbone Specification](https://cdn.opencores.org/downloads/wbspec_b4.pdf)


### Lecture 4b: Verilog (MS)

- [Lecture slides](04_verilog.md)

#### Lecture outline

- Introduction to Verilog
- Comparison with Chisel

#### Verilog Lab. 4b (Optional)

- See [Lab Verilog](lab_verilog.md) for instructions

#### Reading

- Lecture slides (also available as PDF in DTU-Learn)
- Find good Verilog projects and read the code
  - E.g., [YARVI](https://github.com/tommythorn/yarvi)


### Lecture 5: Verification (TP)

- [Lecture slides](05_verification.md)

#### Lecture outline

- Introduction to verification
- Verification methodologies
- Simulation-based verification
- Testbench design
- Assertions and coverage metrics
- Brief overview of formal verification
- Industry Standards, tools, and frameworks in verification

#### Lab. 5

The lab consists of two parts:


1. One part works with formal verification methods. See [Verification Lab](verification-lab/readme.md) for instructions
2. For the other part, see [Caravel Testing](caravel_test.md) for instructions

#### Reading

- Lecture slides (also available as PDF in DTU-Learn)
- TBA

### Lecture 6: Timing and Physical Design (OR)

- [Lecture slides](06_timing_and_placement.md)

#### Lecture outline

- TBD

#### Lab. 6

- TBD

#### Reading

- Lecture slides (also available as PDF in DTU-Learn)

### Lecture 7 TBD (MS)  + Midterm course evaluation


### Lecture 8: Memory (TP)

#### Lecture outline

- Memory types and memory organization
- Register files: FF, sync mem, latches, custom design
- Memory macros (IP blocks)
- Memory options with the SkyWater130 PDK

- [Lecture slides](08_memory.md) TBD

#### Lab. 8

- Exploring memory options with SkyWater130
- Use memories in our project

#### Reading

- Lecture slides (also available as PDF in DTU-Learn)

### Lecture 9: Guest lecture - Chip Design at Oticon (Kasper)

#### Lecture outline:
- Chip design at Demant
- Industrial design and implementation flow overview
- Design and implementation considerations for low power
- Challenges in lower design nodes (and/or at lower voltages)

#### Lab. 9

- Finalise the functional implementation of your project




### Lecture 11: TBD (TP/MS)

- [Lecture slides](???.md) - not yet availble

#### Lecture outline:

- Chip design rules
- Power distribution design, optimization, and analysis
- Digital design timing
- Clock distribution challenges and clock trees
- Power optimization through clock gating
- Timing closure

#### Lab. 11

- Run routing (OR)
- Fix shorts and design rule violations

#### Reading
- None

### Lecture & Lab 12: Project Work and Tapeout (MS/TP)

The full time slot is dedicated to project work.

- Run Timing closure and LVS
- Fix timing violations and handle design missmatches 
- "tape out" your project  


### Lecture 13: Student project presentations (MS/TP)

Each group presents their finalized project and discusses the results.




## License

All original content in this repository, including text, code, and other materials, is licensed under the CC0 1.0 Universal license (see LICENSE file), unless otherwise noted. 

### License Note for Images

Certain images in this repository are not covered by the CC0 license. These images may be subject to more restrictive copyright terms. Where applicable, copyright and licensing information is provided for these images in the relevant directories, file descriptions, or as text integrated in the images.

## Funding
Funded by the European Union within the *[Edu4Chip](https://edu4chip.github.io/) - Joint Education for Advanced Chip Design in Europe* project. Views and opinions expressed are however those of the author(s) only and do not necessarily reflect those of the European Union or European Health and Digital Executive Agency (HaDEA). Neither the European Union nor the granting authority can be held responsible for them.

<img src="figures/funded_by_the_EU.png" width="300">

