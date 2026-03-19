---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #fff
---

<!-- headingDivider: 3 -->

# **Memories**

**02118 - Introduction to Chip Design**

![bg right:27% 550%](https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/Intel%40intel7%2810nmESF%29%40RaptorLake%40RPL%288P%2B16E%29%40i9-13900K%40ES_DSCx05_poly%405xExt.jpg/3840px-Intel%40intel7%2810nmESF%29%40RaptorLake%40RPL%288P%2B16E%29%40i9-13900K%40ES_DSCx05_poly%405xExt.jpg)

### Memories in general

- Memory is where our data and programs are stored
- Machine Learning: models are 100s of GB
- Data has to move between memory and compute units
- Performance is limited by memory access latency and bandwidth
- On-chip memory is faster and can be physically close to compute units
- But on-chip memory competes with compute units for area and power

### On-Chip Memory (i9-13900K 10nm)

- L1: 
  - 80KB/P-Core
  - 96KB/E-Core
- L2:
  - 2MB/P-Core
  - 4MB/(4 E-Cores)
- L3: 36MB (shared)
- Total: ≈70MB!
![bg right:57% width:98%](https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Intel_Core_i9-13900K_Labelled_Die_Shot.jpg/1920px-Intel_Core_i9-13900K_Labelled_Die_Shot.jpg)

### Lecture Overview
- How can we implement memory on-chip?
  - From bad to good
  - Registers
  - Latches
  - SRAM cell and its operation
  - Memory arrays


### Register based memory

![bg vertical right:40% width:100%](https://upload.wikimedia.org/wikipedia/commons/3/37/D-Type_Flip-flop_Diagram.svg)
![bg right:40% width:100%](figures/memory/reg_mem.drawio.svg)


- Simplest way to store data: use DFF
- Area: $7.360\:\mu\mathrm{m} \times 2.720\:\mu\mathrm{m}$
- What else do we need?
  - Address decoding to select registers
  - Address register for synchronous read
  - Multiplexers to select read data

<!-- _footer: <a href="https://commons.wikimedia.org/w/index.php?curid=873697">DFF schematic by jjbeard</a>-->

### Naive Register File

- Hardened with librelane
- $764.59\:\mu\mathrm{m} \times 775.31\:\mu\mathrm{m}$
- 13,819 bits/mm²
- For 70MB: $19.96 \mathrm{cm}\times 19.96\:\mathrm{cm}$
- In 10nm assuming 18x scaling: $1.1\:\mathrm{cm}\times 1.1\:\mathrm{cm}$
- Not very good, we need to do better!

![bg right:40% width:100%](figures/memory/synthram.png)


### Latch

- Would be enough to store a bit
- How can we integrate a level-sensitive latch into a edge-sensitive clocked circuit?
- Use clock as enable signal
- How does the timing look?

![bg vertical right:40% width:100%](https://upload.wikimedia.org/wikipedia/commons/2/2f/D-Type_Transparent_Latch.svg)
![bg right:40% width:100%](https://skywater-pdk.readthedocs.io/en/main/_images/sky130_fd_sc_hd__dlxtp_1.svg)
 
<!-- _footer: Figure by <a href="//commons.wikimedia.org/wiki/User:Inductiveload" title="User:Inductiveload">Inductiveload</a> - Own Drawing in Inkscape 0.46, Public Domain, <a href="https://commons.wikimedia.org/w/index.php?curid=873561">Link</a>> -->


### Latch Write Timing

- Transparent latch when `clk=0`
- Clock is gated with write enable and address select
- Data needs to be ready before *falling* clock edge
- Data needs to be valid until after *rising* edge
- Long setup time

![bg right:40% width:100%](figures/memory/latch_timing.svg)

### Latch Read

- Combinational path through memory on write

### Latch based memory

- we can use clock as enable to store data in a latch
- but data has to be valid for a whole half period
- which period to choose?
- latch write data and address when clock low, update target latch when clock high
- read data using registered address
- what if the same address is written and read in the same cycle? combinational path through memory!


### DFFRAM

![bg right:40% width:100%](figures/memory/dffram.png)

- Area: $683.1\:\mu\mathrm{m} \times 416.54\:\mu\mathrm{m}$
- Density: $34.7\:\mu\mathrm{m}^2/\mathrm{bit}$
- 28.790,4826536745 bits/mm²

### How else can we store data?

![bg right:40% width:80%](figures/memory/inv_cell.drawio.svg)

- Cross-coupled inverters
- Two stable states: 0/1, 1/0, (and meta-stable)
- Force either into 0/1 or 1/0 to store a bit
- How can we combine multiple cells?

### Shared Bitlines

![bg right:40% width:90%](figures/memory/shared_bit_line.drawio.svg)

### 6T SRAM Cell

![bg right:40% width:100%](https://upload.wikimedia.org/wikipedia/commons/3/31/SRAM_Cell_%286_Transistors%29.svg)

- Shared bitlines between cells
- 2 nmos transistors connect to shared bitlines
- wordline controls connection to bitlines
- Sizing constraints for read/write stability

<!-- _footer: Figure by <a href="//commons.wikimedia.org/wiki/User:Inductiveload" title="User:Inductiveload">Inductiveload</a> - <span class="int-own-work" lang="en">Own work</span>, Public Domain, <a href="https://commons.wikimedia.org/w/index.php?curid=5771850">Link</a></sub></sup> -->

### 6T SRAM Cell - Read

![bg right:40% width:100%](https://upload.wikimedia.org/wikipedia/commons/3/31/SRAM_Cell_%286_Transistors%29.svg)
- What is the state of the bitlines before activating the wordline?
- The bitlines are long with high capacitance $\to$ long (dis)charge time
- Better: precharge and amplify *difference* between bitlines
<!-- _footer: Figure by <a href="//commons.wikimedia.org/wiki/User:Inductiveload" title="User:Inductiveload">Inductiveload</a> - <span class="int-own-work" lang="en">Own work</span>, Public Domain, <a href="https://commons.wikimedia.org/w/index.php?curid=5771850">Link</a></sub></sup> -->

### 6T SRAM Cell - Read 

![bg right:40% width:100%](figures/memory/sram_read.svg)

### 6T SRAM Cell - Write

![bg right:40% width:100%](https://upload.wikimedia.org/wikipedia/commons/3/31/SRAM_Cell_%286_Transistors%29.svg)
- set BLs according to the value to be written
- activate WL, the value on BLs will force the inverters to flip if the value is different from the stored value
<!-- _footer: Figure by <a href="//commons.wikimedia.org/wiki/User:Inductiveload" title="User:Inductiveload">Inductiveload</a> - <span class="int-own-work" lang="en">Own work</span>, Public Domain, <a href="https://commons.wikimedia.org/w/index.php?curid=5771850">Link</a></sub></sup> -->

### Multi-Port SRAM Cell

### OpenRam 6T Cell

![bg width:90%](figures/memory/openram_cell_metal_layers.png)
![bg width:90%](figures/memory/openram_cell_transistors.png)

### OpenRam Cell Transistors

![bg width:100%](figures/memory/openram_cell_transistors.png)
![bg width:85%](https://upload.wikimedia.org/wikipedia/commons/3/31/SRAM_Cell_%286_Transistors%29.svg)


### SRAM Array Layout

![bg right:40% width:100%](figures/memory/sram_array_layout.drawio.svg)

### OpenRam Array

- 1rw and 1r port
- 256x32 bits
- Array: $212\:\mu\mathrm{m} \times 254\:\mu\mathrm{m}$
- Array density: $6.57\:\mu\mathrm{m}^2/\mathrm{bit}$
- 152.131,9269053632 bits/mm²
- Total area: $479.78\:\mu\mathrm{m} \times 397.5\:\mu\mathrm{m}$
- Total density: $23.3\:\mu\mathrm{m}^2/\mathrm{bit}$
- 42.954,6980521208 bits/mm²
- For 2KiB version: $17.37\:\mu\mathrm{m}^2/\mathrm{bit}$
- 57.580,9653073489 bits/mm²

![bg right:45% width:100%](figures/memory/openram.png)


### Commercial Sky130 SRAM

![bg right:40% width:100%](figures/memory/commercial_sram.png)

- Area: $387.87\:\mu\mathrm{m} \times 306.775\:\mu\mathrm{m}$
- Density: $14,53\:\mu\mathrm{m}^2/\mathrm{bit}$
- 137.693,6093934725 bits/mm²







### 4T SRAM Cell


![bg right:40% width:100%](https://upload.wikimedia.org/wikipedia/commons/f/fe/SRAM_Cell_%284_Transistors%29.svg)



<!-- _footer: Figure by <a href="//commons.wikimedia.org/wiki/User:Inductiveload" title="User:Inductiveload">Inductiveload</a> - <span class="int-own-work" lang="en">Own work</span>, Public Domain, <a href="https://commons.wikimedia.org/w/index.php?curid=114648423">Link</a> -->



