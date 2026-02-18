# simulate standard gates

## download dependencies

https://unishare.nl/index.php/s/Fnsgyy6CaEe2Zgi download async-circuit-toolkit-2022-07-05.tar.gz

unzip it

install gtkwave

## get environment ready

have a peek in the README.md

```export ACT_HOME=<path to act install>```

add act to your path:

```export PATH=$PATH:$ACT_HOME/bin```

download the cell act from

## run digital sims of standart cells

To test the function of a NOR2 Cell:

```
actsim $ACT_HOME/act/std/cells.act std::cells::NOR2X1
```

now we want to watch the in and outputs
```  
actsim> watch Y
actsim> watch A
actsim> watch B
vcd_start out.vdc

```

now you can set the inputs and outputs with 
```
actsim> set A 0
actsim> set B 0
actsim> advance 1000
actsim> set A 1
actsim> set B 1
actsim> advance 1000
actsim> vcd_stop
```

If you have a peek in 
$ACT_HOME/act/std/cells.act

you will find a lot more standard cells

eg. try NAND2X1, AND2X1, NOR3X1, INVX1

## run SPICE sims

open colab from the zero to asic course

https://colab.research.google.com/gist/proppy/964fa4b9277c3baf9e731872bbad93e4/zerotoasic_project1_1.ipynb

This is a simulation of a SPICE NAND2 

run it

afterward try to run other 2-input logic cells by swaping out the cell name:

in the 3 lines
```
circuit = Circuit('sky130_fd_sc_hd__nand2_1')
circuit.include('./skywater-pdk-libs-sky130_fd_sc_hd/cells/nand2/sky130_fd_sc_hd__nand2_1.spice')
circuit.X('cell', 'sky130_fd_sc_hd__nand2_1', 'A', 'B', 'VGND', 'VNB', 'VPB', 'VPWR', 'Y')
```

and see how they behave differently.

you can see all cells here https://sky130-unofficial.readthedocs.io/en/latest/contents/libraries/sky130_fd_sc_hd/README.html

run with 

- sky130_fd_sc_hd__and2  
```
circuit = Circuit('sky130_fd_sc_hd__and2_1')
circuit.include('./skywater-pdk-libs-sky130_fd_sc_hd/cells/and2/sky130_fd_sc_hd__and2_1.spice')
circuit.X('cell', 'sky130_fd_sc_hd__and2_1', 'A', 'B', 'VGND', 'VNB', 'VPB', 'VPWR', 'Y')
```
- sky130_fd_sc_hd__or2 
```
circuit = Circuit('sky130_fd_sc_hd__or2_1')
circuit.include('./skywater-pdk-libs-sky130_fd_sc_hd/cells/or2/sky130_fd_sc_hd__or2_1.spice')
circuit.X('cell', 'sky130_fd_sc_hd__or2_1', 'A', 'B', 'VGND', 'VNB', 'VPB', 'VPWR', 'Y')
```
- sky130_fd_sc_hd__xor2
```
circuit = Circuit('sky130_fd_sc_hd__xor2_1')
circuit.include('./skywater-pdk-libs-sky130_fd_sc_hd/cells/xor2/sky130_fd_sc_hd__xor2_1.spice')
circuit.X('cell', 'sky130_fd_sc_hd__xor2_1', 'A', 'B', 'VGND', 'VNB', 'VPB', 'VPWR', 'Y')
```
- sky130_fd_sc_hd__xnor2
```
circuit = Circuit('sky130_fd_sc_hd__xnor2_1')
circuit.include('./skywater-pdk-libs-sky130_fd_sc_hd/cells/xnor2/sky130_fd_sc_hd__xnor2_1.spice')
circuit.X('cell', 'sky130_fd_sc_hd__xnor2_1', 'A', 'B', 'VGND', 'VNB', 'VPB', 'VPWR', 'Y')
```