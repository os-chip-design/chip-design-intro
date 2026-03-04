# Lab 5b: Testing a User Project in Caravel

Clone the the caravel user project from https://github.com/os-chip-design/dtu-soc-2026 and follow the setup instructions in the README.

Try to run the test suite using `cf verify --all`. 

Take a look at the Chisel source code in `src/main/scala`.

Now integrate the `WishboneGpio` module into the `CaravelTop` module and remove the `DecoupledGcd` module.

Now take a look at `verilog/dv/cocotb/user_proj_tests/gcd_example_test/` and try to understand the role of each file in the test suite.

Finally, write your own test code which configures the Wishbone GPIO module that you integrated earlier and check that the Wishbone GPIO module can set the outputs of the chip and that the inputs of the chip can be read by the Wishbone GPIO module. Refer to https://github.com/chipfoundry/caravel_user_project/blob/main/verilog/dv/cocotb/gpio_test/gpio_test.py as an example of how to read and write pins of the chip in the testbench.