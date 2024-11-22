# Week 3 Exercise

## Chisel3 and Make

### Open Source Chip Design Course

July 2024

---

### Introduction

The previous weeks you installed OpenLane2, ran OpenLane2, installed the OSS-cad-suite, ran Verilator, and ran GTKwave. This week we will install the Chisel3 tool and combine it with the other tools.

Chisel3 is a library for the "Scala" programming language, allowing you to write Scala code that can be simulated or emitted as Verilog code. The Verilog code can then be further processed.

---

### Installing Chisel

This guide will help you install Chisel on Linux (and WSL). If you experience problems during the installation, you may visit [Chisel Installation](https://www.chisel-lang.org/docs/installation) to resolve common issues.

#### Install Chisel Dependencies

Run the following commands to install Chisel dependencies:

```bash
apt install -y wget gpg apt-transport-https
```

Next, run:

```
wget -qO - https://packages.adoptium.net/artifactory/api/gpg/key/public | gpg --dearmor | tee /etc/apt/trusted.gpg.d/adoptium.gpg >
```

Then, execute:`
```
echo "deb https://packages.adoptium.net/artifactory/deb $(awk -F= '/^VERSION_CODENAME/{print$2}' /etc/os-release) main" | tee /etc/apt/sources.list.d/adoptium.list
```

Now, update the package list:
```
apt update
```

Finally, install the Java Development Kit:
```
apt install temurin-17-jdk
```

You should now have the Java Dev Kit installed.

#### Install Mill

We will now install "Mill," which is a newer alternative to "sbt." First, navigate to your utils folder:
```
cd ~/utils
```

Then run:
```
curl -L https://raw.githubusercontent.com/lefou/millw/0.4.11/millw > mill && chmod +x mill
```

You should now see a "mill" program. This file should be added to your `PATH`. To do this, run:
```
nano ~/.bashrc
```

At the last line of the file, append:
```
export PATH=~/utils:$PATH
```

Then execute:
```
source ~/.bashrc
```

---

### Compiling Chisel

Mill is used to create build scripts. We will now write a build script. Let’s create a work directory:
```
mkdir -p ~/my_designs/design2/chisel
```

Navigate to your working directory:
```
cd ~/my_designs/design2/chisel
```

Create a build file:
```
nano build.sc
```

Write the following inside the file:

```
// import Mill dependency
import mill._
import mill.define.Sources
import mill.modules.Util
import mill.scalalib.TestModule.ScalaTest
import scalalib._
// support BSP
import mill.bsp._

object myproject extends SbtModule { m =>
    override def millSourcePath = os.pwd
    override def scalaVersion = "2.13.12"
    
    override def scalacOptions = Seq(
        "-language:reflectiveCalls",
        "-deprecation",
        "-feature",
        "-Xcheckinit",
    )
    
    override def ivyDeps = Agg(
        ivy"org.chipsalliance::chisel:6.5.0",
    )
    
    override def scalacPluginIvyDeps = Agg(
        ivy"org.chipsalliance:::chisel-plugin:6.5.0",
    )
    
    object test extends SbtTests with TestModule.ScalaTest {
        override def ivyDeps = m.ivyDeps() ++ Agg(
            ivy"org.scalatest::scalatest::3.2.16"
        )
    }
}
```

This code allows you to run Chisel3 code. Note that Chisel has a special way of organizing its files. To accommodate this, create the following two folders:
```
mkdir -p ~/my_designs/design2/chisel/src/main/scala
```
```
mkdir -p ~/my_designs/design2/chisel/src/test/scala
```

Code describing components should be placed in the `.../main/scala` folder, while code meant for testing should be placed in `.../test/scala`.

### Create an Adder Component
Create a component file for an adder:
```
nano ~/my_designs/design2/chisel/src/main/scala/adder.scala
```

Write the following code into the file:
```
package adder

import chisel3._
import _root_.circt.stage.ChiselStage

class Adder extends Module {
    val io = IO(new Bundle {
        val a = Input(UInt(8.W))
        val b = Input(UInt(8.W))
        val c = Output(UInt(8.W))
    })

    io.c := io.a + io.b
}

/**
 * Generate Verilog sources and save it in file adder.v
 */
object Main extends App {
    ChiselStage.emitSystemVerilogFile(
        new Adder,
        firtoolOpts = Array("-disable-all-randomization", "-strip-debug-info")
    )
}
```

You should also create a test file:
```
nano ~/my_designs/design2/chisel/src/test/scala/adder_spec.scala
```

Then write the following code into the file:
```
package adder

import chisel3._
import chisel3.simulator.EphemeralSimulator._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers

class AdderSpec extends AnyFreeSpec with Matchers {
    "Adder should add" in {
        simulate(new Adder()) { dut => 
            dut.io.a.poke(3.U)
            dut.io.b.poke(4.U)
            dut.io.c.expect(7.U)
        }
    }
}
```

Now you have all the files required, and you can compile and test your circuit.

To compile your Chisel code, first enter your Chisel directory:
```
cd ~/my_designs/design2/chisel
```

Then run:
```
mill myproject
```

You should now see that an Adder.sv file has appeared in your directory.

You can also run the test by executing:
```
mill myproject.test
```

You should see:
```
AdderSpec:
- Adder should add
Run completed in 4 seconds, 136 milliseconds.
Total number of tests run: 1
Suites: completed 1, aborted 0
Tests: succeeded 1, failed 0, canceled 0, ignored 0, pending 0
All tests passed.
```

You now have Chisel3 installed.

You might observe a warning:
```
No mill version specified.
You should provide a version via '.mill-version' file or --mill-version option.
```

To get rid of this warning, create a `.mill-version` file:
```
nano .mill-version
```

Then write in the file:
```
0.11.12
```

If you want to learn more about Mill, this is a good resource: [Mill Build Tool](https://alvinalexander.com/scala/mill-build-tool/step-1-hello-world/). You might also want to check out [Chisel Template](https://github.com/chipsalliance/chisel-template/tree/main) for reference (don't use it).


### All the Way

We will now take the output from Chisel and pass it through Verilator, GTKwave, and then OpenLane2.

You should see the following file structure in your working directory:
![File structure](file_structure.png)

At this point, it would make sense to create a makefile that calls a series of commands which we always want to run together.

Make the makefile:
```
nano ~/my_designs/design2/makefile
```

Now copy the makefile from the learn section into this makefile, or it can be found in the repo.

Once you have placed the makefile in `~/my_designs/design2`, you can run:
```
make
```

This will do a run from *Chisel code* to *Verilog* and then pass that to *Openlane* and open the *Openlane GUI*.

You can pass the following arguments to the *make*:

- build
- 	This is the default, and will also be called if you just do "make", it will compile your Chisel code, copy the resulting verilog files to the "verilog" folder, do a Openlane run and finally display the result using the OpenLane GUI. 
- test
- 	This will run the Chisels tests.
- sim
- 	Will compile your Chisel code, copy the resulting verilog files to the "verilog" folder, call verilator, run the executable and then open the vcd file in GTK wave.
- clean
- 	Will cleanup the directory, removing folder generated by the tools.


Example:
```
make test
```

If you try to run "make sim" you will receive an error, this is because we have not setup the verilog test bench yet.

You will need to add a "tb.v":
\begin{minted}{text}
nano ~/my_designs/design2/tb.v
\end{minted}

and then write the content of `tb.v` from learn into this file, alternatively you maybe view the contents in the repo.

If you try to run `make sim`, you will receive an error. This is because we have not set up the Verilog test bench yet.

You will need to add a `tb.v`:
```
nano ~/my_designs/design2/tb.v
```

Then write the content of `tb.v` from learn into this file. Alternatively, you may view the contents in the repo.

Your file structure should look like the following:

![The file structure to expect.](Images/file_structure2.png)

The commands provided by the makefile will be a good starting point for your projects.

---

# Hand-in Assignment
This shall be handed in before next week. You should hand in a document (preferably LaTeX) which answers the following questions.

## 1. Chisel
1. Confirm you have installed Chisel3 and you are able to compile the example.
2. Describe any problems you had during the installation (to help students next year).
3. Run the Chisel test, show the output.

## 2. Build script
1. Confirm that you can run all the build script commands.
2. Go through the build script and explain in your own words what it does, just a 1-2 sentence per recipe (command).
3. What does the **--command** argument do when passed to `nix-shell`?
4. Change the makefile so that it moves the Verilog files to the Verilog folder instead of copying the files.

## 3. Making some changes
1. Convert the 8-bit adder to a 16-bit adder.
2. Run the Chisel test and confirm it is working.
3. Edit the `tb.v` to check that **100 + 200** is **300**.
4. Run Verilator and show it is working; make sure you are simulating the new 16-bit version.
5. Run the 16-bit adder through OpenLane2; show the result in the GUI.

## 4. Notes
For you to remember important steps in the future, it will be a good idea for you to take notes. These will not be evaluated, but you are allowed to turn them in together with the rest of your assignment. These notes are only for you and are not required.
