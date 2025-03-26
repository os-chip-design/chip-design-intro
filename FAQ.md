## Openlane2 Installation

### *It takes a long time build!*

This is intended behaviour as the Openlane2 builds from scratch - using your computer to compile the entire program.

### False-Alarm Deprecated issues
```zsh
nix-shell --pure ~/openlane2/shell.nix
```
#### Workaround
Add this setting onto the command.
```
--extra-deprecated-features url-literals
```

### Openlane2 Rate Limit

Downloading the sanity check for the Openlane2's sky130 PDK command of this produces this error
```rust
[nix-shell:~/openlane2]$ openlane --log-level ERROR --condensed --show-progress-bar --smoke-test
Version 0fe599b2afb6708d281543108caf8310912f54af not found locally, attempting to download…
Traceback (most recent call last):
File "/nix/store/0qj3x1yif58v3dcbq3smm2mjd4vksvaq-python3-3.11.9-env/lib/python3.11/site-packages/volare/manage.py", line 181, in fetch
release_link_list = version_object.get_release_links(
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
File "/nix/store/0qj3x1yif58v3dcbq3smm2mjd4vksvaq-python3-3.11.9-env/lib/python3.11/site-packages/volare/common.py", line 192, in get_release_links
release = github.get_release_links(f"{self.pdk}-{self.name}", session)
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
File "/nix/store/0qj3x1yif58v3dcbq3smm2mjd4vksvaq-python3-3.11.9-env/lib/python3.11/site-packages/volare/github.py", line 177, in get_release_links
return session.api(volare_repo, f"/releases/tags/{release}", "get")
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
File "/nix/store/0qj3x1yif58v3dcbq3smm2mjd4vksvaq-python3-3.11.9-env/lib/python3.11/site-packages/volare/github.py", line 140, in api
req.raise_for_status()
File "/nix/store/0qj3x1yif58v3dcbq3smm2mjd4vksvaq-python3-3.11.9-env/lib/python3.11/site-packages/httpx/_models.py", line 761, in raise_for_status
raise HTTPStatusError(message, request=request, response=self)
httpx.HTTPStatusError: Client error '403 rate limit exceeded' for url 'https://api.github.com/repos/efabless/volare/releases/tags/sky130-0fe599b2afb6708d281543108caf8310912f54af'
For more information check: https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/403
```
#### Workaround
Wait for some minutes and retry again! It just means that people are overloading the GitHub API.

## SiliWiz


- Make sure you use "in", "Vdd", "Vss" as input, power and ground respectively to make sure that the SPICE simulator built into the SiliWiz works as intended.
- Make sure that you have added the "via" layer between the silicon and the metal.
- The current through the gate is proportional to the width and height of the polysilicon, which means that the thinner and smaller the gate, the more current can go through it.
- Higher the capacitance, the less steep the voltage drop after the charge, so you need to zoom in really deep to see the drop than the given example gives.

## TinyTapeout

### *'docs' Build Failure*

Ensure that the info.yaml file has every entry but the Discord filled in the .yaml,
ensure that the indentation is preserved.
Also make sure that /docs/info.md has each subheader filled with different information other than the default given one as otherwise it will fail the build.

### *'gds' Build Failure*

```rust
Run ./tt/tt_tool.py --create-user-config
2025-02-18 07:28:24,712 - project    - ERROR    - Error loading /home/runner/work/ta-test-run/ta-test-run/info.yaml: Top module must start with 'tt_um_' (e.g. tt_um_my_project)
Error: Process completed with exit code 1.
```

Ensure that top module of the verilog project starts with 'tt_um_*'.

### *"Missing Ports"*

```rust
2025-02-18 09:12:31,901 - project    - ERROR    - [000 : unknown] port 'ena' missing from top module ('tt_um_up_down_counter')
2025-02-18 09:12:31,901 - project    - ERROR    - [000 : unknown] port 'rst_en' missing from top module ('tt_um_up_down_counter')
..
2025-02-18 09:12:31,901 - project    - ERROR    - [000 : unknown] port 'ui_in' missing from top module ('tt_um_up_down_counter')
```

TinyTapeout has hard-typed inputs and outputs. You need to make sure that the ports in your Verilog file match the ports in the template. Here are the following "missing ports" to the top module in your implementation.

```verilog
    input  wire [7:0] ui_in,    // Dedicated inputs
    output wire [7:0] uo_out,   // Dedicated outputs
    input  wire [7:0] uio_in,   // IOs: Input path
    output wire [7:0] uio_out,  // IOs: Output path
    output wire [7:0] uio_oe,   // IOs: Enable path (active high: 0=input, 1=output)
    input  wire       ena,      // always 1 when the design is powered, so you can ignore it
    input  wire       clk,      // clock
    input  wire       rst_n     // reset_n - low to reset
```

## SBT

### SBT Java Version - Error compiling the sbt component 'compiler-bridge_2.13'

#### System Dependent


#### IntelliJ IDEA

In order to force the SBT to use the Java version (which currently supported is 11) which is able to compile the compiler bridge component,
then you need to do the following:
- Change the Project SDK to Java 11 by `File -> Project Structure -> Project Settings -> SDKs`, select the `+` and add the Java 11 SDK either using your own or download it from the IDE.
- Change the Build Tool (sbt) SDK to chosen Java 11 SDK by `Settings -> Build, Execution, Deployment -> Build Tools -> sbt`, select the `jre` and pick the Java 11 SDK.

##### WSL (Windows Subsystem for Linux)

Assuming the supported version, in order to install OpenJDK 11 and set it as the default Java version in WSL, follow these steps:

1. Update your package list:
    ```bash
    sudo apt update
    ```

2. Install OpenJDK 11:
    ```bash
    sudo apt install openjdk-11-jdk
    ```

3. Verify the installation:
    ```bash
    java -version
    ```

4. Add the `JAVA_HOME` variable to your shell configuration file (e.g., `.bashrc` or `.zshrc`) to make it persistent, please note and replace `YOURSYSTEM` with your system architecture, like :
    ```bash
    echo 'export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-YOURSYSTEM' >> ~/.bashrc
    source ~/.bashrc
    ```
