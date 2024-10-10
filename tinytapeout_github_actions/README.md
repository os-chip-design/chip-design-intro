# TinyTapeout Github Actions  
### _Open Source Chip Design Course_  
July 2024

## Introduction
This document is intended to teach how to use TinyTapeout by utilizing GitHub actions. This can be used in case building locally will not work or as the last step in your workflow.

## GitHub Setup

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

## GitHub Actions

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
