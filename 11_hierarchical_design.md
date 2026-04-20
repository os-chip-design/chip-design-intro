---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #fff
---

<!-- headingDivider: 3 -->

# **Hierarchical Design In Librelane**
**A Practical Guide**

**02118 - Introduction to Chip Design**

**Tjark Petersen**

# Lecture Overview
- Floorplanning
- Macro pin layout
- Macro PDN connection


### Floorplanning
- What do we have to consider?
- IO pad placement
- Hard macros w/ fixed pin locations
  - Memories
  - Analog blocks
- Custom macros where we need to choose pin locations


![bg right:40% width:80%](https://umsousercontent.com/lib_lnlnuhLgkYnZdkSC/r86juqr1bvqdd7y4.png)


### Floorplanning

- Easy: No macros
- Tools will optimize placement with regard to IO pads

![bg right:40% width:80%](https://umsousercontent.com/lib_lnlnuhLgkYnZdkSC/r86juqr1bvqdd7y4.png)


### Floorplanning

- Hard: Many macros
- What is connected to what?
- Easily high congestion
- 

### Hierarchical Design
- Pros:
  - Shorter runtimes $\to$ faster iterations
  - No interaction between modules in P&R $\to$ Modules can be finished one by one
- Cons:
  - Tool can't optimize for timing and congestion across macro boundaries $\to$ worse timing and congestion
  - Especially true for clock tree
  - Manual optimization of IO placement, macro pin layout, and macro placement

### When does it make sense?
- Repeating blocks (e.g. a processor core)

### Example: Intel Core i9-13900K

![bg width:79%](https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Intel_Core_i9-13900K_Labelled_Die_Shot.jpg/1920px-Intel_Core_i9-13900K_Labelled_Die_Shot.jpg)


<!-- _footer: Figure: By <a href="https://commons.wikimedia.org/w/index.php?curid=130559408">JmsDoug</a> -->

### 

![bg width:34%](figures/Intel_Core_i9-13900K_P_Core.jpg)


<!-- _footer: Figure: By <a href="https://commons.wikimedia.org/w/index.php?curid=130559408">JmsDoug</a> -->

###

![bg width:55%](figures/Intel_Core_i9-13900K_E_Core.jpg)


### Macro power connection?

### Pads
- 80umx200um
![bg right:30%](figures/gpio_pad.png)



<!-- _footer: Figure: By <a href="https://commons.wikimedia.org/w/index.php?curid=130559408">JmsDoug</a> -->

# Using an existing macro


# Designing a custom macro


# Pin order definition
- edges
- bus major
- bit major
- min distance
- ghost pins

# Custom Def template


# PDN layout
- power ring
- power straps
- hierarchical grids
- parameters


# Floorplanning
- aspect ratio + density
- or just fixed size
- other macros? pin access
- FP_SIZING=relative/absolute
- FP_ASPECT_RATIO=1
- FP_CORE_UTIL=50
- CORE_AREA=[0, 0, 1000, 1000]

### Obstructions

```yaml
FP_OBSTRUCTIONS:
  - [330, 330, 550, 550]
ROUTING_OBSTRUCTIONS:
  - "met1 330 330 550 550"
  - "met2 330 330 550 550"
  - "met3 330 330 550 550"
  - "met4 330 330 550 550"
  - "met5 330 330 550 550"
PDN_OBSTRUCTIONS:
  - "met4 330 330 550 550"
  - "met5 330 330 550 550"
```

![bg right:35% width:100%](figures/picorv_custom_shape.png)



# Issues

- Timing violations
  - Setup time violations
  - Hold time violations
  - Slew violations (max slew)
  - Load capacitance violations (max cap)
- Antenna violations


### Timing corners
- IPVT corner
  - (I)nterconnect: wire resistance and capacitance {min, max, nom}
  - (P)rocess: transistor parameters {slow (ss), fast (ff), typical (tt)}
  - (V)oltage: supply voltage, e.g. 1.8V
  - (T)emperature: e.g. 25°C
- Default corner: nom_tt_025C_1v80

# Setup violation, what to do?
- Which corner, does it matter?
- Increase margins:
  - `GRT_RESIZER_SETUP_SLACK_MARGIN` in ns (default = 0.025ns)
  - `GRT_RESIZER_SETUP_MAX_BUFFER_PCT` to limit how many buffers relative to number of existing cells can be added (default = 50%)
- Otherwise:
  - May have to change design
  - Analyze critical path, optimize it

### Critical Path Trace (max.rpt)

```
Startpoint: _11946_ (rising edge-triggered flip-flop clocked by clock)
Endpoint: _10503_ (rising edge-triggered flip-flop clocked by clock)
Path Group: clock
Path Type: max

Fanout         Cap        Slew       Delay        Time   Description
---------------------------------------------------------------------------------------------
                                  0.000000    0.000000   clock clock (rise edge)
                                  0.000000    0.000000   clock source latency
     2    0.056267    0.257117    0.178999    0.178999 ^ clock (in)
                                                         clock (net)
                      0.257159    0.000000    0.178999 ^ clkbuf_0_clock/A (sky130_fd_sc_hd__clkbuf_16)
     8    0.179781    0.191963    0.307513    0.486512 ^ clkbuf_0_clock/X (sky130_fd_sc_hd__clkbuf_16)
                                                         clknet_0_clock (net)
...
                      0.123797    0.005285    0.900431 ^ clkbuf_leaf_192_clock/A (sky130_fd_sc_hd__clkbuf_8)
    13    0.046487    0.092130    0.193294    1.093725 ^ clkbuf_leaf_192_clock/X (sky130_fd_sc_hd__clkbuf_8)
                                                         clknet_leaf_192_clock (net)
                      0.092154    0.001433    1.095158 ^ _11946_/CLK (sky130_fd_sc_hd__dfxtp_1)
     1    0.005632    0.064597    0.336082    1.431240 ^ _11946_/Q (sky130_fd_sc_hd__dfxtp_1)
                                                         core.picorv32_core.decoder_trigger (net)
                      0.064597    0.000203    1.431443 ^ fanout602/A (sky130_fd_sc_hd__buf_2)
     6    0.047677    0.233191    0.247844    1.679287 ^ fanout602/X (sky130_fd_sc_hd__buf_2)
                                                         net602 (net)
...
                      0.059350    0.000115    9.677893 ^ _07218_/B (sky130_fd_sc_hd__nor2_1)
     1    0.003421    0.048187    0.042458    9.720350 v _07218_/Y (sky130_fd_sc_hd__nor2_1)
                                                         _00230_ (net)
                      0.048187    0.000114    9.720464 v _10503_/D (sky130_fd_sc_hd__dfxtp_1)
                                              9.720464   data arrival time

                                 20.000000   20.000000   clock clock (rise edge)
                                  0.000000   20.000000   clock source latency
     2    0.056267    0.257117    0.178998   20.178999 ^ clock (in)
                                                         clock (net)
                      0.257159    0.000000   20.178999 ^ clkbuf_0_clock/A (sky130_fd_sc_hd__clkbuf_16)
     8    0.179781    0.191963    0.307514   20.486513 ^ clkbuf_0_clock/X (sky130_fd_sc_hd__clkbuf_16)
                                                         clknet_0_clock (net)
...
                      0.120020    0.002998   20.890413 ^ clkbuf_leaf_146_clock/A (sky130_fd_sc_hd__clkbuf_8)
     9    0.042436    0.085728    0.187127   21.077539 ^ clkbuf_leaf_146_clock/X (sky130_fd_sc_hd__clkbuf_8)
                                                         clknet_leaf_146_clock (net)
                      0.085740    0.001020   21.078560 ^ _10503_/CLK (sky130_fd_sc_hd__dfxtp_1)
                                 -0.250000   20.828560   clock uncertainty
                                  0.000000   20.828560   clock reconvergence pessimism
                                 -0.102409   20.726152   library setup time
                                             20.726152   data required time
---------------------------------------------------------------------------------------------
                                             20.726152   data required time
                                             -9.720464   data arrival time
---------------------------------------------------------------------------------------------
                                             11.005689   slack (MET)
```

# Hold Time violation what to do?
- Which corner, does it matter?
- Increase margins:
  - `GRT_RESIZER_HOLD_SLACK_MARGIN` in ns (default = 0.05ns)
  - `GRT_RESIZER_HOLD_MAX_BUFFER_PCT` to limit how many buffers relative to number of existing cells can be added (default = 50%)


### Understanding .lib files

- `index_1`: input transition time (slew) in nanoseconds
- `index_2`: output load capacitance in picofarads
- `rise_transition`, `fall_transition`: output slew
- `cell_rise`, `cell_fall`: delay from input to output
```
related_pin : "A2";
rise_transition ("del_1_7_7") {
    index_1("0.0100000000, 0.0230506000, 0.0531329000, 0.1224740000, 0.2823110000, 0.6507430000, 1.5000000000");
    index_2("0.0005000000, 0.0013283400, 0.0035289500, 0.0093752600, 0.0249070000, 0.0661697000, 0.1757910000");
    values( "0.0253370000, 0.0325569000, 0.0506810000, 0.0987801000, 0.2274845000, 0.5733150000, 1.4946702000", \
            "0.0252911000, 0.0324111000, 0.0505358000, 0.0985369000, 0.2280276000, 0.5722391000, 1.4927543000", \
            "0.0251010000, 0.0322888000, 0.0504267000, 0.0984950000, 0.2278604000, 0.5734477000, 1.4949251000", \
            "0.0249341000, 0.0320615000, 0.0500063000, 0.0980978000, 0.2277742000, 0.5726755000, 1.4909479000", \
            "0.0271800000, 0.0342408000, 0.0514140000, 0.0979232000, 0.2269928000, 0.5722665000, 1.4925735000", \
            "0.0329031000, 0.0394216000, 0.0559004000, 0.1002220000, 0.2270916000, 0.5724417000, 1.4956580000", \
            "0.0434918000, 0.0510931000, 0.0676624000, 0.1080043000, 0.2288458000, 0.5738863000, 1.4926184000");
}
```





# Slew
- 

# max cap

# antenna violations
- ratio metal area to gate area

![bg vertical right:30% width: 80%](https://upload.wikimedia.org/wikipedia/commons/6/62/AntennaEffect.gif)
![bg right:30% width: 80%](https://upload.wikimedia.org/wikipedia/commons/d/d9/AntennaFixes.gif)

<!-- _footer: Figure: By <a href="https://commons.wikimedia.org/w/index.php?curid=170018503">JmsDoug</a>, <a href="https://commons.wikimedia.org/w/index.php?curid=170018457">Lou Scheffer</a>-->
