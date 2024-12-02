module tb;

	// Inputs
	reg clk;
	reg reset;
	reg [7:0] a_in;
	reg [7:0] b_in;

	// Outputs
	wire [7:0] res;

	// Instantiate the Device Under Test (DUT)
	Adder dut (
		.clock(clk),
		.reset(reset),
		.io_a(a_in),
		.io_b(b_in),
		.io_c(res)
	);
	
	// Clock generation
	initial begin
		clk = 0;
		forever #5 clk = ~clk; // 10ns clock period, 100MHz clock
	end
	
	// Test sequence
	initial begin
		// Initialize inputs
		clk = 0;
		reset = 0;
		a_in = 0;
		b_in = 0;
		
		// Apply reset
		reset = 1;
		#10 reset = 0;
		
		// Apply test vectors
		a_in = 8'd15; // 15 in decimal
		b_in = 8'd10; // 10 in decimal
		#10; // Wait for 10 time units
		
		// Check result
		if (res !== 8'd25) // Expected result is 15 + 10 = 25
			$display("Test failed: res = %d, expected = 25", res);
		else
			$display("Test passed: res = %d", res);
		
		// Finish the simulation
		$finish;
	end

	// Monitor the counter value (will print in console)
	initial begin
		$monitor("Time = %d, Reset = %b, Count = %d", $time, reset, res);
	end

	// VCD dump (Will be used by GTKwave)
	initial begin
		$dumpfile("sim.vcd");
		$dumpvars(0, sim);
	end

endmodule