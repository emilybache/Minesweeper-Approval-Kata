Minesweeper
===========

This repo contains a simple program that could be a part of a minesweeper game.
The program can be run like this, to produce help text:

    ./minesweeper.py --help

This tells you the format of input that is expected to the program. There are
several sample input files included in this repo:

    demo.txt
    ATInput.txt

For the latter, the expected program output is included in 'ATOutput.txt'.

The program also produces a log file when it runs: 'minesweeper.log'.

The minesweeper program is buggy. For example if you give it the file 'ATInput.txt'
it does not produce the expected output 'ATOutput.txt'.

Your task is to create test cases to expose the bug(s), and then fix them.
The suggestion is to use the tool 'TextTest' to create the tests.

