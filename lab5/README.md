# Week 5 Exercise  
*Open Source Chip Design Course*  
July 2024

## Introduction
The past weeks we have installed all the required tools, and now we want to use them. In this course, you will make a design for TinyTapeout; this is a project where many people share an ASIC to reduce costs to a reasonable level.

## Setup VS Code
At this point, it is highly recommended that you use `Visual Studio Code` to write code on Windows; on other systems, it matters less.

**Windows:**  
To do this, you must first install `VS Code` from [here](https://code.visualstudio.com/). Then you must install the WSL for VS Code from [here](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-wsl).

Now, in your WSL terminal, navigate to your Chisel directory:
```bash
cd ~/my_designs/design2/chisel
```

Now you can, in your WSL terminal, write:
```
code ~/my_designs/design2
```

This will open VS Code on Windows and connect it to your WSL instance.

**Linux:**  
If you are using Linux, you can also install VS Code, but you can use any other editor.

**Mac:**
Good luck

## TinyTapeout and GitHub Actions
You will now get ready to potentially commit a TinyTapeout project.

Go to the TinyTapeout Verilog template, the newest at the time of writing is [https://github.com/TinyTapeout/tt08-verilog-template](https://github.com/TinyTapeout/tt08-verilog-template), but please find the newest version, which matches the tapeout iteration.

Create a new repository using the template, keep the repo public.

![TinyTapeout GitHub Template](Images/github_template_tinytapeout.png)

You may use a git GUI or git in the terminal. If you are using WSL, you likely need to use the terminal. Some basic commands are presented:

- **git fetch**: This command checks for any updates or changes in the remote repository but does not apply them locally.
- **git pull**: This command fetches changes from the remote repository and applies (merges) them to your local branch.
- **git add .**: This stages **all changed files** in your current directory for the next commit, meaning it adds the changes to the staging area. Use with caution to ensure you're adding the correct files.
- **git commit -m "Your message"**: This commits the staged changes with a message. You must provide a descriptive commit message using the `-m` flag. For example:
	```
	git commit -m "Added new feature for X"
	```
- **git push**: This uploads the committed changes from your local repository to the remote repository (e.g., GitHub).
- **git status**: This command shows the current status of the working directory and the staging area. It lets you see which files have been modified, which are staged for commit, and which remain unchanged. It is useful to check the status before committing to ensure that the correct changes are being tracked.

Pull down your repo. You should be able to do this without login; if you are using git in the terminal, you must set up a GitHub token. You can do this here: [https://github.com/settings/tokens](https://github.com/settings/tokens), which will function as your password. You need to check off the "repo" scope; this will allow you to push.

![GitHub Token Scope](Images/repo_check.png)

You will see the following folders and files of interest:

- **docs/info.md**: You should change this file and make descriptions that fit your project.
- **src/**: In this folder, you can add your Verilog source files.
- **test/**: In this folder, you can add your test file as `tb.v`. Please take a look at the existing `tb.v` before replacing it. You may run the test by entering the directory and doing `make`.
- **info.yaml**: This file contains a lot of information which is required to be filled out before you can build.


Every time you upload changes to your GitHub repo, a few tests will run. It will run the tests `gds`, `docs`, and `test`. To begin with, `gds` and `docs` should fail, but at the end of this, all tests should pass.

Start by filling out the information in **info.yaml**, then **docs/info.md**. The pins in the **info.yaml** follow the syntax:
```
pinout:
	# Inputs
	ui[0]: "ui_in[0]"
	ui[1]: "ui_in[1]"
	ui[2]: "ui_in[2]"
```

You should go to your GitHub project page and go to "Settings/Pages" and change "Deploy from a branch" to "GitHub Actions". This is also explained in this guide: [https://tinytapeout.com/faq/#my-github-action-is-failing-on-the-pages-part](https://tinytapeout.com/faq/#my-github-action-is-failing-on-the-pages-part).

Now push your changes to GitHub. If you have not changed the source files, the project should now pass all tests on GitHub. You should observe the following:

![GitHub Tests Passed](Images/github_test_passed.png)

Now add your Verilog source files to the project and change the test by editing the `tb.v` file.  
As long as your project passes, you should be good for TinyTapeout.

If GitHub actions fail and you don't know why, you may find the log files on GitHub, as displayed in the image:

![GitHub Log Files Location](Images/log_files_location.png)

---

## Hand-in Assignment
This shall be handed in before next week. You should submit a document (preferably LaTeX) that answers the following questions.

### 1. TinyTapeout
- Confirm you have a working setup of TinyTapeout as described in the guide.

### 2. 7-segment display counter
In this project you should design a 7-seqment display using chisel, the display should count up from 0 to F (in hexadecimal) and display the number on a 7-seqment-display. 
The counter should count up once every 1 second given a clock freqeuncy of 1MHz. That is it should count up once every 1 million clock cycles.

The project should utilize non of the input pins and output of pins OUT0 - OUT7. Notice there are 8 outputs due to the ".".

The 7-seq display mapping is displayed:
- 0:   "b00111111"
- 1:   "b00000110"
- 2:   "b01011011"
- 3:   "b01001111"
- 4:   "b01100110"
- 5:   "b01101101"
- 6:   "b01111101"
- 7:   "b00000111"
- 8:   "b01111111"
- 9:   "b01100111"
- 10:  "b01110111"
- 11:  "b01111100"
- 12:  "b00111001"
- 13:  "b01011110"
- 14:  "b01111001"
- 15:  "b01110001"

Where the first binary number (seen from the left) is bound to pin OUT0.
You might need to use the options `firtoolOpts = Array("-strip-debug-info", "--disable-all-randomization", "--lowering-options=disallowPackedArrays")` in Chisel.

#### Chisel references
You might want to take a look at the following resources:
- [Chisel Bootcamp](https://mybinder.org/v2/gh/freechipsproject/chisel-bootcamp/master)
- [Chisel Book](https://www.imm.dtu.dk/~masca/chisel-book.html)
- [Chisel Tutorial](https://github.com/ucb-bar/chisel-tutorial)
- [Chisel Cheatsheet](https://github.com/freechipsproject/chisel-cheatsheet/releases/latest/download/chisel_cheatsheet.pdf)

The cheat sheet is also in the appendix.

### 3. Toolchain
- Confirm you have a working toolchain that includes TinyTapeout as described in the guide.
- Pass the 7-seqment display though the TinyTapeout pipeline.
- Write a test bench for the 7-seqment display and show the simulation resulst in GTKwave.
- Show the 7-seqment display in openlane and confirm it builds corerctly using what you have learned in past weeks.
- Show that the 7-seqment display passes for tinytapeout.

### 4. Notes
To remember important steps in the future, it will be a good idea for you to take notes. These will not be evaluated, but you are allowed to submit them together with the rest of your assignment. These notes are only for you and are not required.

## Appendix
Cheat sheet page 1:
![Cheat sheet page 1](chisel_cheat_sheet/chisel_page_1.png)

Cheat sheet page 2:
![Cheat sheet page 2](chisel_cheat_sheet/chisel_page_2.png)
