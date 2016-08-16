package Tree;
import java.util.ArrayList;
import Parser.Position;

public class FuncDef extends DeclTree
{
	private String name;
	private ArrayList<FuncArg> arguments;
    private TypeTree returntype = null;
    private ASTree expression;

	public FuncDef (String name, ArrayList<FuncArg> arguments, TypeTree type, ASTree expression, Position pos) {
        this.name = name;
        this.arguments = arguments;
        this.returntype = type;
        this.expression = expression;
        this.pos = pos;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        for(FuncArg arg : arguments) {
        	arg.accept (visitor);
        }
        if(returntype != null)
            returntype.accept(visitor);

        expression.accept(visitor);

        visitor.exit (this);
    }

    public String getName() {
        return name;
    }

    public ArrayList<FuncArg> getArguments () {
        return arguments;
    }

    public TypeTree getReturnType () {
        return returntype;
    }
}