# simulate standard gates

## download dependencies

https://unishare.nl/index.php/s/Fnsgyy6CaEe2Zgi download async-circuit-toolkit-2022-07-05.tar.gz

unzip it (tar -xvf async-circuit-toolkit-2022-07-05.tar.gz)

install gtkwave

## get environment ready

have a peek in the README.md

```export ACT_HOME=<path to act install>```

add act to your path:

```export PATH=$PATH:$ACT_HOME/bin```

if you get a error libedit not found exec this:
```export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:$ACT_HOME/lib```

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

you will find a lot more standard cells https://sky130-unofficial.readthedocs.io/en/latest/contents/libraries/sky130_fd_sc_hd/README.html

eg. try NAND2X1, AND2X1, NOR3X1, INVX1

you can dispaly the out.vcd file in gtkwave with `gtkwave out.vcd`


## alternative

please follow https://tinytapeout.com/digital_design/wokwi/

and do the 

https://tinytapeout.com/digital_design/puzzle_flipflop/

lab
