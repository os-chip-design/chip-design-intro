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
- **MOSFETs are the foundation of:**
  - Microprocessors
  - Memory chips
  - Digital logic circuits
  - ...

## The Impact of the MOSFET

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

- **Two Types**
  - NMOS: Uses electrons as charge carriers
  - PMOS: Uses holes as charge carriers

## Symbols
**Placeholder**: Symbols for NMOS and PMOS transistors.


## How MOSFETs Work

- **Gate Voltage**:
  - Controls whether current flows between the Source and Drain.
- **Regions of Operation**:
  1. Cutoff: \( V_{GS} < V_{th} \), no current.
  2. Linear: \( V_{DS} \) small, current proportional to \( V_{DS} \).
  3. Saturation: \( V_{DS} > V_{GS} - V_{th} \), maximum current.

**Placeholder**: Graph of MOSFET transfer characteristics.


# Why is the MOSFET Important?

- **Advantages**:
  - High input impedance.
  - Low power consumption.
  - Scalability for ICs.
- **Significance**:
  - Foundation of modern integrated circuits (ICs).
  - Enables billions of transistors on a single chip.

**Full Image Placeholder**: Comparison of a single MOSFET and a chip with billions of transistors.

---

# MOSFET Structure

## The Components of a MOSFET

1. **Gate**:
   - Controls current flow between Source and Drain.
2. **Source**:
   - Starting point of current flow.
3. **Drain**:
   - Endpoint of current flow.
4. **Substrate (Body)**:
   - The semiconductor base material.

**Placeholder**: Cross-sectional diagram of a MOSFET showing Gate, Source, Drain, and Substrate.

---

# MOSFET Symbols

## NMOS and PMOS

- **NMOS**: Current flows through electrons (negative charge).
- **PMOS**: Current flows through holes (positive charge).

**Placeholder**: Circuit symbols for NMOS and PMOS transistors.

---

# How Does a MOSFET Work?

## Basic Principle

- **Voltage at the Gate**:
  - Controls a channel between Source and Drain.
- **Key Mechanism**:
  - Gate voltage creates an electric field, inducing a conductive channel.

**Placeholder**: Diagram of Gate voltage forming a conductive channel.

---

# Modes of Operation

## MOSFET Regions of Operation

1. **Cutoff**:
   - $ V_{GS} < V_{th} $: No current flows.
2. **Linear (Ohmic)**:
   - $ V_{GS} > V_{th}, V_{DS} < V_{GS} - V_{th} $: Current flows proportionally to $ V_{DS} $.
3. **Saturation**:
   - $ V_{GS} > V_{th}, V_{DS} > V_{GS} - V_{th} $: Current is constant.

**Placeholder**: Graph of $ I_D $ vs. $ V_{DS} $ showing the regions.

---

# MOSFET Formulas

## Key Equations

1. **Cutoff Region**:
   $$ I_D = 0 $$
2. **Linear Region**:
   $$ I_D = k \cdot \left( V_{GS} - V_{th} \right) \cdot V_{DS} $$
3. **Saturation Region**:
   $$ I_D = \frac{k}{2} \cdot \left( V_{GS} - V_{th} \right)^2 $$

- $ k = \frac{\mu_n C_{ox} W}{L} $: Process and geometry-dependent constant.

**Placeholder**: Graph of $ I_D $ vs. $ V_{GS} $ showing the regions.

---

# MOSFET Transfer Characteristics

## Behavior of $I_D$ vs. $V_{GS}$

- **Threshold Voltage ($ V_{th} $)**:
  - Minimum Gate voltage needed for conduction.
- **Current Increases**:
  - Exponentially beyond $ V_{th} $.

**Placeholder**: Transfer curve showing $ I_D $ vs. $ V_{GS} $.

---

# Capacitance in MOSFETs

## Gate Capacitance

- **Effect**:
  - Limits switching speed.
- **Types**:
  1. Overlap Capacitance.
  2. Channel Capacitance.

**Placeholder**: Diagram illustrating Gate capacitance in a MOSFET.

---

# Leakage in MOSFETs

## Sources of Leakage

1. **Subthreshold Leakage**:
   - Current flows even when $ V_{GS} < V_{th} $.
2. **Gate Leakage**:
   - Caused by tunneling through the thin oxide layer.

**Placeholder**: Diagram showing leakage paths in a MOSFET.

---

# Fabrication of MOSFETs

## The Process of Building a MOSFET

1. **Doping**:
   - Create Source and Drain regions.
2. **Oxide Layer**:
   - Insulating layer formed on silicon.
3. **Metal Contacts**:
   - Deposited for Gate, Source, and Drain connections.

**Placeholder**: Step-by-step diagram of MOSFET fabrication.

---

# Advantages of MOSFETs

- **Scalability**:
  - Smaller feature sizes enable high transistor density.
- **Low Power**:
  - Ideal for battery-operated devices.
- **High Speed**:
  - Fast switching for high-frequency applications.

**Placeholder**: Comparison of older and modern chips enabled by MOSFET scaling.

---

# Disadvantages of MOSFETs

- **Leakage Current**:
  - Increases with smaller dimensions.
- **Heat Management**:
  - More transistors generate more heat.
- **Threshold Voltage Variability**:
  - Affected by manufacturing variations.

**Placeholder**: Diagram showing challenges like leakage and heat in modern MOSFETs.

---



---

# The MOSFET Transistor

## Building a MOSFET in Silicon

1. Start with a silicon wafer.
2. Doping creates Source and Drain regions.
3. Thin oxide layer forms the Gate insulator.
4. Apply metal layers for Gate, Source, and Drain.

**Placeholder**: Diagram of MOSFET fabrication process.

---

# The NMOS Inverter

## Overview

- Single NMOS transistor with a pull-up resistor.
- Operation:
  - Input LOW: Output HIGH (resistor pulls up).
  - Input HIGH: Output LOW (NMOS conducts).

**Placeholder**: Circuit diagram of an NMOS inverter.

---

# The NMOS Inverter

## Drawbacks

- High power consumption in the HIGH state:
  - Current flows through the resistor.
- Slower operation due to resistive pull-up.

**Placeholder**: Illustration showing power dissipation in an NMOS inverter.

---

# The CMOS Inverter

## Overview

- **Complementary MOS (CMOS)**:
  - Combines NMOS and PMOS transistors.
- Operation:
  - Input LOW: PMOS ON, NMOS OFF → Output HIGH.
  - Input HIGH: NMOS ON, PMOS OFF → Output LOW.

**Placeholder**: Circuit diagram of a CMOS inverter.

---

# The CMOS Inverter

## Advantages of CMOS

- **No static power dissipation**:
  - Only consumes power during switching.
- **Fast switching**:
  - Limited by gate capacitances.

**Placeholder**: Graph of CMOS inverter transfer characteristics.

---

# The CMOS Inverter

## Voltage and Temperature Effects

- CMOS operates over a wide voltage range.
- Performance degrades at high temperatures:
  - Leakage current increases.
  - Switching speed decreases.

**Placeholder**: Graph showing temperature dependence of CMOS performance.

---

# Simple Gates

## Building Logic Gates with CMOS

- **NAND Gate**:
  - Series NMOS and parallel PMOS transistors.
- **NOR Gate**:
  - Parallel NMOS and series PMOS transistors.

**Placeholder**: Circuit diagrams of CMOS NAND and NOR gates.

---

# Simple Gates

## Standard Cells and Custom Circuits

- **Standard Cells**:
  - Predefined layouts for basic gates (NAND, NOR, etc.).
  - Simplify chip design.
- **Custom Circuits**:
  - Tailored for specific applications.

**Placeholder**: Example of a standard cell layout.

---

# Summary

## Key Takeaways

- **Controlled Switches**: Foundation of digital circuits.
- **MOSFET**: Scalable, energy-efficient, and reliable.
- **CMOS**: Power-efficient design for logic gates.
- **Logic Gates**: Built from CMOS for high performance.

---

# Thank You!

## Questions?

## Lab

 * Silliwiz doing the whole thing
 * Maybe also magic? It is more painful


