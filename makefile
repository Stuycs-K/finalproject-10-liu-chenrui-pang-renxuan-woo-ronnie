encode: encode.class
	@java encode $(ARGS)
encode.class:
	@javac encode.java
clean:
	@rm -rf *.class
