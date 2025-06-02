# Dev Log:

This document must be updated daily every time you finish a work session.

## Ronnie Woo

### 2025-05-15 - Absent for AP Physics C Exam

### 2025-05-16 - Project Discussion

We spent the class period discussing different ideas and deciding on our project.

### 2025-05-19 - Starting encode

We decided in class to make a brainfuck encoder, decoder, and visualizer. We looked at what the different symbols do in brainfuck at https://en.wikipedia.org/wiki/Brainfuck. I began coding the encoder and set up a basic input prompt structure.

### 2025-05-20 - Implementing commands

I created the first 5 commands (without taking user input or loops), and started testing at https://www.tutorialspoint.com/compilers/online-brainfk-compiler.htm.

### 2025-05-21 - Absent for AP Macroeconomics Exam

### 2025-05-22 - Wrapping and Byte Input

I implemented wrapping for + and - between the valid ASCII values of 0-255, and added range errors for if the pointer to is moved out of bounds of the 30000 cell array. I then implemented byte input from the user with ,.

### 2025-05-22 - Looping Code

I implemented loops with the [ and ] symbols, but didn't test yet.

### 2025-05-27 - Testing loops

I tested the loop code and fixed escape characters within the test cases.

### 2025-05-28 - Nested loops

I changed the looping code to allow for nested loops and tested successfully.

### 2025-05-29 - Starting File mode

I created file mode for encode, but didn't test it yet. I also fixed recursive "RANGE ERROR" printing.

### 2025-05-30 - Finishing File mode

I fixed a file writing for file mode in decode, and tested it.

### 2025-06-02 - Slides and README

I wrote the presentation slides section and README section for the decoder.