# 02118 - Introduction to Chip Design

This repository contains the material for the 13-week course **02118 - Introduction to Chip Design** at the Technical University of Denmark (DTU). This course was developed with support from the **Edu4Chip** project.

This README file contains all the information related to the course. Please **read it carefully** to have an idea of the course structure and expectations.

README content:
* [Practicalities](#practicalities)
* [Course Aim and Learning Objectives](#course-aim-and-learning-objectives)
* [Reading Material](#reading-material)
* [Lecture Plan](#lecture-plan)
* [Project](#project)
* [Exam](#exam)

*Note: The first regular version will be given in Spring 2026. In Spring 2025, the course will be held in a flexible style (like a special course). Specifically, the content will be adapted to the students' needs and interests.*
*Students are expected to contribute to the course actively and help improve the material for the future version of the course.*

## Practicalities
The course runs on **Wednesdays from 13:00 to 17:00** in **Building 308 - Room 017**. 

Each weekly session consist of a lecture and laboratory work. Some session will be fully dedicated to laboratory work (especially at the end of the course when you are expected to work on your project).

The course has two teachers:
 * [Martin Schoeberl](https://www.imm.dtu.dk/~masca/)
 * [Luca Pezzarossa](https://www2.compute.dtu.dk/~lpez/)

You are very welcome to seek for help by approaching the teachers during the letctures and laboratory sessions, or via mail. Also, feedback about the course is much appreciated.

## Course Aim and Learning Objectives
This course is an introduction to the design of digital integrated circuits. It covers the basics of digital circuits, the tools used, and the process of designing a chip. The course is based open-source tools and open-source PDKs. The course also gives the possibility for student projects to be taped out on Tiny Tapeout.

### Learning Objectives

A student who has met the objectives of the course will be able to:
- **Explain** the physics and operation of semiconductor devices, including transistors, and be able to use this knowledge to **design** simple analog and digital circuits.
- **Explain** the fundamentals of memory design, including different types of memories and their organization, and be able to **design** and **analyze** memory subsystems.
- **Explain** the principles of SoC design, including partitioning, floor planning, and individual hardening, and be able to **apply** these principles to the design of a simple SoC.
- **Explain** the principles of multicore SoC design, including the use of NoCs and accelerators, and be able to **apply** these principles to the design of a simple multicore SoC.
- **Explain** the principles of verification in hardware design, including the use of agile hardware design techniques, and be able to **apply** these principles to the verification of a simple SoC design.
- **Explain** the tool flow involved in chip design, including the use of open-source tools, and be able to **use** these tools to design and simulate a simple SoC.
- **Gain** practical experience in the design and simulation of a simple SoC, and experience a virtual tapeout process.
- **Develop** critical thinking and problem-solving skills through the design and analysis of complex digital systems.
- **Develop** effective communication skills, including the ability to present and discuss technical ideas and designs, both orally and in writing.

## Reading Material
 * Lecture slides and lab material (provided weekly).
 * The textbook [CMOS VLSI Design, A Circuits and Systems Perspective](https://www.amazon.com/CMOS-VLSI-Design-Circuits-Perspective/dp/0321547748) by Neil H. E. Weste and David Harris, also available as PDF (run an online search).
 * The material related to the open-source tools we use, including installation instructions: [OpenLean 2 Documentation](https://openlane2.readthedocs.io/en/latest/)
   - For a quick start: [OpenLane 2 in the browser](https://colab.research.google.com/github/efabless/openlane2/blob/main/notebook.ipynb), link is from [efabless](https://efabless.com/openlane)
 * [Caravel Documentation](https://github.com/efabless/caravel)  

## Lecture plan

This is a tentative list of lectures. The course will be adapted to the students' needs and interests.


### Lecture 1: Introduction to the Chip Design Course (MS) 

#### Lecture outline:
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
- See [Lab 1](lab01.md) for instructions

#### Material
- [Lecture slides](???.pdf)


### Lecture 2: The Transistor, the Inverter, and Other Gates (LP)

#### Lecture outline:
 - The need for a controlled switch  
 - A brief history of the transistor  
 - The MOSFET transistor  
 - The NMOS inverter  
 - The CMOS inverter  
 - Other gates  

#### Lab. 2
- Exercise from [SiliWiz](https://tinytapeout.com/siliwiz/), draw and simulate transistors and an inverter
- See [Lab 2](lecture2/lab2.md) for instructions

#### Material
- [Lecture slides](???.pdf)


### Lecture 3: Verilog (MS)

#### Lecture outline:
- Introduction to Verilog
- Small exercises on connecting a module
- Write a testbench and run it on post-synthesis
- Do the same in Chisel

#### Lab. 3
- Explore Tiny Tapeout with a Verilog project (GitHib based)
- Do local hardening
- Simulate post-synthesis

#### Material
- [Lecture slides](???.pdf)


### Lecture 4: Tool flow (LP)

#### Lecture outline:
- Introduction to the OpenLane tool flow
- Synthesis flow
- Logic optimization
- Timing analysis
- Placement and routing
- Post-implementation verification

#### Lab. 4 
- Make a small/medium size design of your choiche (larger than the "Hello World" from Lab. 1.)
- Run the desing through the open-source tools

#### Material
- [Lecture slides](???.pdf) - not yet availble


### Lecture 5: Memory and Register Files (MS) - TENTATIVE

#### Lecture outline:
- Memory types and memory organization
- Register files: FF, sync mem, latches, custom design
- Memory macros (IP blocks)
- Memory in SkyWater (?) 
- Models and simulation

#### Lab. 5 
- TBD

#### Material
- [Lecture slides](???.pdf) - not yet availble


### Lecture 6: SoC, interfaces, and NoCs (LP)
#### Lecture outline:
- Introduction to SoCs 
- Memory-Mapped I/O concepts
- Interfaces: Ready/Valid handshaking, APB, AXI, SPI
- Introduction to NoCs (Networks-on-Chip)
- Building large systems 

#### Lab. 6 
- Decide the desing for the final project
- Define the objectives 
- Compile the design specifications
- Define a verification plan 
- Make a timeplan

#### Material
- [Lecture slides](???.pdf) - not yet availble


### Lecture 7: Student project presentations (MS/LP)

Each group presents the project idea and preliminary design, outlining the chosen concept, the objectives, the verification plan, and the time plan.

The teachers and the other groups give feeback. 

#### Lab. 7 
- Work on the project
- Apply the learning from the lecture in the project

#### Material
- None


### Lecture 8: Verification (TP)

#### Lecture outline:
- Introduction to verification
- Verification methodologies
- Simulation-based verification
- Testbench design
- Assertions and coverage metrics
- Brief overview of formal verification
- Industry Standards, tools, and frameworks in verification

#### Lab. 8
- Work on the project
- Apply the learning from the lecture in the project

#### Material
- [Lecture slides](???.pdf) - not yet availble


### Lecture 9: Standard Cells (MS) - TENTATIVE
#### Lecture outline:
- Introduction to standard cells
- Role of standard cells in digital design
- Components of a standard cell library
- Types of standard cells (e.g., logic gates, flip-flops)
- Power, performance, and area 
- Characterization of standard cells

#### Lab. 9
- Work on the project

#### Material
- [Lecture slides](???.pdf) - not yet availble


### Lecture 10: Chip Design Ruls, Power, and Clock (JS, OR(?), LP(?))

#### Lecture outline:
- Chip design rules
- Power distribution design, optimization, and analysis
- Digital design timing
- Clock distribution challenges and clock trees
- Power optimization through clock gating
- Timing closure

#### Lab. 10
- Work on the project
- Apply the learning from the lecture in the project

#### Material
- [Lecture slides](???.pdf) - not yet availble


### Lecture 11: Submitting your Design to eFabless (LP(?)/MS(?)) - TENTATIVE

#### Lecture outline:
- Overview of eFabless
- The Caravel framework
- How to submit your desing

#### Lab. 11 
- Work on the project
- Apply the learning from the lecture in the project

#### Material
- [Lecture slides](???.pdf) - not yet availble


### Lecture 12: Overview on Analog and Mixed Chip Desing (guest) - TENTATIVE  

#### Lecture outline:
- Introduction to analog design
- Key differences vetween analog and digital design
- Basic building blocks of analog circuits
- Analog layout considerations
- Example: Op. Amp. or ADC/DAC
- Tools and methodologies for analog 
- Challenges in mixed-signal design

#### Lab. 12
- Work on the project

#### Material
- [Lecture slides](???.pdf) - not yet availble


### Lecture 13: Student project presentations (MS/LP)
Each group presents their finalized project and discuss the results.


## Project
The project aims to provides you with an opportunity to apply the concepts, tools, and techniques learned throughout the course to design, implement, verify, and prepare a medium/small digital design for tapeout using open-source tools. 

### Group forming
The project should be carried out in **groups of 3 people** (groups of 2 are also possible but less preferred). You are free to select your group members. **Groups should be registered as soon as formed in the DTU-Learn group forming facility.** If you experience difficulties forming a group, please contact the teacher.

When forming a new group, please make sure that you align expectations between the members. To achieve this, we recommend having a discussion about each member’s availability, work habits, and goals for the course to ensure a smooth and collaborative experience.


### Ideas
You can choose your own project idea based on your interests. Some suggestions include:

- Designing an SRAM module for Tiny Tapeout
- Contributing a component for the Edu4Chip tapeout, such as a small accelerator or subsystem
- Creating a custom-designed C-gate standard cell
- Developing an efficient register file or other fundamental digital block
- VGA-based videogame
- More suggestion will come later

### Project Scope
The project involves the following tasks:

-Design selection and implementation
  - Choose a design
  - Define objectives and specifications
  - Implement the design using Verilog or Chisel

- Verification and testing
  - Create a testbench to verify the functionality of the design pre-synthesis
  - Perform post-synthesis and post-layout simulations to ensure correctness
  - Test using an FPGA

-Physical design
  -Use the OpenLane toolchain to run the design through the synthesis, placement, routing, and verification processes.

Tapeout preparation
  - Prepare the design for submission to eFabless or Tiny Tapeout, ensuring it meets the requirements.

### Deliverables
You are expected to hand-in the following deliverables:
- **Report:** A report describing your desing and your work. The report should be formatted as IEEE paper and not be longer than 6 pages

In the following, you can find the expected content of the report:
  - *Title*
  - *Group number*
  - *Names and student IDs of the group members*
  - *Contributions:* Clearly state what each team member contributed to the project. This section is crucial for evaluating individual contributions and ensuring fair grading.
  - *Introduction*: Introduce your chosen design, outlining its purpose, objectives, and specifications. Explain the type of system you aimed to design (and its potential applications).
  - *Design*: Summarize the key aspects of your design, including its functionality and features. Provide a detailed block diagram to illustrate the overall architecture. If needed, explain how the design fits into a larger system.
  - *Implementation*:
    - Describe how you implemented your design using Verilog, Chisel, or both.
    - Highlight specific steps in the chip design process and explain how open-source tools were used.
    - Include details on performance, and area and any challenges encountered during the implementation.
    - Explain the testing methodology, including simulation results.
    - Describe how you verified the design.
    - Include results from DRC (Design Rule Check), timing analysis, etc. to demonstrate the design's readiness for tapeout.
    - Tapeout preparation: Summarize the final steps taken to prepare the design for tapeout, (integration with the Caravel framework and/or and ensuring compliance with Tiny Tapeout requirements). 

- **Source files:** All the source code of your implementation (and tests). It is fine to just have a link to a repository. 

- **README file:** A README file that includes instructions on how to set up and run your processor on the FPGA, as well as how to run any test cases you developed.

The deadline for the hand-in is **TBD** at **midnight**.

### Project assessment criteria
- **Relevance and complexity**: Is the desing releavnt and sufficiently complex?
- **Correctness**: Does the design function as intended?
- **Optimization**: Is the design efficient in terms of area and performance?
- **Verification quality**: Are the testbenches comprehensive and simulations accurate?
- **Testing quality**: Was the desing properly tested?
- **Completion**: Is the design ready for tapeout?
- **Report quality**: Does the report properly convey what was acheived?

### Suggested timeline
- **Weeks 6**: Finalize the project concept, define objectives, and begin initial design and verification planning.
- **Weeks 7-11**: Develop the design, perform simulation and verification, and initiate the physical design process.
- **Weeks 12-13**: Finalize physical design, complete DRC/LVS checks, and prepare the design for submission.

## Exam

Project + written or oral examination. More information about the exam will come later.
