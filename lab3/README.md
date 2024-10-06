# Introduction
Last week, you should have successfully installed OpenLane2 and completed running a design through its flow. While you were able to generate a layout and run verification checks like DRC and LVS, you likely noticed that simulation was not covered. Simulation is a crucial step that ensures the logical correctness of your design before it's sent for fabrication. This week, you will address that gap by setting up additional tools necessary for simulating Verilog designs.

You will install the oss-cad-suite, a comprehensive collection of open-source EDA tools that includes simulators and utilities for hardware description languages such as Verilog and VHDL. These tools will allow you to run simulations, check waveforms, and verify the functionality of your design at the RTL level. By interacting with these tools, you will learn how to write testbenches, run simulations, and analyze the results to ensure your design behaves as expected.

# Installing the tools
Navigate to your home directory:
```bash
cd ~
```

First, download the oss-cad-suite:
```
wget https://github.com/YosysHQ/oss-cad-suite-build/releases/download/2024-07-20/oss-cad-suite-linux-x64-20240720.tgz
```

Alternatively, you can find the newest version at: oss-cad-suite GitHub and download that.

Make a utils folder:
```
mkdir ~/utils
```

Now unpack the tgz file and place the files in the utils folder:
```
tar -xzf oss-cad-suite-linux-x64-20240720.tgz -C ~/utils
```

Ensure that you have a folder at:
```
~/utils/oss-cad-suite
```

Now remove the tgz file as it is no longer needed:
```
rm oss-cad-suite-linux-x64-20240720.tgz
```

Now you want to add the binaries in your oss-cad-suite to your path. Do:
```
nano ~/.bashrc
```

At the very bottom of the script, append:
```
export PATH=~/utils/oss-cad-suite/bin:$PATH
```

Now save and exit.

To confirm that you did it correctly, type:
```
source ~/.bashrc && which verilator
```

You should now see:
```
*User*/utils/oss-cad-suite/bin/verilator
```

You now have the oss-cad-suite installed.

## Using the tools
The tools used today are Verilator and GTKwave.

**Here is a quick explanation of Verilator:**  
Verilator is a tool that translates Verilog files to C++ files for simulating the behavior of the Verilog code. Verilator can directly output and run a binary file, so you don't have to do anything C++ related. Verilator outputs a VCD file.  
You can read the documentation here: [https://verilator.org/guide/latest/](https://verilator.org/guide/latest/).  
Do **NOT** follow the installation instructions in the documentation; instead, follow the installation provided in this document (which you should already have done).

**Here is a quick explanation of GTKwave:**  
GTKwave is a graphic application that can display the content of VCD files. GTKwave takes a VCD file as input.  
You can read the documentation here: [https://gtkwave.github.io/gtkwave/](https://gtkwave.github.io/gtkwave/).

### Run Verilator

Find and learn the `counter.v` and `tb.v` files and place them in: 
```
~/my_designs/my_first_design/source
```


To run Verilator, do this inside the "source" directory:
```
verilator --binary -j 0 -Wall counter.v tb.v --top tb --trace
```


Here is a quick explanation of the command:
1. `verilator` will call Verilator.
2. `--binary` tells Verilator to output a binary file; alternatively, you could build the C++ project yourself.
3. `-j 0` tells Verilator to use as many threads as it would like to.
4. `-Wall` is shorthand for "warning all" and makes Verilator tell you if you do something that is considered bad practice.
5. `counter.v` and `tb.v` are the files we wish Verilator to verilate.
6. `--top` tells Verilator which module is the top module.
7. `--trace` allows Verilator to output a "VCD" file.

This will create a folder named `obj_dir` with a C++ project inside; it will also build the C++, and you should find a file named "Vtb" inside the `obj_dir` folder. To execute this program, you do:
```
./obj_dir/Vtb
```

You should now see the following things happening:
1. Verilator should print:
```
	..... 	
	Time = 20, Reset = 0, Count = 0
	Time = 25, Reset = 0, Count = 0
	Time = 25, Reset = 0, Count = 1
	.....
	Time = 115, Reset = 0, Count = 10
	Time = 120, Reset = 1, Count = 10
	Time = 120, Reset = 1, Count = 0
	.....
```

This is caused by the following code inside the `tb.v` file:
```verilog
initial begin
    $monitor("Time = %d, Reset = %b, Count = %d", $time, reset, count);
end
```

2. You should see "counter_tb.vcd" appear in your folder. This can be used to plot the waveforms in GTKwave. This file is created because of the `--trace` argument and the following code inside the `tb.v` file:
    ```verilog
    initial begin
        $dumpfile("counter_tb.vcd");
        $dumpvars(0, counter_tb);
    end
    ```

3. You should see the simulation ending gracefully; it will look like this:
    ```
    - tb.v:44: Verilog $finish
    - S i m u l a t i o n   R e p o r t: Verilator 5.027 devel
    - Verilator: $finish at 185ps; walltime 0.002 s; speed 0.000 s/s
    - Verilator: cpu 0.000 s on 1 threads; alloced 249 MB
    ```


## Run GTKwave

To run GTKwave do:

`gtkwave counter.vcd`

Expand the "TOP" module, select "tb," and add the signals to the view by double-clicking. You should see:

![GTKwave output](Images/GTKwave_output.png)

## Hand-in Assignment

This shall be handed in before next week. You should hand in a document (preferably LaTeX) which answers the following questions.

### 1. Setup

1. Confirm that the installation was successful; optionally include a screenshot.
2. Describe any problems you had during the installation (to help students next year).

### 2. Verilator

These questions require you to read the documentation of Verilator found at: [Verilator Documentation](https://verilator.org/guide/latest/).
(Hint: the "Input Languages" section might be interesting)

1. Describe what a two-state signal is.
2. Describe what a four-state signal is.
3. Is Verilator able to simulate four-state signals? If no, what happens?
4. What does the `--lint-only` flag do?
5. What does the `-Wall` flag do? Is there a way to display warnings without exiting?
6. What does the `--timing` flag do?
7. What does the `-f` argument do?
8. What does a VCD file contain?
9. Is a VCD file in binary or text format? (Don't Google, see for yourself.)

### 3. GTKwave

1. Display "count[63:0]" in decimal.
2. Color the "count[63:0]" signal red.
3. Include a screenshot of GTKwave with count[63:0] in decimal and colored red.

### 4. Notes

For you to remember import steps in the future, it will be a good idea for you to take notes. These will not be evaluated, but you are allowed to turn them in together with the rest of your assignment. These notes are only for you and are not required.
