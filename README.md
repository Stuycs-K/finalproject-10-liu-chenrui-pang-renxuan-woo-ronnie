[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/am3xLbu5)
# Brainfuck Interpreter

### GROUP NAME HERE
**Neurosurgeons**

Chenrui Liu

Renxuan Pang

Ronnie Woo

### Project Description:
We wrote brainfuck analyzation tools, including a Decoder, Encoder, and a Visualizer.

### Instructions:

### Run `make clean`

#### For Encoder (argument order matters!):
`make encode ARGS="<flag1> <flag2> <arg>"`

`<flag1>` = '-p' (plaintext mode) or '-f' (file mode)

`<flag2>` = '-s' (shorthand output) or '-n' (standard output)

`<arg>` = 'plaintext' or 'file name'

Using plaintext mode will output the brainfuck to the terminal, while file mode will output the brainfuck to a `output.txt` file.

#### For Visualizer
`make Visualizer`

In the GUI make sure to input correct brainfuck instructions, or press q to exit.
In comma mode (input listening mode), only the first character (ASCII) will be considered.

#### For Decoder
`make decode ARGS="<brainfuck>"`

### Resources/ References:

https://www.dcode.fr/brainfuck-language

https://en.wikipedia.org/wiki/Brainfuck

https://gist.github.com/roachhd/dce54bec8ba55fb17d3a
