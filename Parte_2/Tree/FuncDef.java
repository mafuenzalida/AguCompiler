package Tree;
import java.util.ArrayList;

public class FuncDef extends DeclTree
{
	private String name;
	private ArrayList<FuncArg> arguments;
    private TypeTree type = null;
    private ASTree expression;

	public FuncDef (String name, ArrayList<FuncArg> arguments, TypeTree type, ASTree expression) {
        this.name = name;
        this.arguments = arguments;
        this.type = type;
        this.expression = expression;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        for(FuncArg arg : arguments) {
        	arg.accept (visitor);
        }
        if(type != null)
            type.accept(visitor);

        expression.accept(visitor);

        visitor.exit (this);
    }

    public String getName() {
        return name;
    }
}