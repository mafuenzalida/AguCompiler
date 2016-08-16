package Lexer;

// TODO completar tokens
public class Token {

	public int type; 
	public int column;
	public int row;
	public Object value;


	public Token(int type, int row, int col) {
		this.type = type;
		this.column = col;
		this.row = row;
	}

	public Token(int type, int row, int col, Object val) {
		this.type = type;
		this.column = col;
		this.row = row;
		this.value = val;
	}

}