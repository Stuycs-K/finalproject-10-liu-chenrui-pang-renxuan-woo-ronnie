# Intro to Brainfuck
So what is brainfuck in the first place?

Brainfuck is an esoteric programming language created in 1993 by Urban Muller meant to be minimalistic, consisting of only eight simple commands, a data pointer, and an array of bytes which in this context is referred to as cells or memory. Despite its simplicity, Brainfuck is Turing-complete, meaning it can theoretically compute anything the a conventional computer can.

# How Brainfuck Works

Here are the eight commands:
`>`: Increment the data pointer
`<`: Decrment the data pointer
`+`: Increment the byte at the data pointer
`-`: Decrement the byte at the data pointer
`.`: Output the byte at the data pointer
`[`: Is the start of a loop of commands.
`]`: Is the end of a loop of commands.
The loops only work as long as the byte at the position of the data pointer is not zero.

`,`: ...

Each character's ASCII value needs to be reached by incrementing or decrementing a cell, and then printed. Loops make the process more efficient for repeating increments/decrements.

# Example for Understanding: "Hi"
At the most basic level, you can simply increment the current cell 72 times using the `+`command and then outputing "H" with `.` Increment it another time and outputing it to get "I", but that's no fun and the brainfuck clogs up the screen. We want to utilize loops.