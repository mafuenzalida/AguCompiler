package Tree;
import Semantic.Type;
import Parser.Position;

public abstract class ASTree
{
	public Type type;
	public Position pos;
	public boolean e = false;

    public abstract void accept (TreeVisitor visitor);
}
