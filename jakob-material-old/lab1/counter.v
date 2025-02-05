module counter (
    input wire clk,
    input wire reset,
    output reg [63:0] count
);

    always @(posedge clk or posedge reset) begin
        if (reset) begin
            count <= 64'b0;
        end else begin
            count <= count + 1;
        end
    end

endmodule