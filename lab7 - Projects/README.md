# Week 7 Exercise  
*Open Source Chip Design Course*  
*July 2024*

---

## Introduction
This week you will choose a project, there will be 3 projects to choose from.

The projects are:
- 7-segment display counter
- Mandlebrot Set VGA Display

For all projects you must:

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

## Before next week
You should have a group before next week, each group should be between 2-3 person. You will begin the assignemnt next week or now if you already have a group.

## 1. 7-segment display counter
In this project you should design a 7-seqment display using verilog or chisel, the display should count up from 0 to F (in hexadecimal) and display the number on a 7-seqment-display. 
The counter should count up once every 1 second given a clock freqeuncy of 1MHz. That is it should count up once every 1 million clock cycles.

The project should utilize non of the input pins and output of pins OUT0 - OUT7. Notice there are 8 outputs due to the ".".

The 7-seq display mapping is displayed:
- 0:   "b00111111"
- 1:   "b00000110"
- 2:   "b01011011"
- 3:   "b01001111"
- 4:   "b01100110"
- 5:   "b01101101"
- 6:   "b01111101"
- 7:   "b00000111"
- 8:   "b01111111"
- 9:   "b01100111"
- 10:  "b01110111"
- 11:  "b01111100"
- 12:  "b00111001"
- 13:  "b01011110"
- 14:  "b01111001"
- 15:  "b01110001"

Where the first binary number (seen from the left) is bound to pin OUT0.

You might need to use the options `firtoolOpts = Array("-strip-debug-info", "--disable-all-randomization", "--lowering-options=disallowPackedArrays")` in Chisel.

## 3. Mandlebrot Set VGA Display 
The Mandlebrot set is a iconic computation. Consider the eqaution:
Zn+1 = Zn + C
Where Z0 = 0
Any complex number C which is a part of the Mandlebrot set will no go towards infinity as the number of itterations goes towards infinity.

This project consists of two parts, firstly you must create a structure which is able to tell if a complex number is stable or unstable, you may assume it is stable if has not gone outside the stability circle in X itterations where X is somewhere between 10 and 20.
If the number is unstable you must color it with some color other then black, if it is stable you must color it black.

Secondly you must create a way to interface with the VGA controller [Tiny VGA](https://github.com/mole99/tiny-vga). You may use 16-bit or 32-bit fixed point representation or 16-bit or 32-bit IEEE 754 floating point representation.
It is recommended to start with a fixed point representation. If you are using a fixed-point representation it should represent the range -2 to 2 for both the real and complex part.

You may use the visual tool to check if you design displays correctly [VGA Playground](https://vga-playground.com/).

The output pins should be assigned:
```
assign uo_out = {hsync, B[0], G[0], R[0], vsync, B[1], G[1], R[1]};
```
Where R0, G0, B0 are the least significant bits and R1, G1, B1 are the most significant bits.


### Optional Assignments
Some optional additions are listed, but feel free to come up with your own ideas.
- Move the point of view
- Zoom
- Color the set based on level of unstability.

The project should be in a state where it should be possiable for you to commit it to tinytapeout and you should be quite sure the project would work.
