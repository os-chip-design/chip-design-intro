---
marp: true
theme: gaia
_class: lead
paginate: true
backgroundColor: #fff
---

<!-- headingDivider: 3 -->

# **Timing and Physical Design**

**Ole Richter**

## Challenges of moving from sim to ASIC

- verified RTL
- syntesised netlist
- standard cells

## Naive steps

- place them
- connect signals and power
- should *work* as in your sim

![width:1000px](figures/L10/krakenbegin.svg)

## Timing and clock

- the clock is the global *perfect* signal that makes your sim work.

*event* is a signal change 

![width:600px](figures/L10/slopes.svg)

## Timing Delays

![width:1000px](figures/L10/slopes.svg)

## Timing Delays and drive strength

![width:1000px](figures/L10/drivestrength.svg)

## Clock insertion

![width:1000px](figures/L10/fifo_plain.svg)

## Clock insertion

![width:1000px](figures/L10/fifo_clk_flat.svg)

## Clock tree insertion

![width:1000px](figures/L10/fifo_clk_tree.svg)

## Event ordering

![width:1000px](figures/L10/ff_events.svg)

## setup timing

![width:500px](figures/L10/ff_events.svg) ![width:200px](figures/L10/ff_circle_setup.svg)

## setup timing

![width:600px](figures/L10/ff_circle_setup_fix.svg)

## hold timing
![width:500px](figures/L10/ff_events.svg) ![width:200px](figures/L10/ff_circle_hold.svg)

## hold timing

![width:800px](figures/L10/ff_circle_hold_fix.svg)

## skew, jitter of the clock

![width:300px](figures/L10/ff_circle_c1.svg) ![width:250px](figures/L10/ff_circle_c2.svg)

## static timing analysis (STA)

 - get all wire timings & load delays

![width:800px](figures/L10/timing_graph.svg)

## recap clock

- timing delays
- setup and hold
- critical cycle and path
- timing closure
![width:600px](figures/L10/krakenmid1.svg)

## Power

- the problem: supply bumps

![width:900px](figures/L10/powerbump.svg)

## IR analysis

- resitance of power rails
- locaslised consumtion of all cells
- capacitence in the power network

## Solutions

- wide powerlines, and execive power disrtibution
- Power rings, grids and stripes

![width:400px](figures/L10/powerlines.svg)

## Decoupling Capacitances

- clock syncronises switching
- add capacitances between power rails to stabilised

## Recap power

- dont save on power lines
- also helps for heat

![width:800px](figures/L10/krakenmid2.svg)

## Phyical Design (Rules)

- rule set of the foundry that is tested
- they are confident that they can produce it for you

## CMOS Fabrication

![width:600px](figures/L10/circuitdia-01.svg) 

## CMOS Fabrication

![width:600px](figures/L10/circuitdia-02.svg)

## Spacing 

![width:800px](https://skywater-pdk.readthedocs.io/en/main/_images/p035-li_dotdash_dotdash.svg) 
skywater 130 li connect

## minimim width and area

![width:600px](https://skywater-pdk.readthedocs.io/en/main/_images/p044-m4_dotdash.svg) 
skywater 130 metal


## Conclusion

 - clock and timing
 - power and supply drops
 - design rules

 ![width:600px](figures/L10/krankenend.svg)




x``


