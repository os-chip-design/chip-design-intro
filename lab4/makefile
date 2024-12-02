SHELL := /bin/bash  # Ensure Make uses bash

# Define directories
CHISEL_DIR=chisel
VERILOG_DIR=src

# Find all Verilog (.v and .sv) files in the VERILOG_DIR
ALL_VERILOG_FILES_IN_VERILOG_FOLDER := $(shell find $(VERILOG_DIR) -name '*.v' -or -name '*.sv')

# Default target
all: build

# These recipes calls a series of other recipes
build: compile copy_verilog run_openlane
sim : compile copy_verilog view_sim 

# Compile target
compile:
	@echo "Entering $(CHISEL_DIR) and running mill myproject"
	cd $(CHISEL_DIR) && mill myproject

# Test target for chisel only
test:
	@echo "Entering $(CHISEL_DIR) and running mill myproject.test"
	cd $(CHISEL_DIR) && mill myproject.test

# Clean target
clean:
	@echo "Cleaning up Mill output"
	cd $(CHISEL_DIR) && mill clean
	rm -rf chisel/out
	rm -rf $(VERILOG_DIR)
	rm -rf obj_dir
	rm -rf runs
	rm -f sim.vcd

# Create the verilog directory if it doesn't exist and copy .sv and .v files
copy_verilog:
	@echo "Creating $(VERILOG_DIR) directory"
	mkdir -p $(VERILOG_DIR)
	@echo "Copying .sv and .v files from $(CHISEL_DIR) to $(VERILOG_DIR)"
	mv $(CHISEL_DIR)/*.sv $(CHISEL_DIR)/*.v $(VERILOG_DIR) 2>/dev/null || true
	cp top.sv $(VERILOG_DIR)/

# Run verilator
view_sim: copy_verilog
	cd $(VERILOG_DIR)
	verilator --binary -j 0 $(ALL_VERILOG_FILES_IN_VERILOG_FOLDER) tb.v --top tb --trace
	obj_dir/Vtb
	gtkwave sim.vcd
	
# Run openlane
run_openlane: copy_verilog
	nix-shell --pure --command "openlane $(CURDIR)/config.json" ~/openlane2/shell.nix
	
# Run and view openlane	
view_klayout:
	nix-shell --pure --command "openlane --last-run --flow openinklayout $(CURDIR)/config.json" ~/openlane2/shell.nix
	
# Run and view openlane	
view_openlane:
	nix-shell --pure --command "openlane --last-run --flow openinopenroad $(CURDIR)/config.json" ~/openlane2/shell.nix
