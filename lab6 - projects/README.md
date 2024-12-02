# Week 6 Exercise  
*Open Source Chip Design Course*  
*July 2024*

---

## Introduction
This is a special week as it is the last of these weekly assignments, you must complete the Hand-in Assignment and find a group for next week where you can begin your final project.

## Assignment
For the rest of the course you there will be no more weekly assignments, instead you will work on the followingly presented project.

You must find a team of 2-3 people to complete the project.

### Mandlebrot Set VGA Displayer
The Mandlebrot set is a iconic computation. Consider the eqaution:
Zn+1 = Zn + C
Where Z0 = 0
Any complex number C which is a part of the Mandlebrot set will no go towards infinity as the number of itterations goes towards infinity.

This project consists of two parts, firstly you must create a structure which is able to tell if a complex number is stable or unstable, you may assume it is stable if has not gone outside the stability circle in X itterations where X is somewhere between 10 and 20.
If the number is unstable you must color it with some color other then black, if it is stable you must color it black.

Secondly you must create a way to interface with the VGA controller [Tiny VGA](https://github.com/mole99/tiny-vga). You may use 16-bit or 32-bit fixed point representation or 16-bit or 32-bit IEEE 754 floating point representation.
It is recommended to start with a fixed point representation. If you are using a fixed-point representation it should represent the range -2 to 2 for both the real and complex part.

You may use the visual tool to check if you design displays correctly [VGA Playground](https://vga-playground.com/) or [VGA simulation](https://github.com/SamanMohseni/VGA-Simulation).

The output pins should be assigned:
```
assign uo_out = {hsync, B[0], G[0], R[0], vsync, B[1], G[1], R[1]};
```
Where R0, G0, B0 are the least significant bits and R1, G1, B1 are the most significant bits.

You will have 4 clock cycles per pixel output. A 100 MHz clock will be advaliable, and the VGA output is clocked at 25Mhz.

### 1. Design
You must:
- Provide a working chisel/verilog code.
- Show that the project works by tests and simulating. The Simulations should be displayed using GTK-wave.
- Provide an in-depth tour of the design and your ideas.
- Provide an overview of the physical design.
- Pass all the github actions tests.

You must awnser:
- What is the max clock frequency you can operate at.
- What is the limiting factor (for increasing clock frequency) area or speed? and can you trade one for the other.
- How much power do you consume?

The project should be in a state where it should be possiable for you to commit it to tinytapeout and you should be quite sure the project would work.

Some optional additions are listed, but feel free to come up with your own ideas.
- Color: Color the set based on level of unstability (itterations).
- Zoom: Allow the ability to zoom using button inputs.
- Move: Allow the ability to move the point of view using button inputs.

### 2. Exploring design contrants and parameters
You are expected to explore the openlane2 GUI to awnser the following questions. 

1. In the openlane GUI find the resistance of the metal 1, 3 and 5. Do they differ and why?
2. Find the capacitance of metal 1, 2, 3, 4 and 5, give a reasoning for why they are different.
3. Ponder on what the capatance of the different metal layers couble to, you do not have to be correct here, but your argumentation should be sound.
4. What is edge capacitance?
5. What is the minimum area of metal 2, 4 and 5.
6. Give a describtion of why there is a minimum area, why can't you just make it smaller? (this is releated to the fabrication process of ICs)
7. what is the resistance of via 1, 2, 3, 4 and 5.
8. Find pin "uo_out[3]", "uo_out[7]" and "uio_out[4]", povide an image and comment if you see something interesting.
9. Estimate the resistence from the edge of the design down to a gate in the center of your design, assume perfect voltage source at the edge of the design.
10. Disable "physical instances" and "nets" to observed the space taken up by your logic cells. Provide an image a some comments.
11. Find and go to "12-openroad-staprepnr/summary.rpt" to see "Reg to Reg paths" and "Setup	Worst Slack" report your findings. 
12. Find and go to "openroad-globalplacementskipio.log" to see utilization procentage, report your findings.

### 2. Notes and help
You might want to visit [VGA spec](http://javiervalcarce.eu/html/vga-signal-format-timming-specs-en.html) and [Tiny VGA spec](http://www.tinyvga.com/vga-timing/640x480@60Hz), this is explains the VGA protocol, but note that you will not be interfacing directly with the VGA protocol, but rather the [Tiny VGA](https://github.com/mole99/tiny-vga), which is a somewhat simplified version. You will only have 2 bit of color per channel.

I would recommend that you write first write a purely combinatoric version of a Mandlebrot simulator and then afterwards you may make it more compicated but dividing the computation over multiple clock cycles.
I would also recommend that you write the code in a way that allows you to change the number of bits you operate with, you may want to try different configuration of fractional bit.

You can find a "DisplayTester.scala" and "VgaSyncGenerator.scala" which will help you get started. The "DisplayTester.scala" will output an image with the resulting image.

## Hand-in Assignment
You should submit a document (preferably LaTeX) that answers the following questions. See assignment on learn.

A note on using TinyTapeout and openlane2: When you view your design in openlane2, you are not viewing the extect same that would be commited to TinyTapeout, you are just viewing a implementation of the circuit. 
The design implementation might change if you where to submit it to TinyTapeout, so all analysis you do in your local openlane2 installation should be taken as a approximation of what would be fabricated by TinyTapeout. 

### 1. Exploring the layout
In this assignment, you will be exploring the layout generated by OpenLane2. You are required to visit and read the following page: [OpenLane2 Configuration Reference](https://openlane2.readthedocs.io/en/latest/reference/configuration.html).
You should explore the 7-seqment display you made last week.

1. Open the KLayout viewer using make.
   You are now expected to show the power strips; you may disable or enable viewable layers in the "Layers." You can use "Hide all" and then the metal layer in which the power strips reside. They are called "met*.drawing."
   Display the power strips in your assignment.
2. Describe what a power strip is.
3. Locate the signal pins; these also reside on a metal layer. You shall outline the pins in a program like *Paint* or *GIMP*.
4. How big are the pins? (you must meassure them)
5. !?!??! Now locate and outline the power pins. If they are not there, you might want to read the docs.
6. !?!??! Change the die area to "" and core area. 
7. Describe what die area is.
8. Describe what core area is.
