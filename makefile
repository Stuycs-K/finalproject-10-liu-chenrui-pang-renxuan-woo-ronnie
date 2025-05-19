run: Bf.class
	@cd Programs/Java;java Bf $ARGS
Bf.class:
	@javac ./Programs/Java/Bf.java
clean:
	@rm -rf ./Programs/Java/Bf.class
