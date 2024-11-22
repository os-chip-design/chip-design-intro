module tb;

    // Inputs
    reg clk;
    reg reset;

    // Outputs
    wire [63:0] count;

    // Instantiate the Device Under Test (DUT)
    counter dut (
        .clk(clk),
        .reset(reset),
        .count(count)
    );
    
    // Clock generation
    initial begin
        clk = 0;
        forever #5 clk = ~clk; // 10ns clock period, 100MHz clock
    end
    
    // Test sequence
    initial begin
        // Initialize inputs
        reset = 1;

        // Wait 20 ns for global reset to finish
        #20;
        reset = 0;

        // Let the counter run for a while
        #100;

        // Assert reset
        reset = 1;
        #10;
        reset = 0;

        // Let it run again
        #50;

        // Finish the simulation
        $finish;
    end

    // Monitor the counter value (will print in console)
    initial begin
        $monitor("Time = %d, Reset = %b, Count = %d", $time, reset, count);
    end

    // VCD dump (Will be used by GTKwave)
    initial begin
        $dumpfile("counter_tb.vcd");
        $dumpvars(0, counter_tb);
    end

endmodule