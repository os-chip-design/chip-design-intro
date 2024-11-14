# Week 7 Exercise  
*Open Source Chip Design Course*  
*July 2024*

---

## Introduction
This week you will choose a project, there will be 3 projects to choose from.

The projects are:
	- 7-segment display counter
	- SHA265 Hasher
	- Mandlebrot Set VGA Display

## Before next week
You should have a group before next week, each group should be between 2-3 person. You will begin the assignemnt next week or now if you already have a group.

### 1. 7-segment display counter
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
	
#### Assignment
	You must:
		- Provide a working chisel/verilog code.
		- Show that the project works by tests and simulating. The Simulations should be displayed using GTK-wave.
		- Provide an overview of the physical design.
		- Pass all the github actions tests.
		
	The project should be in a state where it should be possiable for you to commit it to tinytapeout and you should be quite sure the project would work.
	
### 2. SHA265 Hasher
	In this project you must construct A SHA265 (Secure Hash Algorithm 256-bit) hasher is a cryptographic hash function that produces a fixed-size output (256 bits) from an input of any length. The key property of a cryptographic hash function like SHA-256 is that it maps any input data to a fixed-length hash (256 bits) in a way that is computationally infeasible to reverse.
	
	The SHA-256 algorithem processes input data in blocks of 512 bits (64 bytes). 
	The first step of the SHA-256 is "Preprocessing", this step invloves 3 sub-steps,which are "padding the message", "parsing the message into message blocks" and "setting the initial hash value".
	To create a blocks of data which is required for the next step. The next step is "Hash Computation" which computes the resulting hash.
	You may find more information here: [SHA256 specification](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.180-4.pdf).
	
#### Assignment
	You must:
		- Provide a working chisel/verilog code.
		- Show that the project works by tests and simulating. The Simulations should be displayed using GTK-wave.
		- Provide an overview of the physical design.
		- Pass all the github actions tests.
		
	The project should be in a state where it should be possiable for you to commit it to tinytapeout and you should be quite sure the project would work.
	

### 3. Mandlebrot Set VGA Display 
	
		
