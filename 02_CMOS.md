---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #fff
<!--backgroundImage: url('https://marp.app/assets/hero-background.svg')-->
---

<!-- headingDivider: 3 -->

# **The Tranistor, the Inverter, <br>and Other Gates**

**Luca Pezzarossa and Martin Schoeberl**

## Agenda

1. The need for a controlled switch  
2. A brief history of the transistor  
3. The MOSFET transistor  
4. The NMOS inverter  
5. The CMOS inverter  
6. Other gates  

# <br><br>The need for a controlled switch


# Information representation

- **Binary number representation** 
  - Foundation of digital computing
  - Foundation of digital electronics

- **Physical levels** 
  - Logic 1: High voltage (5V, 3.3V, 1.8V, or others.)
  - Logic 0: Low voltage (e.g., 0V).

- **A sort of 'controlled switch' is needed to perfrom computations**


# Controlled switch in a circuit

**Image placeholder**: Not gate with resistor and controlled switch
![width:850](./lecture_02/test.jpg)


# Early technologies: Relays

- **Mechanical relays**:
  - Controlled by electromagnets.
  - Early computers used relays for logic operations.


# Early technologies: Relays

**Image placeholder**: Relay photo, relay in the not gate


# Early technologies: Relays
- **Advantages**:
  - Easy to build
  - Support high loads

- **Disadvantages**:
  - Slow switching speed
  - Prone to mechanical wear
  - Loud

# Early technologies: Vacuum tubes

- **Vacuum tubes**:
  - Used thermionic emission to control current flow.

**Image placeholder**: Diagram and thermoionic effect.


# Early technologies: Vacuum tubes
**Image placeholder**: Tube photo.


# Early technologies: Vacuum tubes
- **Advantages**:
  - No moving parts
  - Faster than relays

- **Disadvantages**:
  - Bulky and fragile
  - High power consumption
  - Excessive heat generation


# EINAC (1946)
**Image placeholder**: EINAC


# Transition to solid-state devices

- **The need for improvement**
  - Relays and vacuum tubes were not scalable
  - High power and size limitations
- **Solution** 
  - Solid-state devices transistors
  - Smaller, faster, cheaper, and more reliable

# Transition to Solid-State Devices
**Image placeholder**: Side-by-side comparison of a vacuum tube and a transistor.



# <br><br>A brief history of the transistor

## The point-contact transistor

- **The first transistor**
<br>
- **Bell Labs (1947)**
  - by John Bardeen, Walter Brattain, and William Shockley
<br>
- **Goal** 
  - Replace vacuum tubes with a solid-state solution

## The point-contact transistor
**Image placeholder**: the first point-contact transistor.


## The point-contact transistor

- **Structure**: Germanium crystal with two gold contacts
- **Functionality**: Amplified electrical signals
- **Limitations**: Fragile and difficult to manufacture

- **The science behind it was now demostrated!**


## The Bipolar Junction Transistor (BJT)

- **Bell Labs (1948)**
  - by William Shockley
- **Structure**: Three semiconductor layers
- **Amplifies a current**
- **Two types**: (NPN or PNP)
- **Advantages**: More robust and manufacturable than point-contact transistors
- **Applications**: Radios, early computers, and amplifiers

## The Bipolar Junction Transistor (BJT)

**Image placeholder**: Diagram of an NPN transistor and a photo of BJTs...

## Transition from Germanium to Silicon

- **Germanium was initially used but had limitations**
  - Unstable at high temperatures
  - Stability means maintain its properties and performance at different temperatures
- **Silicon emerged as the better material**
  - Abundant and thermally stable
- **First silicon transistor** 
  - 1954 by Texas Instruments


## The Metal-Oxide-Semiconductor Field-Effect Transistor (MOSFET)

- **Bell Labs (1959)**
  - by Mohamed Atalla and Dawon Kahng
- **Structure**: More later...
- **Advantages**
  - Scalable for miniaturization
  - Low power consumption

## The Impact of the MOSFET

- **Enabled the development of integrated circuits (ICs)**.
<br>
- **MOSFETs are the foundation of:**
  - Microprocessors
  - Memory chips
  - Digital logic circuits
  - ...

## The impact of the MOSFET

**Image placeholder**: Diagram of an integrated circuit highlighting MOSFETs.

## The Integrated circuit revolution

- **First ICs contained only a few transistors**
- **Moore's Law (1965)**:
  - Predicted the doubling of transistors on a chip every two years.


- **1971**: Intel 4004 (First microprocessor)
  - 2,300 transistors.
- **2020s**: Modern processors
  - Billions of transistors.

## Evolition of transistors in microprocessors

**Placeholder**: Intel 4004 vs Modern processors


## FinFET and 3D transistors

- **Introduced for sub-20nm technologies**
  - FinFET (2010s)
  - Gate-All-Around Transistors (2020s)

## FinFET and 3D transistors
**Placeholder**: Diagram of FinFET and Gate-All-Around designs


# Timeline summary

1947 --> **Point-Contact Transistor**: First working transistor
<br>1948  --> **BJT**: Improved, smaller, producilble
<br>1959  --> **MOSFET**: Modern IC technology
<br>2010s -> **FinFET**: Sub-20nm technology
<br>2020s -> **Gate-All-Around Transistors**


# <br><br>The MOSFET transistor

## Generalities

- **Metal-Oxide-Semiconductor Field-Effect Transistor (MOSFET)**
  - Most widely used transistor type today
<br>
- **Advantages**
  - High input impedance
  - Low power consumption
  - Scalability for ICs


## Generalities
- **Two Types**
  - NMOS: Uses electrons as charge carriers
  - PMOS: Uses holes as charge carriers

- **Pins**
  - **Gate**: Controls current flow between Source and Drain.
  - **Source**: Starting point of current flow.
  - **Drain**: Endpoint of current flow.
  - **Substrate (Body)**: The semiconductor base material (**omitted**).

## Symbols
**Placeholder**: Symbols for NMOS and PMOS transistors.


## How MOSFETs Work

- **Gate Voltage**:
  - Controls whether current flows between the Source and Drain
- **Regions of Operation**:
  1. Cutoff: $V_{GS} < V_{th}$, no current
  2. Linear: $V_{DS}$ small, current proportional to $V_{DS}$
  3. Saturation: $V_{DS} > V_{GS} - V_{th}$, maximum current

**Placeholder**: Graph of MOSFET transfer characteristics.


## MOSFET Structure

**Placeholder**: Cross-sectional diagram of a MOSFET showing Gate, Source, Drain, and Substrate.


## Working basic principle

- **Voltage at the Gate**:
  - Controls a channel between Source and Drain.

- **Key Mechanism**:
  - Gate voltage creates an electric field, inducing a conductive channel.

## MOSFET Structure

**Placeholder**: Blackboard.


## MOSFET regions of operation

1. **Cutoff**:
   - $V_{GS} < V_{th}$: No current flows
2. **Linear (Ohmic)**:
   - $V_{GS} > V_{th}, V_{DS} < V_{GS} - V_{th}$: Current flows <br> proportionally to $V_{DS}$
3. **Saturation**:
   - $V_{GS} > V_{th}, V_{DS} > V_{GS} - V_{th}$: Current is constant

$V_{th}$ is the Gate  threshold voltage needed for conduction

## MOSFET regions of operation

1. **Cutoff**:
   $I_D = 0$
2. **Linear (Ohmic)**:
   $I_D = k \cdot \left( V_{GS} - V_{th} \right) \cdot V_{DS}$
3. **Saturation**:
   $I_D = \frac{k}{2} \cdot \left( V_{GS} - V_{th} \right)^2$

$k = \frac{\mu_n C_{ox} W}{L}$ : Process and geometry-dependent constant

## MOSFET Transfer Characteristics

**Placeholder**: Graph of $ I_D $ vs. $ V_{GS} $ showing the regions.


## Capacitance in MOSFETs

- **Reason**:
  - Proximity of components where there is a potential difference

- **Effect**:
  - Limits switching speed


## Capacitance in MOSFETs

**Placeholder**: Diagram illustrating Gate capacitance in a MOSFET


# Leakage in MOSFETs
- **Subthreshold leakage**
   - Current flows even when $V_{GS} < V_{th}$
- **Gate leakage**
   - Caused by tunneling through the thin oxide layer
- **Other leakages**
   - Anywhere there is a potential difference


# Leakage in MOSFETs
**Placeholder**: Diagram showing leakage paths in a MOSFET.


## Fabricating a MOSFET in silicon

**Placeholder**: Diagram of MOSFET fabrication process showing the steps.



# <br><br>The NMOS inverter (NOT gate)

## The NMOS Inverter

- Single NMOS transistor with a pull-up resistor
<br>
- **Operation**
  - Input LOW: Output HIGH (resistor pulls up).
  - Input HIGH: Output LOW (NMOS conducts).

## The NMOS Inverter

**Placeholder**: Circuit diagram of an NMOS inverter.


# The NMOS Inverter

- **High power consumption in the 'output LOW' state**
  - Current flows through the resistor
<br>
- Slower operation due to resistive pull-up


## The CMOS Inverter

- **Complementary MOS (CMOS)**:
  - Combines NMOS and PMOS transistors

- Operation:
  - Input LOW: PMOS ON, NMOS OFF → Output HIGH.
  - Input HIGH: NMOS ON, PMOS OFF → Output LOW.

## The CMOS Inverter

**Placeholder**: Circuit diagram of a CMOS inverter.


## The CMOS Inverter

- **No static power dissipation**
  - Only consumes power during switching.

- **Fast switching**
  - Limited by gate capacitances.


## The CMOS Inverter

**Placeholder**: Circuit diagram of a CMOS inverter in silicon.


# <br><br> Simple gates

## Building logic gates with CMOS

- **NAND Gate**:
  - Series NMOS and parallel PMOS transistors.

- **NOR Gate**:
  - Parallel NMOS and series PMOS transistors.


## Building logic gates with CMOS

**Placeholder**: Circuit diagrams of CMOS NAND and NOR gates.

## Building logic gates with CMOS

**Placeholder**: SILICON OF NAND / NOR


## Standard Cells and Custom Circuits

- **Standard Cells**:
  - Predefined layouts for basic gates (NAND, NOR, etc.).
  - Simplify chip design.
<br>
- **Custom Circuits**:
  - Tailored for specific applications.


## Standard Cells and Custom Circuits
**Placeholder**: Example of a standard cell layout.


# <br><br>Summary

## Key Takeaways

- **Controlled Switches**: Foundation of digital circuits.
- **MOSFET**: Scalable, energy-efficient, and reliable.
- **CMOS**: Power-efficient design for logic gates.
- **Logic Gates**: Built from CMOS for high performance.



# <br>End
  **Laboratory** 
  - All exercises form [SiliWiz](https://tinytapeout.com/siliwiz/)
  - See Laboratory 2 file
  - Feel free to skip some if you are confident witht the topic



