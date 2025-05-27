# Dev Log:

This document must be updated daily every time you finish a work session.

## Chenrui Liu

### 2025-05-14 - Setting up the Repository
Spent class period setting up the repository since group is absent.

### 2025-05-15 - Research Period
Did research about brainfuck while group was absent. I looked at the website https://www.dcode.fr/brainfuck-language to try to understand the syntax of the language.

### 2025-05-16 - Absent
I was taking my AP Psychology Exam.

### 2025-05-19 - Started Coding
I started working on the java program to encode plaintext into brainfuck. Basic template but I didn't know how to actually make program.

### 2025-05-20 - Finish Encoder
I spent class time studying the algorithm for encoding plaintext into brainfuck,  and I realized that the algorithm used by the website I was researching could be optimized as there are extraneous cells.I mapped out how to do make the program as comments. Then, I spent time at home making the actual program which ends up a success.

### 2025-05-21 - Working on File Mode
I am attempting to make a file mode for my encoder to take in any file (.txt, .png, etc) and convert their data into brainfuck. I ran into errors with library imports as file handling is more complex.

### 2025-05-22 - Absent
I was taking my AP Calculus BC Exam.

### 2025-05-23 - File Mode Completed
I made a working file mode but soon realized that if I run the file mode on an image, the resulting output is too long so it freezes the program for a bit. I am considering an alternative way to demonstrate this encoding more concisely.

After bouncing ideas with my teammates, I decided the best course of action is to add another flag for if we want to output the brainfuck in shorthand mode or standard mode. Shorthand mode will be useful for large data to minimize the brainfuck output.

### 2025-05-27 - Update Instructions and Update File Mode for output
Added some instructions to the ReadME and also update encode.java to have proper output file for the brainfuck for file mode.
