# Week 1 Exercise
### Setup of Openlane2
#### Open Source Chip Design Course
*July 2024*

## Introduction
In this course, you'll begin by setting up the required tools on your own PC, which are fundamental for working with open-source chip design. These tools will allow you to run simulations, compile designs, and verify digital circuits using open-source workflows. Throughout the course, you will be interacting with OpenLane2; a toolchain designed for physical design automation (PDA) of digital circuits.

Once the tools are installed, you’ll move on to creating a simple design that will go through several verification stages, such as layout versus schematic (LVS), design rule checking (DRC), and timing analysis. These steps simulate real-world processes in chip fabrication. After completing the design flow, you’ll hand in a report documenting your setup, challenges, and initial design results. This will ensure that you have a properly functioning toolchain and understand the process of digital circuit design and verification.

## Installing the tools
This guide contains the same information as the documentation, found at: [https://openlane2.readthedocs.io/en/latest/getting_started/index.html](https://openlane2.readthedocs.io/en/latest/getting_started/index.html). Read the docs for more information. If you encounter any problems during installation, the docs will be a helpful resource to resolve them.

The installation process will take up several gigabytes of storage on your system, so it’s important to verify you have enough space before starting. During installation, multiple tools, such as the Nix package manager will be configured. Nix will handle the toolchain setup.

This setup assumes you’re using a Linux-based system (Ubuntu 22.04 or newer) or Windows with WSL2. You’ll also install essential compilers, libraries, and dependencies like Python 3.11, G++, and more. By the end of the installation, you’ll have a fully functioning environment for running digital design workflows, and you'll be ready to proceed till next week.

To check your Ubuntu version do:
```
lsb_release -a
```

You may upgrade your Ubuntu by doing:
```
sudo do-release-upgrade
```

Required packages
&nbsp;

Before starting, you must install the following requirements:

- Git (package name: git)
- Nix (package name: nix-bin)
- G++ (package name: g++)
- Gcc (package name: gcc)
- Clang (package name: clang)
- Python3 (package name: python3.11)
- Python3 venv (package name: python3.11-venv)
- Make (package name: make)
- Pip3 (package name: pip)
- Wget (package name: wget)
- help2man (package name: help2man)
- Perl (package name: perl)
- Perl-doc (package name: perl-doc)
- libfl2 (package name: libfl2)
- libfl-dev (package name: libfl-dev)
- zlibc (package name: zlibc)
- zlib1g (package name: zlib1g)
- lib1g-dev (package name: zlib1g-dev)
- Ccache (package name: ccache)
- Mold (package name: mold)
- Perftools (package name: libgoogle-perftools-dev)
- Numactl (package name: numactl)
- Tkinter (package name: python3-tk)
- docker (package name: docker.io)
- Librsvg2-bin (package name: librsvg2-bin)
- Pngquant (package name: pngquant)

&nbsp;

You can download these using apt by doing:
```
apt install insert package name
```

If you are not using apt, you are likely skilled enough to download through your own package manager.
It is also a good idea to do:
```
sudo apt-get update && sudo apt-get upgrade && sudo apt-get dist-upgrade
```

This will update all your packages. 
Once you have downloaded all the requirements, you must make an alias for Python. Do:
```
alias python="python3.11"
```

&nbsp;
To view that the alias is set up correctly, do:
alias
&nbsp;

You should see an entry stating:
```
alias python='python3.11'
```

&nbsp;

The alias will not be remembered if you restart. You might need to do this again in the future. This might be required during the installation of some tools. It is important that you are using Python 3.11 for later steps.

&nbsp;

In this course, the OpenLane2 toolchain is installed through Nix. Firstly, do:
```
git clone https://github.com/efabless/openlane2/ ~/openlane2
```

&nbsp;

This will place the OpenLane2 project in your home folder. You can access the folder by doing:
```
cd ~/openlane2
```

&nbsp;

The following step takes a long time, and it is recommended that you take a break in the meantime, do:
```
nix-shell --pure ~/openlane2/shell.nix
```

This might take 20-60 minutes. This will set up the project and open a nix-shell. All following commands will take place inside the nix-shell.
Test that the installation worked by doing inside the nix terminal:
```
openlane --log-level ERROR --condensed --show-progress-bar --smoke-test
```
This will download the skywater130nm; you should not see any errors. You should see green process-bars stating 100% and everything should download.

At this point, it should be stated that you can exit the nix-shell by doing:
```
exit
```
and you can re-enter the nix-shell by doing:
```
nix-shell --pure ~/openlane2/shell.nix
```

---

# Making a design
At this point, you have everything needed installed; now you shall make a design.

Exit the nix-shell by doing:
```
exit
```

You must make a folder; it is recommended that you do:
```
mkdir -p ~/my_designs/my_first_design
```

&nbsp;

Navigate to the folder:
```
cd ~/my_designs/my_first_design
```
&nbsp;

Now create a configuration file inside the folder:
```
nano config.json
```

&nbsp;

Copy-paste into the config.json file:
```json
{
  "DESIGN_NAME": "counter_64bit",
  "VERILOG_FILES": ["dir::source/counter.v"],
  "CLOCK_PERIOD": 25,
  "CLOCK_PORT": "clk"
}
```

In nano, you can do this by right-clicking, ctrl+s, and ctrl+x.
Make a directory for your Verilog source files inside your my_first_design folder:
```
mkdir source
```

Place the counter.v and tb.v file from learn inside the source folder.
Now re-enter the nix-shell with:
```
nix-shell --pure ~/openlane2/shell.nix
```

Now you can run openlane2 on the design by doing:
```
openlane ~/my_designs/my_first_design/config.json
```

You should see:
```
    * Antenna
    Passed (Y)
    
    * LVS
    Passed (Y)
    
    * DRC
    Passed (Y)
```
Well done, you have made your first design with OpenLane2.
To view your design, do:
```
openlane --last-run --flow openinklayout ~/my_designs/my_first_design/config.json
```

You should see your design in KLayout.
Also, check that OpenRoad GUI works by doing:
```
openlane --last-run --flow openinopenroad ~/my_designs/my_first_design/config.json
```

You should see your design in the OpenRoad GUI.

## Hand-in Assignment

This assignment is to be submitted before next week. You should provide a document (preferably in LaTeX) that answers the following questions.

### 1. Setup
1. Confirm that the installation was successful; optionally, include a screenshot.
2. Describe any problems encountered during the installation (to assist students next year).
3. Locate and confirm the existence of a "runs" folder. State its location.

### 2. Screenshots
1. Include a screenshot of your design in KLayout.
2. Include a screenshot of your design in the OpenRoad GUI.

### 3. Read the Docs
This guide is largely based on the documentation found at [OpenLane2 Documentation](https://openlane2.readthedocs.io/en/latest/getting_started/newcomers/index.html). This documentation includes further reading required to answer the following questions. Each answer should be 3-7 sentences long.

1. Describe in your own words what LVS is and state its full name.
2. Describe in your own words what DRC is and state its full name.
3. Describe in your own words what STA is and state its full name.
4. Describe in your own words what an antenna check is.

### 4. Notes
To aid your memory six weeks from now, it is advisable to take notes. These notes will not be evaluated, but you may submit them along with the rest of your assignment. These notes are solely for your reference and are not required.
