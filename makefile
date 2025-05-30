decode: decode.class
	@java decode $(ARGS)
decode.class:
	@javac decode.java
encode: encode.class
	@java encode $(ARGS)
encode.class:
	@javac encode.java
Visualizer: Visualizer.class
	@java Visualizer $(ARGS)
Visualizer.class:
	@javac Visualizer.java
clean:
	@rm -rf *.class output.txt
