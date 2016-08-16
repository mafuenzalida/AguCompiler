package Tree;
import java.util.ArrayList;
import Parser.Position;

public class ClassCtor extends ClassBody
{
	private String name;
	private ArrayList<ASTree> expressions;

	public ClassCtor (String name, ArrayList<ASTree> expressions, Position pos) {
        this.name = name;
        this.expressions = expressions;
        this.pos = pos;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        for(ASTree e : expressions) {
        	e.accept (visitor);
        }
        visitor.exit (this);
    }

    public String getName() {
        return name;
    }

    public ArrayList<ASTree> getExpressions() {
        return expressions;
    }
}