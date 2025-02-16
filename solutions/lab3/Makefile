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

clean:
	- rm -rf src
	- rm -rf test_run_dir
	- rm *.txt
	- rm *.vcd
	- rm *.out
	- rm *.vvp