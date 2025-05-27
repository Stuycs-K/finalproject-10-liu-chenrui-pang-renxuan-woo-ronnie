decode: decode.class
	@java decode $(ARGS)
decode.class:
	@javac decode.java
clean:
	@rm -rf *.class
