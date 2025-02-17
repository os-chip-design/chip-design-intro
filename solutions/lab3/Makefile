all: clean test_counter test_verilog

# Use ChiselTest
# some workaround to find the Verilog files
test_counter:
	mkdir src
	mkdir src/main
	mkdir src/main/resources
	cp up_down_counter.v src/main/resources
	sbt test

test_verilog:
	iverilog up_down_counter.v tb_counter.v
	./a.out

synth:
	openlane counter.json

show:
	openlane --last-run --flow openinklayout counter.json
clean:
	- rm -rf src
	- rm -rf test_run_dir
	- rm -rf runs
	- rm *.txt
	- rm *.vcd
	- rm *.out
	- rm *.vvp