# Intro to Brainfuck
So what is brainfuck in the first place?

Brainfuck is an esoteric programming language created in 1993 by Urban Muller meant to be minimalistic, consisting of only eight simple commands, a data pointer, and an array of bytes which in this context is referred to as cells or memory. Despite its simplicity, Brainfuck is Turing-complete, meaning it can theoretically compute anything that a conventional computer can.

# How Brainfuck Works

Here are the eight commands:
- `>`: Increment the data pointer
- `<`: Decrment the data pointer
- `+`: Increment the byte at the data pointer
- `-`: Decrement the byte at the data pointer
- `.`: Output the byte at the data pointer
- `[`: Is the start of a loop of commands.
- `]`: Is the end of a loop of commands.
The loops only work as long as the byte at the position of the data pointer is not zero.
- `,`: Takes value of a character and outputs to active cell.

Each character's ASCII value needs to be reached by incrementing or decrementing a cell, and then printed. Loops make the process more efficient for repeating increments/decrements.

# Decoding

The decoder takes in a string of Brainfuck code, either as ciphertext (`-c`) or in a file (`-f`). It uses a function `execute()` that parses the input and executes it character by character.

`>` and `<` move the pointer. If the pointer ever goes out of bounds, `RANGE ERROR` is printed and the program ends.

`+` and `-` increment the value at the cell being pointed at. The value wraps around within the range 0-255.

`.` prints the character with the corresponding ASCII value at current cell.

When `[`is parsed, `execute()` is called recursively on the substring of code between the left bracket and its corresponding right bracket. This substring is determined by reading forward one character at a time until `]` is found. For nested loops, if another `[` is found before a `]`, the next `]` is not counted as the corresponding right bracket.

Example (not actual code): `[+++.[+++].]`

## Test Cases

`make decode ARGS="-c +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.+.+.+."`

Output: "ABCD"

`make decode ARGS="-c ,."`

Output: Whatever character is inputted

`make decode ARGS="-c +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.[-\>+\<]\>."`

Output:"AA"

`make decode ARGS="-c ++++++++[\>++++[\>++\>+++\>+++\>+\<\<\<\<-]\>+\>+\>-\>\>+[\<]\<-]\>\>.\>---.+++++++..+++.\>\>.\<-.\<.+++.------.--------.\>\>+.\>++."`
Output: "Hello World!"

# Encoder
At the most basic level, you can encode anything in brainfuck by just incrementing using the `+` command and then outputting with the `.` command. However, this is very inefficient and the brainfuck will be almost impossible to read. To solve this, our encoder utilizes the `[` and `]` commands to loop and shorten the process. 

Since the ASCII decimal values for any common character we need are between 32 to 126, we can find a common factor to function as the number of loops. This is difficult because of the last digit, but we can avoid this calculation and manually increment it after the loop which leaves us with numbers that are easily divisible by 10 from 30 to 120. The quotient from this division will be the value of each loop for that character value.

To improve ease of use, the encoder features a file mode and a shorthand mode. 

- File mode can be useful to encode a ".txt" file. File mode is **NOT** suggested with large files like images.

- Shorthand mode is useful to automatically count the number of "+"s in each brainfuck operation so you can follow along easier with the process. It follows the format "<#>+" with <#> being the number of "+"s.

## Test Cases
`make encode ARGS="-p -n 'HI'"`

`make encode ARGS="-p -n 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.'"`

# Visualizer
For the visualizer, after you run the code you will be presented with a GUI and all you have to do is to enter brainfuck instructions (the 8 instructions) and you will be able to see the code compile in action. On the bottom here displays 25 cells that you can work with, and on the right side you can see which page you are on, and that is all you need to start writing brainfuck commands.

The user can either just type a command (out of the 8) and then press enter, or press q when they would like to exit the program. 

When you reach the end of the scrolling for the current page, the page will automatically scroll to the next cell unless there are no more cells to go to. 

## Test Case
For the test case I will be demonstrating the Hello World program running in the visualizer
