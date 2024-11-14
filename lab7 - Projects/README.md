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

### Assignment
You must awnser:
- What is the max clock frequency you can operate at while still only counting up once every 1 second
- What is the limiting factor (for increasing clock frequency) area or speed? and can you trade one for the other.
- How much power do you consume?
	
The project should be in a state where it should be possiable for you to commit it to tinytapeout and you should be quite sure the project would work.


## 3. Mandlebrot Set VGA Display 

	
