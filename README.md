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

The course runs on **Wednesdays from 13:00 to 17:00** in **TBD: Building 308 - Room 017**. 

Each weekly session consist of a lecture and laboratory work. Some session will be fully dedicated to laboratory work (especially at the end of the course when you are expected to work on your project).

The course has two teachers:
 * [Martin Schoeberl](https://www.imm.dtu.dk/~masca/)
 * [Ole Richter](https://orbit.dtu.dk/en/persons/ole-richter/)

## Tools

Install the LibreLane tools locally or use the server `chipdesign1.compute.dtu.dk`. The tools are currently usable on Linux and MacOS (even native with Mac Silicon). For Windows use WSL2 to have a Linux environment. There is no official support for Windows available. See [Lab 1](lab_01.md) for installation instructions.

## Course Aim

This course is an introduction to the design of digital integrated circuits. It covers the basics of digital circuits, the tools used, and the process of designing a chip. The course is based open-source tools and open-source PDKs. The course also gives the possibility for student projects to be taped out on Tiny Tapeout.


## Reading Material

 * Lecture slides and lab material
 * The textbook [CMOS VLSI Design, A Circuits and Systems Perspective](https://www.amazon.com/CMOS-VLSI-Design-Circuits-Perspective/dp/0321547748) by Neil H. E. Weste and David Harris, also available as PDF
 * The material related to the open-source tools we use, including installation instructions: [OpenLean 2 Documentation](https://openlane2.readthedocs.io/en/latest/)
   - For a quick start: [OpenLane 2 in the browser](https://colab.research.google.com/github/efabless/openlane2/blob/main/notebook.ipynb), link is from [efabless](https://efabless.com/openlane)
 * [Caravel Documentation](https://github.com/efabless/caravel)
 * [Chisel Book](https://www.imm.dtu.dk/~masca/chisel-book.html) (as  reference when doing designs in Chisel) 

## Lecture plan

This is the list of lectures from 2025. It will be adapted for 2026. 

### Lecture 1: Introduction to the Chip Design Course (MS) 

- [Lecture slides](01_intro.md)

#### Lecture outline
- Overview of chip design and its importance in modern electronics
- Basic terminology and concepts
- Covering the process (first intro in tool flow)
- AISC with standard cells
- PDK
- Edu4Chip

#### Lab. 1
- Open Source tools (local) installation
- Running a "Hello World" example
- Exploring a standard gate
- See [Lab 1](lab_01.md) for instructions

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

\* quick read, no need to go into details with formulas

### Lecture 3: Verilog and Tiny Tapeout (MS)

- [Lecture slides](03_verilog.md)

#### Lecture outline

- Introduction to Verilog
- Introduction to Tiny Tapeout

#### Lab. 3

- Small Verilog example (programmable counter)
- Write a testbench and run it on post-synthesis
- Do it in Chisel and in Verilog
- Do local hardening
- Simulate post-synthesis
  - probably need a Verilog or cocotb testbench
- Explore Tiny Tapeout with a Verilog project (GitHub based)
- Use the counters in a memory mapped device (maybe)
- See [Lab 3](lab_03.md) for instructions

#### Reading

- Lecture slides (also available as PDF in DTU-Learn)
- Find good Verilog projects and read the code
  - E.g., [YARVI](https://github.com/tommythorn/yarvi)


### Lecture 4: Tool flow and course project (OR) 

- [Lecture slides](04_tools.md)

#### Lecture outline

- Introduction to the OpenLane2 ASIC design flow
- Input preparation and RTL linting
- Synthesis and logic optimization
- Floorplanning and placement
- Clock tree synthesis
- Routing and RC extraction
- Timing signoff and GDSII streaming
- Physical signoff and SDF simulation
- Overview of the course project and group formation

#### Lab. 4

- Discussion about the project
- Form and register groups for the course project
- Choose a project component to work on (CPU, memory, peripherals, testing, etc.)
- Set up the development environment
- Start working on initial design steps
- Register groups in DTU-Learn and sign up in the GitHub repository

#### Reading

- Lecture slides (also available as PDF in DTU-Learn)
- [OpenLane2 Documentation](https://openlane2.readthedocs.io/en/latest/)

This is a large documentation from whih this slide set is based upon, you do not need to read it all. Reference to it when needed.


### Lecture 5: Introduction to Chisel (MS)

- [Lecture slides sources](https://github.com/schoeberl/chisel-book/tree/master/slides-tutorial)
- PDF in DTU Learn (05_Chisel_unit1-4)

#### Lecture outline

- High paced introduction of Chisel

#### Lab. 5

- Lab 1-4 at [chisel-lab](https://github.com/schoeberl/chisel-lab)
- Work on the project

#### Reading

- Lecture slides
- [Digital Design with Chisel](https://www.imm.dtu.dk/~masca/chisel-book.html)

### Lecture 6: SoC, Interconnects, and NoCs (LP)

- [Lecture slides](06_soc_interconnects_noc.md)

#### Lecture outline

- System-on-Chip
- Interconnects
- Networks-on-Chip overview


#### Lab. 6

- Work on the project

#### Reading

- Lecture slides (also available as PDF in DTU-Learn)

### Lecture 7: Student Project Presentations (MS/OR)

Each group presents their project plan and initial developments. This is a key milestone in the course, where you will receive valuable feedback from the teachers and your peers.

Please prepare a short presentation (a few simple slides) for a maximum duration of 10 minutes. Your presentation should include:
- **Objectives:** What are the key goals of your project?
- **Current actions:** What steps have been taken so far?
- **Time plan:** Timeline for development and key milestones.
- **Preliminary design:** Initial architecture, block diagrams, ideas, etc.
- **Implementation and verification plan:** How do you plan to implement and validate your design?
- **Obstacles and open matters:** Are there any obstacles or current problems that prevent moving forward?

After each presentation, we will have a 5 minutes feedback session.

The schedule of the presentations is announced on DTU-Learn. Please note that all groups should be present for all the presentations.

To make the most of the presentation and feedback session, please take into account the following tips:

- Keep your presentation structured and concise.
- Focus on clarity: explain your choices and reasoning.
- Be prepared to engage with feedback and questions.

#### Lab. 7 

- Work on the project


### Lecture 8: Verification (TP) + Midterm course evaluation

- [Lecture slides](08_verification.md)

#### Lecture outline

- Introduction to verification
- Verification methodologies
- Simulation-based verification
- Testbench design
- Assertions and coverage metrics
- Brief overview of formal verification
- Industry Standards, tools, and frameworks in verification

#### Lab. 8

- Finalise implementation of project

#### Reading

- Lecture slides (also available as PDF in DTU-Learn)
- TBA


### Lecture 9: Standard Cells, Memory, and Register Files (MS) OR?

- [Lecture slides](09_std_cells.md)

#### Lecture outline

- Introduction to standard cells
- Role of standard cells in digital design
- Components of a standard cell library
- Types of standard cells (e.g., logic gates, flip-flops)
- Power, performance, and area 
- Characterization of standard cells
- Memory types and memory organization
- Register files: FF, sync mem, latches, custom design
- Memory macros (IP blocks)
- Memory with the SkyWater PDK
- Models and simulation

#### Lab. 9

- Finalise functional implementation of project


### Lecture 10: Chip Design Ruls, Power, and Clock (OR)

- [Lecture slides](???.md) - not yet availble

#### Lecture outline:

- Chip design rules
- Power distribution design, optimization, and analysis
- Digital design timing
- Clock distribution challenges and clock trees
- Power optimization through clock gating
- Timing closure

#### Lab. 10

- Run your though P&R (OR)
- Handle placement and macro insertion


### Lecture 11: Guest lecture - Low-power Chip Design and Implementation (Lars Graversen/Demant)

#### Lecture outline:
- Chip design at Demant
- Industrial design and implementation flow overview
- Design and implementation considerations for low power
- Challenges in lower design nodes (and/or at lower voltages)

#### Lab. 11

- Run routing (OR)
- Fix shorts and design rule violations

#### Reading
- None

### Lecture & Lab 12: Project work

The full time slot is dedicated to project work.

- Run Timing closure and LVS (OR)
- Fix timing violations and handle design missmatches 
- "tape out" your project  


### Lecture 13: Student project presentations (MS/OR)
Each group presents their finalized project and discusses the results.


## Project: DTU-SoC-2025

The course project is focused on designing and implementing a **System-on-Chip (SoC)** using open-source tools. The goal is to collaboratively develop a working SoC that includes a **CPU, memory, peripherals, and essential interfaces**.

The project is hosted at [DTU-SoC-2025](https://github.com/os-chip-design/dtu-soc-2025).

### Group forming

The project should be carried out in **groups of 3 people** (groups of 2 are also possible but less preferred). You are free to select your group members. **Groups should be registered as soon as formed in the DTU-Learn group forming facility.** If you experience difficulties forming a group, please contact the teacher.

When forming a new group, please make sure that you align expectations between the members. To achieve this, we recommend having a discussion about each member’s availability, work habits, and goals for the course to ensure a smooth and collaborative experience.

### Project scope

Each student group will contribute to a part of the SoC. You can choose from the following components:

- **Cache system** for Wildcat
- **Memory controller** (handling SPI-based flash, RAM access, memory-mapped peripherals)
- **Peripherals:**
  - VGA character display
  - Keyboard interface
  - Serial port (UART)
  - GPIO and timer
  - Special IOs (PWM and others)
- **SPI interface** (with optional quad-mode support)
- **Continuous integration** (managing continuous verification after design changes)
- **Verification** (creating testbenches, simulations, and FPGA testing)
- **Physical design tools** (OpenLane2 workflow, synthesis, placement, routing, signoff)
- **Caravel** integration (integrating the design with the Caravel framework)

See Lecture 4 for more details.

### Weekly Coordination

To ensure smooth progress, we will coordinate weekly during the lab sessions. These sessions will be used to:

- Discuss progress and challenges faced by each group
- Provide feedback and guidance on design, implementation, and verification
- Address any issues related to tools and integration
- Keep track of milestones and ensure alignment with the tapeout schedule
- Work on the project

### Report

You are expected to hand-in a **report** describing your desing and your work. The report should be formatted as IEEE paper (IEEEtran template) and not be longer than 4 pages.
In the following, you can find the expected content of the report (not all entries may apply to your project):
  - *Title*
  - *Group number*
  - *Names and student IDs of the group members*
  - *Contributions:* Clearly state what each team member contributed to the project. This section is crucial for evaluating individual contributions and ensuring fair grading.
  - *Introduction*: Introduce your chosen design, outlining its purpose, objectives, and specifications.
  - *Design*: Summarize the key aspects of your design, including its functionality and features. Provide a detailed block diagram to illustrate the overall architecture. Explain how the design fits into the larger system.
  - *Implementation*:
    - Describe how you implemented your design using Verilog, Chisel, or both.
    - Highlight specific steps in the chip design process and explain how open-source tools were used.
    - Include details on performance, and area and any challenges encountered during the implementation.
    - Explain the testing methodology, including simulation results.
    - Describe how you verified the design.
    - Include results from DRC (Design Rule Check), timing analysis, etc. to demonstrate the design's readiness for tapeout.
    - Tapeout preparation: Summarize the final steps taken to prepare the design for tapeout, (integration with the Caravel framework and/or and ensuring compliance with Tiny Tapeout requirements). 
  - *Link/explanantion where to find your code* Explain where your code is located in your repository. Possibly link to a **README file** that includes all needed technical instructions.

The deadline for the hand-in is the **4th of May 2025** at **23:59**.

### Project assessment criteria

- **Relevance and complexity**
  - Does the design address a meaningful challenge?
- **Correctness**
  - Does the implementation function as intended?
  - Are there any critical design flaws?
- **Optimization**
  - Area and performance efficiency considerations
  - Power consumption and design trade-offs
- **Verification quality**
  - Comprehensive testbenches and accurate simulations
  - Pre-synthesis and post-synthesis validation
- **Testing quality**
  - Functional FPGA testing
  - DRC and layout verification
- **Completion**
  - Is the design ready for tapeout?
- **Report quality**
  - Clear documentation of methodology, challenges, and results

### Suggested timeline

- **Week 4**: Form groups, select project, open discussion
- **Week 6**: Finalize desing concept, define specs, align between groups
- **Weeks 7-9**: Develop design, run simulations
- **Weeks 10-11**: Initiate physical design, P&R, Problem strategies
- **Weeks 12-13**: Finalize physical design, complete DRC/LVS checks, prepare for submission

## Exam

Project report and presentation.


## License

All original content in this repository, including text, code, and other materials, is licensed under the CC0 1.0 Universal license (see LICENSE file), unless otherwise noted. 

### License Note for Images

Certain images in this repository are not covered by the CC0 license. These images may be subject to more restrictive copyright terms. Where applicable, copyright and licensing information is provided for these images in the relevant directories, file descriptions, or as text integrated in the images.

## Funding
Funded by the European Union within the *[Edu4Chip](https://edu4chip.github.io/) - Joint Education for Advanced Chip Design in Europe* project. Views and opinions expressed are however those of the author(s) only and do not necessarily reflect those of the European Union or European Health and Digital Executive Agency (HaDEA). Neither the European Union nor the granting authority can be held responsible for them.

<img src="figures/funded_by_the_EU.png" width="300">

