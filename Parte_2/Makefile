# Archivos fuente. Se usa wildcard para que la variable SOURCE
# contenga todos los archivos por separado, y no dir/*.java.
SOURCE = $(wildcard Printer/*.java) $(wildcard Tree/*.java)

# Directorio para los archivos .class.
BUILDDIR = build

# Con $? se seleccionan solo los archivos modificados del lado derecho.
$(BUILDDIR)/Main.class: Main.java Lexer/Lexer.java Parser/Parser.java $(SOURCE)
	javac -d $(BUILDDIR) $?

# Generación del lexer y parser. El lexer depende del parser ya
# que necesita la interfaz, y clases como Location
Lexer/Lexer.java: Lexer/agu.flex Parser/Parser.java
	cd $(dir $<) && jflex $(notdir $<)

Parser/Parser.java: Parser/agu.y
	cd $(dir $<) && $(YACC) -Wall --report all $(notdir $<)

.PHONY: clean
clean:
	$(RM) build/*.class build/*/*.class
	$(RM) Lexer/Lexer.java Lexer/Lexer.java~ Parser/Parser.java