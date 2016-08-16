package Tree;
import java.util.ArrayList;
import Parser.Position;

public class ConstrArgs extends DeclTree
{
	private ArrayList<ConstrArgDec> arguments = null;

	public ConstrArgs (ArrayList<ConstrArgDec> arguments, Position pos) {
        this.arguments = arguments;
        this.pos = pos;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        for(ConstrArgDec arg : arguments) {
            arg.accept (visitor);
        }
        visitor.exit (this);
    }
}