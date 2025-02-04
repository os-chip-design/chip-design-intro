# Lab 1: Explore OpenLane 2

## Tool Installation

The tools are currently usable on Linux and MacOS (even native with Mac Silicon). For Windows use WSL2 to have a Linux environment. There is no official support for Windows available.

See documentation for installation instructions at the [OpenLane 2 Documentation](https://openlane2.readthedocs.io/en/latest/).

 * Install nix
 * Install OpenLane 2 (via git clone)

Switch to your nix environment within your openlane clone directory by excuting the following command:

`$ nix-shell`

On the first call this will take quite some time, as it will download and install all the necessary tools. On subsequent calls, it will be faster.

### Optional: use VSCode

From any folder (e.g., where your design is) you can start VSCode with the following command:

`$ code .`

then enter the nix environment from a VSCode terminal.

## Using a Server

Alternative to installing the tools locally you can run them on
`chipdesign1.compute.dtu.dk`. You shall have access via `ssh` to this server.`nix` and OpenLane2 are installed. Change to `/home/share/openlane2` and
start the nix shell.

Then change back to your home directory start working from there.
Please do not change anything in the `openlane2` directory.

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

Furthermore, the OpenLane run will generate several reports in the run directory. Look around and see what you can findout about the design. Can you find the size and usuage of the standard cells? Can you find out the maximum frequency of the design?

## Optional: Synthesize your Own Design

 Use one of your own (Chisel) designs, create the `.json` file and run it through the flow. Maybe explore how large your vending machine is. Find as a reference the size of a single tile in Tiny Tapeout, the service you will use to produce your chip. Would your vending machine fit into that cheap $ 50 tile?

 ## Optional: Synthesize a Very Simple Design

 Although the former designs have been simple, we often loose the connection how they are implemented. I suggest to synthesize a super simple design, e.g., an 8-bit counter and explore the results in more detail. Can you see the adder used for the counter?