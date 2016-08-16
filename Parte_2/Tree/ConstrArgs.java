package Tree;
import java.util.ArrayList;

public class ConstrArgs extends DeclTree
{
	private ArrayList<ConstrArgDec> arguments = null;

	public ConstrArgs (ArrayList<ConstrArgDec> arguments) {
        this.arguments = arguments;
    }

    public void accept (TreeVisitor visitor) {
        visitor.enter (this);
        for(ConstrArgDec arg : arguments) {
            arg.accept (visitor);
        }
        visitor.exit (this);
    }
}