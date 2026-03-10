
# Caravel Testing Lab

In this lab you will learn how to write tests for Caravel and your user project using Cocotb. If you haven't set up the Caravel repository yet, do so now by cloning https://github.com/os-chip-design/chisel-caravel and following the instructions in the README for the setup.

 In the current state, if you run the tests using `cf verify --all` you should see that 3 tests are failing and 1 test is passing. The goal of this lab is to integrate a simple wishbone GPIO module into the user project space and implement the code for the management core, such that all the tests pass.

```
> cf verify --all
┏━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━┳━━━━━━━━┳━━━━━━━━━━━━┓                                                                                                                    
┃ Total                    ┃ Passed ┃ Failed        ┃ Unknown       ┃ duration   ┃        ┃            ┃                                                                                                                    
┡━━━━━━━━━━━━━━━━━━━━━━━━━━╇━━━━━━━━╇━━━━━━━━━━━━━━━╇━━━━━━━━━━━━━━━╇━━━━━━━━━━━━╇━━━━━━━━╇━━━━━━━━━━━━┩                                                                                                                    
│ 4                        │ 1      │ 3             │ 0             │ 0:01:13.50 │        │            │                                                                                                                    
│                          │        │               │               │            │        │            │                                                                                                                    
│ Test                     │ status │ start         │ end           │ duration   │ p/f    │ seed       │                                                                                                                    
│ RTL-gcd_example_test     │ done   │ 15:55:24(Mon) │ 15:55:40(Mon) │ 0:00:15.97 │ passed │ 1773068129 │                                                                                                                    
│ RTL-gpio_read_input_test │ done   │ 15:55:40(Mon) │ 15:55:59(Mon) │ 0:00:18.59 │ failed │ 1773068143 │                                                                                                                    
│ RTL-gpio_set_output_test │ done   │ 15:55:59(Mon) │ 15:56:08(Mon) │ 0:00:08.86 │ failed │ 1773068162 │                                                                                                                    
│ RTL-gpio_bidir_test      │ done   │ 15:56:08(Mon) │ 15:56:38(Mon) │ 0:00:29.87 │ failed │ 1773068171 │                                                                                                                    
└──────────────────────────┴────────┴───────────────┴───────────────┴────────────┴────────┴────────────┘ 
```

## Integrating the Wishbone GPIO

You are provided with a simple wishbone GPIO module in `src/main/scala/WishboneGpio.scala`. Your first task is to integrate this module into the Chisel top level in `src/main/scala/CaravelTop.scala`. This requires instantiating the module, integrating it into the existing address decoding and wishbone logic and connecting it to the GPIO pins. Take a look at the `WishboneGpio` module and make sure you understand how it works. 

Some of the lower GPIO pins are shared with the management core, use therefore the [15:8] range. A simple address decoding scheme is used where `addr[19:16]` is used to select the peripheral and `addr[15:0]` is used as the address within the peripheral. You can use `addr[19:16] == 0` for the wishbone GPIO module.

Remember to run `make chisel-generate` to generate the Verilog code after you have made the changes.

## Testing in Caravel

Tests in Caravel are run using Cocotb. The tests are located in `verilog/dv/cocotb`. The directory structure of the tests is as follows:

```
verilog/dv/cocotb
  ├── cocotb_tests.py
  └── user_proj_tests
      └── test_name
          ├── test_name.c
          ├── test_name.py
          └── test_name.yaml
```

The `cocotb_tests.py` file is used to run all the tests in the `user_proj_tests` directory. Each test is located in a separate directory and consists of a C file for the code run on the management core, a Python file for the Cocotb testbench, and a YAML file for the test description. Running a test using `cf verify test_name` will compile the C code, and run the Cocotb testbench where the management core will boot the compiled code and run the test. Any logic for checking whether the test passed or failed has to be implemented in the Cocotb testbench.

## C API

The C code for the management core relies on the API's provided in `mgmt_core_wrapper/verilog/dv/firmware/APIs/`. We will walk through the API's that are relevant for the tests in this lab, but feel free to explore the other API's as well.

### Management GPIO

The management GPIO is an additional GPIO pin controlled by the management core. In the test setup it is used to communicate from the management core to the testbench, for example to signal that the test has passed. Setting the management GPIO to output mode is done using:
```c
ManagmentGpio_outputEnable();
```
And writing a value (0 or 1) to the management GPIO is done using:
```c
ManagmentGpio_write(value);
```

**OBS**: The lacking 'e' in 'Managment' is a typo in the API

### GPIO

Since we will drive the GPIO pins from within the user project and from the testbench, we need to be able to configure the mode of the GPIO pins from the management core. This is done using the following functions:

```c
GPIOs_configureAll(config);
GPIOs_configure(i, config);
```
Where `config` can be one of the following:
 - `GPIO_MODE_MGMT_STD_INPUT_NOPULL`
 - `GPIO_MODE_MGMT_STD_INPUT_PULLDOWN`
 - `GPIO_MODE_MGMT_STD_INPUT_PULLUP`
 - `GPIO_MODE_MGMT_STD_OUTPUT`
 - `GPIO_MODE_MGMT_STD_BIDIRECTIONAL`
 - `GPIO_MODE_MGMT_STD_ANALOG`
 - `GPIO_MODE_USER_STD_INPUT_NOPULL`
 - `GPIO_MODE_USER_STD_INPUT_PULLDOWN`
 - `GPIO_MODE_USER_STD_INPUT_PULLUP`
 - `GPIO_MODE_USER_STD_OUTPUT`
 - `GPIO_MODE_USER_STD_BIDIRECTIONAL`
 - `GPIO_MODE_USER_STD_OUT_MONITORED`
 - `GPIO_MODE_USER_STD_ANALOG`

### Wishbone

The wishbone interface can be enabled using the following function:

```c
User_enableIF();
```

Writing words to the wishbone interface is done using:

```c
USER_writeWord(value, wordAddr);
```

**OBS**: The address is specified in words, so for example to write to address 16 (0x10) you would specify `wordAddr` as `(0x10 >> 2) = 4`

Reading a word from the wishbone interface is done using:

```c
uint32_t value = USER_readWord(wordAddr);
```

## Cocotb API's

The tests for the user project are written using Cocotb, which is a Python library for writing testbenches for hardware designs. Inside the Cocotb test function, a `caravelEnv` variable provides access to a number of API's for interacting with the Caravel test environment.

```python
caravelEnv = await test_configure(dut, timeout_cycles=???)
```

The testbench can communicate and synchronize with the management core using the management GPIO:

```python
await caravelEnv.wait_mgmt_gpio(value)
```
This will block until the management GPIO is set to the specified value (0 or 1).

The testbench can wait for a number of clock cycles using:

```python
await cocotb.triggers.ClockCycles(caravelEnv.clk, num_cycles)
```

The testbench can drive the GPIO pins using:

```python
caravelEnv.drive_gpio_in((high_bit, low_bit), value)
```
This will drive the GPIO pins from `low_bit` to `high_bit` (inclusive) with the specified value.

The testbench can monitor the value of the GPIO pins using:

```python
gpio_value = caravelEnv.monitor_gpio(high_bit, low_bit)
```

## Implementing the Tests

Now we have all the necessary information to implement the tests. Inside the `verilog/dv/cocotb/user_proj_tests` directory you will find three directories for the three failing tests: `gpio_read_input_test`, `gpio_set_output_test` and `gpio_bidir_test`. They already contain the Cocotb testbenches and a C code skeleton, which you will need to fill in. The goal of each of the tests is as follows:

- `gpio_read_input_test`: Test that we can read the value of the GPIO pins correctly from the user project. The testbench will drive a value on the GPIO pins and the management core should configure the GPIO pins as input and read the value from the wishbone GPIO module, and signal pass if the value is correct.
- `gpio_set_output_test`: Test that we can set the value of the GPIO pins correctly from the user project. The management core should configure the GPIO pins as output and set a value on the GPIO pins using the wishbone GPIO module, and signal pass once the value is set. The testbench will monitor the GPIO pins and check that the correct value is set.
- `gpio_bidir_test`: Test that we can use the GPIO pins in bidirectional mode where the direction is controlled using the `oeb` register in the wishbone GPIO module. The management core should configure the GPIO pins as bidirectional, and then set the upper 4 bits of the GPIO pins as input and the lower 4 bits as output. The management core should iterate from 0 to 15, and for each value, set the lower 4 bits of the GPIO pins to that value, and then wait for the testbench to drive the same value on the upper 4 bits of the GPIO pins, before moving on to the next value.

Implement the code for the management core in the C files such that all the tests pass. If you make changes to the Chisel code, don't forget to run `make chisel-generate` to generate the Verilog code. You can run each test individually using `cf verify test_name` or run all the tests using `cf verify --all`.