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

`,`: Takes value of a character and outputs to active cell.

Each character's ASCII value needs to be reached by incrementing or decrementing a cell, and then printed. Loops make the process more efficient for repeating increments/decrements.

# Example for Understanding Encoding: "HI"
At the most basic level, you can simply increment the current cell 72 times using the `+`command and then outputing "H" with `.` Increment it once more and outputing it to get "I", but that's no fun and the brainfuck clogs up the screen. We want to utilize loops.

Since the ASCII values for any character we need are between 32 to 126, we can find a common divisor to function as part of the loop. This is difficult because of the last digit, but we can avoid this calculation and manually increment it after the loop. This leaves us with numbers that are easily divisable by 10 from 30 to 120.

We can loop the the first two cells 10 times incrementing by 7 to get a value of 70 in both cells. To make "H" and "I", we can increment the first cell by 2 and the second cell by 3 while outputing with `.` at each step.

But even with looping, the brainfuck can still be ridiculously long. Especially with our file mode implementation where you may want to encode the bytes of an image into brainfuck. Our encoder implements a shorthand that adds the number before the "+" increment. So if we were to increment 8 times, instead of "++++++++"  we can just write "8+". This improves readability massively.

# Visualizer
