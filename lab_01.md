# Lab 1: Explore OpenLane 2

## Tool Installation

The following tools are installed:
* Install nix
* Install librelane (via git clone)

### Nix Installation

Follow the description from the [LibreLane Install](https://librelane.readthedocs.io/en/latest/installation/nix_installation/index.html) to install Nix.


### LibreLane Installation

The tools are currently usable on Linux and MacOS (even native with Mac Silicon). For Windows use WSL2 to have a Linux environment. There is no official support for Windows available.

See documentation for installation instructions at the [LibreLane Install](https://librelane.readthedocs.io/en/latest/installation/nix_installation/index.html).

Switch to your nix environment within your openlane clone directory by excuting the following command:

`$ nix-shell`

On the first call this will take quite some time, as it will download and install all the necessary tools. On subsequent calls, it will be faster.

### Optional: use VSCode / VSCodium

From any folder (e.g., where your design is) you can start VSCode with the following command:

`$ code .`

then enter the nix environment from a VSCode terminal.

### Alternative: Install via docker

Follow the description from the [LibreLane Docker](https://librelane.readthedocs.io/en/latest/installation/docker_installation/index.html) to install Nix.


## Using a Server

Alternative to installing the tools locally you can run them on
`chipdesign1.compute.dtu.dk`. You shall have access via `ssh` to this server.

However, students cannot access our internal systems directly by default, even with DTU VPN active. The only system we allow SSH into by students using DTU VPN is 'linuxterm1.compute.dtu.dk' aka. 'thinlinc.compute.dtu.dk'. From here they can then ssh again into chipdesign1.

`nix` and librelane packages are installed. 

go and clone librelane (first time only)
```
git clone https://github.com/librelane/librelane
```
go inside the folder and execute nix-shell
```
cd librelane
nix-shell
```
the first time check if everything works with 
```
librelane --smoke-test
```
You can also use VSCode (running locally) connecting to the server for
easier editing and file navigation.



## Running a "Hello World" Example

A minimal design consists of Verilog files and a `.json` file for the configuration of OpenLane.

Create a working folder, the two verilog files, and the single configuration files as described in the OpenLane *Newcomers* section and run the flow (within that folder) with:

```
openlane config.json
```

The flow takes some time and will print out sone info. Check the output to get a first information on what happened. E.g., you can already see some resource numbers in the output.

Let us then look at the generated ASIC (the GDSII file) with the following command:

```
openlane --last-run --flow openinklayout config.json
```

This command will start KLayout and show the layout of the generated ASIC. Play with KLayout a bit, e.g., use the ruler to measure the size of your ASIC. The unit is um.

Furthermore, the OpenLane run will generate several reports in the run directory. Look around and see what you can findout about the design.

* Can you find the size and usuage of the standard cells?
* Can you find out the maximum frequency of the design?

## Optional: Use tinytapeout summary tool

explore the summary tool to get a better overview of your design statistics

```
git clone https://github.com/mattvenn/librelane_summary.git
# and add the tool to your path
export PATH=$PATH:$(pwd)/librelane_summary
```

go inside you design folder and generate the statistics with:
```
summary.py
```
checkout the readme for more options.

## Optional: Synthesize your Own Design

Use one of your own (Chisel) designs, create the `.json` file and run it through the flow. Maybe explore how large your vending machine is. Find as a reference the size of a single tile in Tiny Tapeout, the service you will use to produce your chip. Would your vending machine fit into that cheap $ 50 tile?

## Optional: Synthesize a Very Simple Design

Although the former designs have been simple, we often loose the connection how they are implemented. I suggest to synthesize a super simple design, e.g., an 8-bit counter and explore the results in more detail. Can you see the adder used for the counter?

## How to control and customise your flow

librelane lets you adapt thier example flow at many levels, 

If you start your own design you have to adapt your design configuration [Design Conf Docs](https://librelane.readthedocs.io/en/latest/reference/configuration.html).

each flow consists out of steps and do get your design tapeed out we will need to tweek and adapt them to your design. Both for better results and most promently to fix mistakes and problems.
This we can do by changing the config of each step in the flow [Step Variable Docs](https://librelane.readthedocs.io/en/latest/reference/step_config_vars.html).

In special circumstances if you doing more complex designs or something special you can also adapt the flow itself [Flow Variable Docs](https://librelane.readthedocs.io/en/latest/reference/flows.html).
