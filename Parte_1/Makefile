Compiler.class: Lexer/Lexer.java Lexer/Token.java Lexer/sym.java

%.class: %.java
	javac $<

Lexer/Lexer.java: Lexer/agu.flex
	cd $(dir $<) && jflex $(notdir $<)

.PHONY: clean
clean:
	$(RM) *.class Lexer/*.class Lexer/Lexer.java