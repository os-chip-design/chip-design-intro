# Define the Verilog source files
VERILOG_SOURCES = counter.v tb_counter.v

# Define the output executable
OUTPUT_EXECUTABLE = tb_counter

# Define the VVP output file
VVP_OUTPUT = tb_counter.vvp

# Define the simulation output file
SIM_OUTPUT = tb_counter_output.txt

# Define the Icarus Verilog compiler and simulator
IVERILOG = iverilog
VVP = vvp

# Default target to compile and run the testbench
all: run

# Compile the Verilog sources
compile:
	$(IVERILOG) -o $(VVP_OUTPUT) $(VERILOG_SOURCES)

# Run the simulation
run: compile
	$(VVP) $(VVP_OUTPUT) | tee $(SIM_OUTPUT)

# Clean up generated files
clean:
	rm -f $(VVP_OUTPUT) $(SIM_OUTPUT) $(OUTPUT_EXECUTABLE)

.PHONY: all compile run clean