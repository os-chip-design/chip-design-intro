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

### Memories in general

![bg right:27% 550%](https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/Intel%40intel7%2810nmESF%29%40RaptorLake%40RPL%288P%2B16E%29%40i9-13900K%40ES_DSCx05_poly%405xExt.jpg/3840px-Intel%40intel7%2810nmESF%29%40RaptorLake%40RPL%288P%2B16E%29%40i9-13900K%40ES_DSCx05_poly%405xExt.jpg)

### Why is on-chip memory important?

### Cache (i9-13900K 10nm)

- L1: 
  - 80KB/P-Core
  - 96KB/E-Core
- L2:
  - 2MB/P-Core
  - 4MB/(4 E-Cores)
- L3: 36MB (shared)
- Total: ≈70MB!



![bg right:57% width:98%](https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Intel_Core_i9-13900K_Labelled_Die_Shot.jpg/1920px-Intel_Core_i9-13900K_Labelled_Die_Shot.jpg)


### Register based memeory


### Latch based memory



### Synth Ram

- $764.59\:\mu\mathrm{m} \times 775.31\:\mu\mathrm{m}$
- density: $567702\: \mu \text{m}^2 / 1\:\textrm{kiB} = 69.3\frac{\mu \text{m}^2}{\textrm{bit}}$
- For 70MB: $19.7 \mathrm{cm}\times 19.7\:\mathrm{cm}$
- In 10nm assuming 18x scaling: $4.6\:\mathrm{cm}\times 4.6\:\mathrm{cm}$

![bg right:40% width:100%](figures/memory/synthram.png)


### Latch

![bg vertical right:40% width:100%](https://upload.wikimedia.org/wikipedia/commons/2/2f/D-Type_Transparent_Latch.svg)
![bg right:40% width:100%](https://skywater-pdk.readthedocs.io/en/main/_images/sky130_fd_sc_hd__dlxtp_1.svg)

<!-- _footer: Figure by <a href="//commons.wikimedia.org/wiki/User:Inductiveload" title="User:Inductiveload">Inductiveload</a> - Own Drawing in Inkscape 0.46, Public Domain, <a href="https://commons.wikimedia.org/w/index.php?curid=873561">Link</a>> -->


### How else can we store data?

![bg right:40% width:80%](figures/memory/inv_cell.drawio.svg)


### 6T SRAM Cells

![bg right:40% width:100%](https://upload.wikimedia.org/wikipedia/commons/3/31/SRAM_Cell_%286_Transistors%29.svg)

<!-- _footer: Figure by <a href="//commons.wikimedia.org/wiki/User:Inductiveload" title="User:Inductiveload">Inductiveload</a> - <span class="int-own-work" lang="en">Own work</span>, Public Domain, <a href="https://commons.wikimedia.org/w/index.php?curid=5771850">Link</a></sub></sup> -->

### 6T SRAM Cell - Read

### 6T SRAM Cell - Write

### Multi-Port SRAM Cell

### OpenRam 6T Cell

![bg width:90%](figures/memory/openram_cell_metal_layers.png)
![bg width:90%](figures/memory/openram_cell_transistors.png)

### 4T SRAM Cell


![bg right:40% width:100%](https://upload.wikimedia.org/wikipedia/commons/f/fe/SRAM_Cell_%284_Transistors%29.svg)



<!-- _footer: Figure by <a href="//commons.wikimedia.org/wiki/User:Inductiveload" title="User:Inductiveload">Inductiveload</a> - <span class="int-own-work" lang="en">Own work</span>, Public Domain, <a href="https://commons.wikimedia.org/w/index.php?curid=114648423">Link</a> -->


### SRAM Array Layout

![bg right:40% width:100%](figures/memory/sram_array_layout.drawio.svg)

