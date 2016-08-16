package Tree;
import java.util.ArrayList;

public class ClassCtor extends ClassBody
{
	private String name;
	private ArrayList<ASTree> expressions;

	public ClassCtor (String name, ArrayList<ASTree> expressions) {
        this.name = name;
        this.expressions = expressions;
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
}