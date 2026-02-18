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

##


first download the spice models

```
git clone https://github.com/mattvenn/sky130_fd_pr.git
```

to run spice sims we need to create a test harness

```
import std::cells;
open std::cells;

defproc dut(bool? A,B; bool! Y)
{
   std::cells::NOR2X1 dut(.A=A,.B=B,.Y=Y);
}

defproc test(bool? A,B; bool! Y)
{
   dut a(.A=A,.B=B,.Y=Y);
}
``` as test.act

further we need a config file with:

```
begin level
  string default "prs"
  begin types
    string_table device "dut<>" # Processes to be simulated at device level
  end
end


begin sim
  begin device
    real timescale 1e-12
    real analog_window 0.05	# 5% window of power supply
    real settling_time 1e-12
    int waveform_steps 10
    real waveform_time 8e-12
     
    string outfile "xyce_out"
    string model_files "${HOME}/sky130_fd_pr/models/all.spice"

    int case_for_sim 1		# 1 = uppercase, 0 = lowercase

    int dump_all 0					# Set to 1 for Xyce to output traces	
 
    string output_format "csv"	# Xyce output formats - can also do csv, raw
  end
end
``` as spice.conf